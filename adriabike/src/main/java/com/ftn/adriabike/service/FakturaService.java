package com.ftn.adriabike.service;

import com.ftn.adriabike.model.*;
import com.ftn.adriabike.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
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




    public void createFaktura(Authentication authentication){
        Korisnik current = userService.getUser(authentication);
        IzlaznaFaktura izlaznaFaktura = new IzlaznaFaktura();
        izlaznaFaktura.setBrojFakture(ThreadLocalRandom.current().nextInt(10, 10000000 + 1));
        izlaznaFaktura.setDatumIzdavanja(Date.valueOf(LocalDate.now()));
        izlaznaFaktura.setDatumValute(Date.valueOf(LocalDate.now()));
        izlaznaFaktura.setUkupanRabat(0.0);
        fakturaRepository.save(izlaznaFaktura);

        //nadji korpu korisnika
        Korpa korpa = korpaRepository.findFirstByKupac(current);
        for(StavkaKorpe stavkaKorpe : stavkaKorpeRepository.findAllByKorpa(korpa)){
            StavkaFakture stavkaFakture = new StavkaFakture();
            Artikal artikal = artikalRepository.findById(stavkaKorpe.getId()).orElse(null);

            stavkaFakture.setKolicina(stavkaKorpe.getKolicina());
            stavkaFakture.setJedinicnaCena(artikalService.getCenaArtikla(artikal));
            stavkaFakture.setRabat(0.0);
            stavkaFakture.setOsnovica(artikalService.getCenaArtiklaOsnovica(artikal));

            //get these values
            stavkaFakture.setProcenatPDV(artikal.getPoreskaKategorija().getPoreskaStopa().getProcenatPDV());
            stavkaFakture.setIznosPDV(artikalService.getCenaArtiklaOsnovica(artikal) * (artikal.getPoreskaKategorija().getPoreskaStopa().getProcenatPDV()/100));

            stavkaFakture.setUkupno(stavkaFakture.getKolicina() * stavkaFakture.getJedinicnaCena());
            stavkaFakture.setArtikal(artikal);
            stavkaFakture.setIzlaznaFaktura(izlaznaFaktura);

            stavkaFaktureRepository.save(stavkaFakture);

        }



    }



}
