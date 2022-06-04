package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Cenovnik;
import com.ftn.adriabike.model.StavkaCenovnika;
import com.ftn.adriabike.repository.CenovnikRepository;
import com.ftn.adriabike.repository.StavkaCenovnikaRepository;
import com.ftn.adriabike.web.dto.CenovniciPagingResult;
import com.ftn.adriabike.web.dto.CenovnikDTO;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import com.ftn.adriabike.web.dto.StavkeCenovnikaDTO;
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



}
