package com.ftn.adriabike.service;

import com.ftn.adriabike.model.*;
import com.ftn.adriabike.repository.*;
import com.ftn.adriabike.web.dto.IzlaznaFakturaDTO;
import com.ftn.adriabike.web.dto.StavkaIzlazneFaktureDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FakturaService{

    @Autowired
    FakturaRepository fakturaRepository;

    @Autowired
    StavkaFaktureRepository stavkaFaktureRepository;

    @Autowired
    private KorisnikService userService;

    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private StavkaKorpeRepository stavkaKorpeRepository;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MagacinskaKarticaRepository magacinskaKarticaRepository;

    @Autowired
    private MagacinskaKarticaService magacinskaKarticaService;





    public void createFaktura(Authentication authentication){
        Korisnik current = userService.getUser(authentication);
        IzlaznaFaktura izlaznaFaktura = new IzlaznaFaktura();
        izlaznaFaktura.setBrojFakture(ThreadLocalRandom.current().nextInt(10, 10000000 + 1));
        izlaznaFaktura.setDatumIzdavanja(Date.valueOf(LocalDate.now()));
        izlaznaFaktura.setDatumValute(Date.valueOf(LocalDate.now()));
        izlaznaFaktura.setUkupanRabat(0.0);
        izlaznaFaktura.setStatusFakture(Status.NEPOTVRDJENA);
        izlaznaFaktura.setKupac(current);


        fakturaRepository.save(izlaznaFaktura);

        //nadji korpu korisnika
        Korpa korpa = korpaRepository.findFirstByKupac(current);

        for(StavkaKorpe stavkaKorpe : stavkaKorpeRepository.findAllByKorpa(korpa)){
            StavkaFakture stavkaFakture = new StavkaFakture();
            System.out.println("stavka korpe " + stavkaKorpe);

            Artikal artikal = artikalRepository.findById(stavkaKorpe.getArtikal().getId()).orElse(null);

            stavkaFakture.setKolicina(stavkaKorpe.getKolicina());
            stavkaFakture.setJedinicnaCena(artikalService.getCenaArtikla(artikal));
            stavkaFakture.setRabat(0.0);
            stavkaFakture.setOsnovica(artikalService.getCenaArtiklaOsnovica(artikal));

            stavkaFakture.setProcenatPDV(artikal.getPoreskaKategorija().getPoreskaStopa().getProcenatPDV());
            stavkaFakture.setIznosPDV(stavkaFakture.getJedinicnaCena() - stavkaFakture.getOsnovica());

            stavkaFakture.setUkupno(stavkaFakture.getKolicina() * stavkaFakture.getJedinicnaCena());
            stavkaFakture.setArtikal(artikal);
            stavkaFakture.setIzlaznaFaktura(izlaznaFaktura);

            stavkaFaktureRepository.save(stavkaFakture);
            stavkaKorpeRepository.delete(stavkaKorpe);

        }

        izlaznaFaktura.setUkupanIznos(stavkaFaktureRepository.getUkupanIznos(izlaznaFaktura.getId()));
        izlaznaFaktura.setUkupnaOsnovica(stavkaFaktureRepository.getUkupnaOsnovica(izlaznaFaktura.getId()));
        izlaznaFaktura.setUkupanPDV(stavkaFaktureRepository.getUkupnoPDV(izlaznaFaktura.getId()));

        String subject = "Поштовани/а,\nХвала што купујете код нас.\nСтатус ваше поруџбине можете проверити кроз Ваш налог на сајту.\nУ колико је наручена роба доступна и спремна за реализацију наше сарадње добићете имејл са потврдом да се иста може испоручити или преузети. Тренутни статус ваше поруџбине је: NA ČEKANJU";
       // notificationService.sendNotification(current, subject, izlaznaFaktura.getBrojFakture());

        korpaRepository.delete(korpa);

        fakturaRepository.save(izlaznaFaktura);



    }

    public List<IzlaznaFakturaDTO> getIzlazneFaktureNaCekanju(){
        List<IzlaznaFakturaDTO> izlaznaFakturaDTO = new ArrayList();

        for(IzlaznaFaktura izlaznaFaktura : fakturaRepository.findByStatusFakture(String.valueOf(Status.NEPOTVRDJENA))){
            izlaznaFakturaDTO.add(new IzlaznaFakturaDTO(izlaznaFaktura));

            for(StavkaFakture stavkaFakture: stavkaFaktureRepository.findAllByIzlaznaFaktura(izlaznaFaktura)){
                for(IzlaznaFakturaDTO i:  izlaznaFakturaDTO){
                    i.getStavkaFakture().add(new StavkaIzlazneFaktureDTO(stavkaFakture));
                }
            }


        }

        return izlaznaFakturaDTO;
    }

    public IzlaznaFakturaDTO getFaktura(Integer fakturaId){
        IzlaznaFaktura izlaznaFaktura = fakturaRepository.findById(fakturaId).orElse(null);
        IzlaznaFakturaDTO izlaznaFakturaDTO = new IzlaznaFakturaDTO(izlaznaFaktura);
        for(StavkaFakture stavkaFakture: stavkaFaktureRepository.findAllByIzlaznaFaktura(izlaznaFaktura)){
            izlaznaFakturaDTO.getStavkaFakture().add(new StavkaIzlazneFaktureDTO(stavkaFakture));

        }

        return izlaznaFakturaDTO;

    }

    public String checkKolicina(Integer fakturaId){
        IzlaznaFaktura izlaznaFaktura = fakturaRepository.findById(fakturaId).orElse(null);
        StringBuilder message = new StringBuilder();
        for(StavkaFakture stavkaFakture: stavkaFaktureRepository.findAllByIzlaznaFaktura(izlaznaFaktura)){
            MagacinskaKartica magacinskaKartica =magacinskaKarticaRepository.findFirstByArtikal(stavkaFakture.getArtikal());
            Integer kolicina = magacinskaKartica.getPocetnoStanjeKolicina() + magacinskaKartica.getPrometUlazaKolicina() - magacinskaKartica.getPrometIzlazaKolicina();

            if(stavkaFakture.getKolicina() < kolicina){
                message.append("Dostupna je roba ").append(stavkaFakture.getArtikal().getNaziv()).append(" u datoj količini");
                magacinskaKarticaService.magacinskaKarticaIzlaz(stavkaFakture);
            }else{
                message.append("\nNedostupna je roba ").append(stavkaFakture.getArtikal().getNaziv()).append(", nedovoljna količina!");
            }


        }
        return message.toString();
    }






}
