package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.MagacinskaKartica;
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

    public Double getProsecnaCena(Double ulaznaCena, MagacinskaKartica magacinskaKartica){
        if(magacinskaKartica.getProsecnaCena() == null){
            return ulaznaCena;
        }
        return (magacinskaKartica.getProsecnaCena() + ulaznaCena)/2;

    }
}
