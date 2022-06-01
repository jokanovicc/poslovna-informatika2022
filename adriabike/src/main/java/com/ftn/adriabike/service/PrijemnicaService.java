package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Prijemnica;
import com.ftn.adriabike.model.StavkaPrometnogDokumenta;
import com.ftn.adriabike.repository.ArtikalRepository;
import com.ftn.adriabike.repository.MagacinRepository;
import com.ftn.adriabike.repository.PrijemnicaRepository;
import com.ftn.adriabike.repository.StavkaPrometnogDokumentaRepository;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PrijemnicaService {

    @Autowired
    private MagacinRepository magacinRepository;

    @Autowired private PrijemnicaRepository prijemnicaRepository;
    @Autowired private StavkaPrometnogDokumentaRepository stavkaPrometnogDokumentaRepository;


    @Autowired
    private ArtikalRepository artikalRepository;

    void createPrijemnica(List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTO){

        Prijemnica prijemnica = new Prijemnica();
        prijemnica.setBroj((int) Math.floor(Math.random()*(50000-20000+1)+1000));
        prijemnica.setDatumDokumenta(new Date(Calendar.getInstance().getTime().getTime()));
        prijemnica.setDatumKnjizenja(prijemnica.getDatumDokumenta());
        prijemnica.setMagacin(magacinRepository.findById(1).orElse(null));

        prijemnicaRepository.save(prijemnica);

        createStavkaPrometnogDokumenta(dobavljanjeNoveRobeDTO, prijemnica);


    }

    private void createStavkaPrometnogDokumenta(List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTOList, Prijemnica prijemnica) {

        for(DobavljanjeNoveRobeDTO dobavljenjeNoveRobe: dobavljanjeNoveRobeDTOList){

            StavkaPrometnogDokumenta stavkaPrometnogDokumenta = new StavkaPrometnogDokumenta();
            stavkaPrometnogDokumenta.setPrijemnica(prijemnica);
            stavkaPrometnogDokumenta.setArtikal(artikalRepository.findById(dobavljenjeNoveRobe.getArtikalId()).orElse(null));
            stavkaPrometnogDokumenta.setKolicina(dobavljenjeNoveRobe.getKolicina());
            stavkaPrometnogDokumenta.setCena(dobavljenjeNoveRobe.getCena());
            stavkaPrometnogDokumenta.setVrednost(dobavljenjeNoveRobe.getKolicina() * dobavljenjeNoveRobe.getCena());
            stavkaPrometnogDokumentaRepository.save(stavkaPrometnogDokumenta);

        }

    }

}
