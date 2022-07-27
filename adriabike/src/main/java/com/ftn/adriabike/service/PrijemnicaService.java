package com.ftn.adriabike.service;

import com.ftn.adriabike.model.*;
import com.ftn.adriabike.repository.ArtikalRepository;
import com.ftn.adriabike.repository.MagacinRepository;
import com.ftn.adriabike.repository.PrijemnicaRepository;
import com.ftn.adriabike.repository.StavkaPrometnogDokumentaRepository;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import com.ftn.adriabike.web.dto.PrijemnicaPagingResponseDTO;
import com.ftn.adriabike.web.dto.PrijemnicaResponseDTO;
import com.ftn.adriabike.web.dto.StavkaPrometnogDokumentaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private PoreskaKategorijaService poreskaKategorijaService;

    @Autowired ArtikalService artikalService;

    public void createPrijemnica(List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTO){

        Prijemnica prijemnica = new Prijemnica();
        prijemnica.setBroj((int) Math.floor(Math.random()*(50000-20000+1)+1000));
        prijemnica.setDatumDokumenta(new Date(Calendar.getInstance().getTime().getTime()));
        prijemnica.setDatumKnjizenja(prijemnica.getDatumDokumenta());
        prijemnica.setMagacin(magacinRepository.findById(getMagacinFromDobavljanjeNoveRobe(dobavljanjeNoveRobeDTO)).orElse(null));

        prijemnicaRepository.save(prijemnica);

        createStavkaPrometnogDokumenta(dobavljanjeNoveRobeDTO, prijemnica);


    }

    public Integer getMagacinFromDobavljanjeNoveRobe(List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTO){
        Integer id = null;
        for(DobavljanjeNoveRobeDTO dt: dobavljanjeNoveRobeDTO){
            id = dt.getMagacinId();

        }
        return id;
    }

    private void createStavkaPrometnogDokumenta(List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTOList, Prijemnica prijemnica) {

        for(DobavljanjeNoveRobeDTO dobavljenjeNoveRobe: dobavljanjeNoveRobeDTOList){
            StavkaPrometnogDokumenta stavkaPrometnogDokumenta = new StavkaPrometnogDokumenta();
            Artikal artikal = artikalRepository.findById(dobavljenjeNoveRobe.getArtikalId()).orElse(null);
            stavkaPrometnogDokumenta.setPrijemnica(prijemnica);
            stavkaPrometnogDokumenta.setArtikal(artikal);
            stavkaPrometnogDokumenta.setKolicina(dobavljenjeNoveRobe.getKolicina());
            if(dobavljenjeNoveRobe.getCena() == 0){
                stavkaPrometnogDokumenta.setCena(artikalService.getCenaArtiklaOsnovica(artikal));
                stavkaPrometnogDokumenta.setVrednost(dobavljenjeNoveRobe.getKolicina() * artikalService.getCenaArtiklaOsnovica(artikal));
            }else{
                stavkaPrometnogDokumenta.setCena(dobavljenjeNoveRobe.getCena());
                stavkaPrometnogDokumenta.setVrednost(dobavljenjeNoveRobe.getKolicina() * dobavljenjeNoveRobe.getCena());
            }
            stavkaPrometnogDokumentaRepository.save(stavkaPrometnogDokumenta);

        }

    }

    public PrijemnicaResponseDTO getDetaljnaPrijemnicaById(Integer idPrijemnice){
        Prijemnica prijemnica = prijemnicaRepository.findById(idPrijemnice).orElse(null);

        PrijemnicaResponseDTO prijemnicaResponseDTO = new PrijemnicaResponseDTO(prijemnica);

        for(StavkaPrometnogDokumenta stavka: stavkaPrometnogDokumentaRepository.findAllByPrijemnica(prijemnica)){
            StavkaPrometnogDokumentaDTO stavkaPrometnogDokumenta = new StavkaPrometnogDokumentaDTO(stavka);
            stavkaPrometnogDokumenta.setPoreskaStopa(poreskaKategorijaService.getPoreskaStopa(stavka.getArtikal()));
            prijemnicaResponseDTO.getStavkaPrometnogDokumenta().add(stavkaPrometnogDokumenta);
        }

        return prijemnicaResponseDTO;
    }

    public PrijemnicaPagingResponseDTO getAllDetaljnaPrijemnica(Integer pageNo){
        //List<Prijemnica> prijemnice = prijemnicaRepository.findAll();
        Pageable paging = PageRequest.of(pageNo,1);

        Page<Prijemnica> prijemnice = prijemnicaRepository.findAll(paging);
        List<PrijemnicaResponseDTO> prijemnicaResponseDTO = new ArrayList<>();

        for(Prijemnica prijemnica : prijemnice.getContent()){
            PrijemnicaResponseDTO response = new PrijemnicaResponseDTO(prijemnica);
            prijemnicaResponseDTO.add(response);

            for(StavkaPrometnogDokumenta stavka: stavkaPrometnogDokumentaRepository.findAll()){
                StavkaPrometnogDokumentaDTO stavkaPrometnogDokumenta = new StavkaPrometnogDokumentaDTO(stavka);
                stavkaPrometnogDokumenta.setPoreskaStopa(poreskaKategorijaService.getPoreskaStopa(stavka.getArtikal()));
                response.getStavkaPrometnogDokumenta().add(stavkaPrometnogDokumenta);
            }

        }

        PrijemnicaPagingResponseDTO prijemnicePaging = new PrijemnicaPagingResponseDTO();
        prijemnicePaging.setPrijemnice(prijemnicaResponseDTO);
        prijemnicePaging.setPagesCount(prijemnice.getTotalPages());

        return prijemnicePaging;
    }

}
