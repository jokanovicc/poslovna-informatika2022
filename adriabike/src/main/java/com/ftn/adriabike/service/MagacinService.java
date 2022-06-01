package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Magacin;
import com.ftn.adriabike.model.MagacinskaKartica;
import com.ftn.adriabike.repository.ArtikalRepository;
import com.ftn.adriabike.repository.MagacinRepository;
import com.ftn.adriabike.repository.MagacinskaKarticaRepository;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import com.ftn.adriabike.web.dto.MagacinResponseDTO;
import com.ftn.adriabike.web.dto.MagacinskaKarticaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MagacinService {

    @Autowired
    private MagacinRepository magacinRepository;

    @Autowired
    private MagacinskaKarticaRepository magacinskaKarticaRepository;

    @Autowired
    private ArtikalRepository artikalRepository;


    public List<MagacinResponseDTO> getAll(){
        ArrayList<MagacinResponseDTO> magacinList = new ArrayList<>();
        for(Magacin m: magacinRepository.findAll()){
            magacinList.add(new MagacinResponseDTO(m));
        }

        return magacinList;
    }

    public List<MagacinskaKarticaResponse> getMagacinskeKarticeByMagacin(Integer magacinId){
        ArrayList<MagacinskaKarticaResponse> magacinskaKarticaResponse = new ArrayList<>();
        for(MagacinskaKartica m : magacinskaKarticaRepository.findAllByMagacin_Id(magacinId)){
            magacinskaKarticaResponse.add(new MagacinskaKarticaResponse(m));
        }

        return magacinskaKarticaResponse;

    }

    public MagacinskaKarticaResponse findOne(Integer id){
        return new MagacinskaKarticaResponse(magacinskaKarticaRepository.findById(id).orElse(null));
    }


}
