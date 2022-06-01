package com.ftn.adriabike.service;

import com.ftn.adriabike.model.MagacinskaKartica;
import com.ftn.adriabike.model.PrometMagacinskeKartice;
import com.ftn.adriabike.model.Smer;
import com.ftn.adriabike.repository.PrometMagacinskeKarticeRepository;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Calendar;

@Service
public class PrometMagacinskeKarticeService {

    @Autowired
    private PrometMagacinskeKarticeRepository prometMagacinskeKarticeRepository;



    public void createUlaznogPrometaMagacinskeKartice(DobavljanjeNoveRobeDTO dobavljanjeNoveRobeDTO, MagacinskaKartica magacinskaKartica){

        PrometMagacinskeKartice prometMagacinskeKartice = new PrometMagacinskeKartice();
        prometMagacinskeKartice.setDatum(new Date(Calendar.getInstance().getTime().getTime()));
        prometMagacinskeKartice.setKolicina(dobavljanjeNoveRobeDTO.getKolicina());
        prometMagacinskeKartice.setCena(dobavljanjeNoveRobeDTO.getCena());
        prometMagacinskeKartice.setVrednost(dobavljanjeNoveRobeDTO.getKolicina() * dobavljanjeNoveRobeDTO.getCena());
        prometMagacinskeKartice.setSmer(Smer.ULAZ);

        prometMagacinskeKartice.setMagacinskaKartica(magacinskaKartica);

        prometMagacinskeKarticeRepository.save(prometMagacinskeKartice);


    }

}