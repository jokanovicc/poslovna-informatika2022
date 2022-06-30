package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.MagacinskaKartica;
import com.ftn.adriabike.model.StavkaFakture;
import com.ftn.adriabike.repository.ArtikalRepository;
import com.ftn.adriabike.repository.MagacinRepository;
import com.ftn.adriabike.repository.MagacinskaKarticaRepository;
import com.ftn.adriabike.repository.PoslovnaGodinaRepository;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MagacinskaKarticaService {

    @Autowired
    private MagacinRepository magacinRepository;
    @Autowired
    MagacinskaKarticaRepository magacinskaKarticaRepository;

    @Autowired
    PrometMagacinskeKarticeService prometMagacinskeKarticeService;

    @Autowired
    PoslovnaGodinaRepository poslovnaGodinaRepository;

    @Autowired
    private ArtikalService artikalService;




    public void createMagacinskaKartica(DobavljanjeNoveRobeDTO dobavljanjeNoveRobeDTO, Artikal artikal){

        MagacinskaKartica magacinskaKartica = new MagacinskaKartica();
        magacinskaKartica.setMagacin(magacinRepository.findById(dobavljanjeNoveRobeDTO.getMagacinId()).orElse(null));

        magacinskaKartica.setPoslovnaGodina(poslovnaGodinaRepository.findLatest());

        magacinskaKartica.setPocetnoStanjeKolicina(0);
        magacinskaKartica.setPrometUlazaKolicina(dobavljanjeNoveRobeDTO.getKolicina());
        magacinskaKartica.setPrometIzlazaKolicina(0);
        magacinskaKartica.setPocetnoStanjeVrednost(0.0);
        magacinskaKartica.setPrometUlazaVrednost(dobavljanjeNoveRobeDTO.getKolicina() * dobavljanjeNoveRobeDTO.getCena());
        magacinskaKartica.setPrometIzlazaVrednost(0.0);
        magacinskaKartica.setUkupnaVrednost(0 + magacinskaKartica.getPrometUlazaVrednost());
        magacinskaKartica.setProsecnaCena(getProsecnaCena(dobavljanjeNoveRobeDTO.getCena(), magacinskaKartica));
        magacinskaKartica.setArtikal(artikal);

        magacinskaKarticaRepository.save(magacinskaKartica);

        prometMagacinskeKarticeService.createUlaznogPrometaMagacinskeKartice(dobavljanjeNoveRobeDTO, magacinskaKartica);



    }

    public void createPocetnoStanje(){



        for(MagacinskaKartica magacinskaKartica: magacinskaKarticaRepository.findProslogodinjeKartice()){


            MagacinskaKartica novaMagacinskaKartica = new MagacinskaKartica();
            novaMagacinskaKartica.setPocetnoStanjeKolicina(magacinskaKartica.getPrometUlazaKolicina() - magacinskaKartica.getPrometIzlazaKolicina());
            novaMagacinskaKartica.setPocetnoStanjeVrednost((magacinskaKartica.getPrometUlazaKolicina() - magacinskaKartica.getPrometIzlazaKolicina()) * artikalService.getCenaArtiklaOsnovica(magacinskaKartica.getArtikal()));
            novaMagacinskaKartica.setProsecnaCena(magacinskaKartica.getPocetnoStanjeVrednost());

            novaMagacinskaKartica.setMagacin(magacinskaKartica.getMagacin());
            novaMagacinskaKartica.setUkupnaVrednost(novaMagacinskaKartica.getPocetnoStanjeVrednost());
            novaMagacinskaKartica.setPrometUlazaVrednost(0.0);
            novaMagacinskaKartica.setPrometIzlazaVrednost(0.0);
            novaMagacinskaKartica.setPrometIzlazaKolicina(0);
            novaMagacinskaKartica.setPrometUlazaKolicina(novaMagacinskaKartica.getPocetnoStanjeKolicina());
            novaMagacinskaKartica.setArtikal(magacinskaKartica.getArtikal());
            novaMagacinskaKartica.setPoslovnaGodina(poslovnaGodinaRepository.findLatest());


            magacinskaKarticaRepository.save(novaMagacinskaKartica);

        }
    }

    public void magacinskaKarticaIzlaz(StavkaFakture stavkaFakture){

        MagacinskaKartica magacinskaKartica = magacinskaKarticaRepository.findFirstByArtikal(stavkaFakture.getArtikal().getId());
        Double cena = artikalService.getCenaArtiklaOsnovica(stavkaFakture.getArtikal()) * stavkaFakture.getKolicina();
        magacinskaKartica.setPrometIzlazaKolicina(magacinskaKartica.getPrometIzlazaKolicina() + stavkaFakture.getKolicina());
        magacinskaKartica.setPrometIzlazaVrednost(magacinskaKartica.getPrometIzlazaVrednost() + cena);
        magacinskaKartica.setUkupnaVrednost(magacinskaKartica.getUkupnaVrednost() + cena);
        magacinskaKartica.setProsecnaCena(getProsecnaCena(artikalService.getCenaArtiklaOsnovica(stavkaFakture.getArtikal()), magacinskaKartica));

        magacinskaKarticaRepository.save(magacinskaKartica);
        prometMagacinskeKarticeService.createIzlaznogPrometaMagacinskeKartice(stavkaFakture, magacinskaKartica);



    }

    public Double getProsecnaCena(Double ulaznaCena, MagacinskaKartica magacinskaKartica){
        if(magacinskaKartica.getProsecnaCena() == null){
            return ulaznaCena;
        }
        return (magacinskaKartica.getProsecnaCena() + ulaznaCena)/2;

    }
}
