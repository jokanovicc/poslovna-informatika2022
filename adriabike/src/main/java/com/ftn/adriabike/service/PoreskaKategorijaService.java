package com.ftn.adriabike.service;

import com.ftn.adriabike.model.PoreskaKategorija;
import com.ftn.adriabike.repository.PoreskaKategorijaRepository;
import com.ftn.adriabike.web.dto.PoreskaKategorijaResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoreskaKategorijaService {

    @Autowired
    private PoreskaKategorijaRepository poreskaKategorijaRepository;


    public List<PoreskaKategorijaResponseDTO> getAll(){
        List<PoreskaKategorijaResponseDTO> poreskaKategorijaResponseDTOS = new ArrayList<>();

        for(PoreskaKategorija pk : poreskaKategorijaRepository.findAll()){
            poreskaKategorijaResponseDTOS.add(new PoreskaKategorijaResponseDTO(pk));
        }

        return poreskaKategorijaResponseDTOS;
    }
}
