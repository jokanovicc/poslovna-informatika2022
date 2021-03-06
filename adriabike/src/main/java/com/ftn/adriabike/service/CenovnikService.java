package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Cenovnik;
import com.ftn.adriabike.model.StavkaCenovnika;
import com.ftn.adriabike.repository.CenovnikRepository;
import com.ftn.adriabike.repository.PreduzeceRepository;
import com.ftn.adriabike.repository.StavkaCenovnikaRepository;
import com.ftn.adriabike.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CenovnikService {

    @Autowired
    private StavkaCenovnikaRepository stavkaCenovnikaRepository;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    @Autowired
    private PreduzeceRepository preduzeceRepository;


    public CenovniciPagingResult getAllCenovnici(Integer pageNo){
        Pageable paging = PageRequest.of(pageNo,1, Sort.by("start").descending());
        Page<Cenovnik> cenovnici = cenovnikRepository.findAll(paging);
        List<CenovnikDTO> cenovniciDTO = new ArrayList<CenovnikDTO>();

        for(Cenovnik c: cenovnici.getContent()){
            CenovnikDTO cenovnikDTO = new CenovnikDTO(c);
            List<StavkaCenovnika> stavke = stavkaCenovnikaRepository.findAllByCenovnik(c);
            for(StavkaCenovnika stavka: stavke){
                cenovnikDTO.getStavkeCenovnikaDTO().add(new StavkeCenovnikaDTO(stavka));
            }
            cenovniciDTO.add(cenovnikDTO);
        }

        CenovniciPagingResult cenovniciPagingResult = new CenovniciPagingResult();
        cenovniciPagingResult.setCenovnici(cenovniciDTO);
        cenovniciPagingResult.setPagesCount(cenovnici.getTotalPages());

        return cenovniciPagingResult;

    }

    public CenovnikDTO getById(Integer cenovnikId){
        Cenovnik cenovnik = cenovnikRepository.findById(cenovnikId).orElse(null);
        CenovnikDTO cenovnikDTO = new CenovnikDTO(cenovnik);
        List<StavkaCenovnika> stavke = stavkaCenovnikaRepository.findAllByCenovnik(cenovnik);

        for(StavkaCenovnika stavka: stavke){
            cenovnikDTO.getStavkeCenovnikaDTO().add(new StavkeCenovnikaDTO(stavka));
        }

        return cenovnikDTO;


    }

    public CenovnikDTO createNoviCenovnik(RastCenovnikaDTO rastCenovnikaDTO){
        Cenovnik cenovnik = cenovnikRepository.findLatest();
        List<StavkaCenovnika> stavka = stavkaCenovnikaRepository.findAllByCenovnik(cenovnik);

        Cenovnik noviCenovnik = new Cenovnik();
        noviCenovnik.setPreduzece(cenovnik.getPreduzece());
        noviCenovnik.setStart(rastCenovnikaDTO.getDatum());
        cenovnikRepository.save(noviCenovnik);

        for(StavkaCenovnika s : stavka){
            StavkaCenovnika novaStavka = new StavkaCenovnika();
            novaStavka.setCenovnik(noviCenovnik);
            novaStavka.setArtikal(s.getArtikal());

            if(rastCenovnikaDTO.isPoskupljenje()){
                novaStavka.setCena(s.getCena() + ((s.getCena() * rastCenovnikaDTO.getProcenat()) / 100));
            }else{
                novaStavka.setCena(s.getCena() - ((s.getCena() * rastCenovnikaDTO.getProcenat()) / 100));
            }
            stavkaCenovnikaRepository.save(novaStavka);

        }

        return new CenovnikDTO(noviCenovnik);

    }


    public void korigujCenu(KorigovanjeCeneDTO korigovanjeCene){
        StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findById(korigovanjeCene.getStavkaId()).orElse(null);
        stavkaCenovnika.setCena(korigovanjeCene.getNovaCena());

        stavkaCenovnikaRepository.save(stavkaCenovnika);
    }


    public void dodajPocetnoStanjeUCenovnik(Double cena, Artikal artikal){
        Cenovnik cenovnik = cenovnikRepository.findLatest();
        StavkaCenovnika stavkaCenovnika = new StavkaCenovnika();
        stavkaCenovnika.setCenovnik(cenovnik);
        stavkaCenovnika.setCena(cena);
        stavkaCenovnika.setArtikal(artikal);

        stavkaCenovnikaRepository.save(stavkaCenovnika);

    }



}
