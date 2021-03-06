package com.ftn.adriabike.service;

import com.ftn.adriabike.model.*;
import com.ftn.adriabike.repository.ArtikalRepository;
import com.ftn.adriabike.repository.PrometMagacinskeKarticeRepository;
import com.ftn.adriabike.web.dto.AnalyticDTO;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import com.ftn.adriabike.web.dto.PrometMagacinskeKarticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Service
public class PrometMagacinskeKarticeService {

    @Autowired
    private PrometMagacinskeKarticeRepository prometMagacinskeKarticeRepository;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private ArtikalRepository artikalRepository;



    public void createUlaznogPrometaMagacinskeKartice(DobavljanjeNoveRobeDTO dobavljanjeNoveRobeDTO, MagacinskaKartica magacinskaKartica){

        PrometMagacinskeKartice prometMagacinskeKartice = new PrometMagacinskeKartice();
        prometMagacinskeKartice.setDatum(new Date(Calendar.getInstance().getTime().getTime()));
        prometMagacinskeKartice.setKolicina(dobavljanjeNoveRobeDTO.getKolicina());

        if(dobavljanjeNoveRobeDTO.getArtikalId() == null){
            prometMagacinskeKartice.setCena(dobavljanjeNoveRobeDTO.getCena());
            prometMagacinskeKartice.setVrednost(dobavljanjeNoveRobeDTO.getKolicina() * dobavljanjeNoveRobeDTO.getCena());
        }else{
            prometMagacinskeKartice.setCena(artikalService.getCenaArtiklaOsnovica(artikalRepository.findById(dobavljanjeNoveRobeDTO.getArtikalId()).orElse(null)));
            prometMagacinskeKartice.setVrednost(dobavljanjeNoveRobeDTO.getKolicina() * prometMagacinskeKartice.getCena());
        }


        prometMagacinskeKartice.setSmer(Smer.ULAZ);
        prometMagacinskeKartice.setMagacinskaKartica(magacinskaKartica);

        prometMagacinskeKarticeRepository.save(prometMagacinskeKartice);


    }

    public void createIzlaznogPrometaMagacinskeKartice(StavkaFakture stavkaFakture, MagacinskaKartica magacinskaKartica){

        PrometMagacinskeKartice prometMagacinskeKartice = new PrometMagacinskeKartice();
        prometMagacinskeKartice.setDatum(new Date(Calendar.getInstance().getTime().getTime()));
        prometMagacinskeKartice.setKolicina(stavkaFakture.getKolicina());
        prometMagacinskeKartice.setCena(artikalService.getCenaArtiklaOsnovica(stavkaFakture.getArtikal()));
        prometMagacinskeKartice.setVrednost(stavkaFakture.getKolicina() * artikalService.getCenaArtiklaOsnovica(stavkaFakture.getArtikal()));
        prometMagacinskeKartice.setSmer(Smer.IZLAZ);

        prometMagacinskeKartice.setMagacinskaKartica(magacinskaKartica);

        prometMagacinskeKarticeRepository.save(prometMagacinskeKartice);


    }

    public List<PrometMagacinskeKarticeDTO> getAnalitika(AnalyticDTO analyticDTO){
        List<PrometMagacinskeKarticeDTO> prometMagacinskeKartice = new ArrayList<>();
        for(PrometMagacinskeKartice p: prometMagacinskeKarticeRepository.findBetweenDates(analyticDTO.getStartDate(), analyticDTO.getEndDate())){
            if(Objects.equals(analyticDTO.getSmer(), p.getSmer().toString())){
                prometMagacinskeKartice.add(new PrometMagacinskeKarticeDTO(p));
            }else if(Objects.equals(analyticDTO.getSmer(), "")){
                prometMagacinskeKartice.add(new PrometMagacinskeKarticeDTO(p));
            }
        }
        return prometMagacinskeKartice;
    }


    public List<PrometMagacinskeKarticeDTO> getAnalitikaByKartica(Date dateStart, Date dateEnd, Integer kartica){
        List<PrometMagacinskeKarticeDTO> prometMagacinskeKartice = new ArrayList<>();
        for(PrometMagacinskeKartice p: prometMagacinskeKarticeRepository.findBetweenDates(dateStart, dateEnd, kartica)){
            prometMagacinskeKartice.add(new PrometMagacinskeKarticeDTO(p));
        }
        return prometMagacinskeKartice;
    }



}
