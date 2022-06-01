package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Cenovnik;
import com.ftn.adriabike.model.StavkaCenovnika;
import com.ftn.adriabike.repository.CenovnikRepository;
import com.ftn.adriabike.repository.StavkaCenovnikaRepository;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CenovnikService {

    @Autowired
    private StavkaCenovnikaRepository stavkaCenovnikaRepository;

    @Autowired
    private CenovnikRepository cenovnikRepository;


}
