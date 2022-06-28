package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.PoreskaKategorija;
import com.ftn.adriabike.model.PoreskaStopa;
import com.ftn.adriabike.repository.PoreskaKategorijaRepository;
import com.ftn.adriabike.repository.PoreskaStopaRepository;
import com.ftn.adriabike.web.dto.PoreskaKategorijaDTO;
import com.ftn.adriabike.web.dto.PoreskaKategorijaResponseDTO;
import com.ftn.adriabike.web.dto.PoreskaStopaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PoreskaKategorijaService {

    @Autowired
    private PoreskaKategorijaRepository poreskaKategorijaRepository;

    @Autowired
    private PoreskaStopaRepository poreskaStopaRepository;


    public List<PoreskaKategorijaResponseDTO> getAll(){
        List<PoreskaKategorijaResponseDTO> poreskaKategorijaResponseDTOS = new ArrayList<>();

        for(PoreskaKategorija pk : poreskaKategorijaRepository.findAll()){
            poreskaKategorijaResponseDTOS.add(new PoreskaKategorijaResponseDTO(pk));
        }

        return poreskaKategorijaResponseDTOS;
    }

    public Integer getPoreskaStopa(Artikal a){
        PoreskaStopa poreskaStopa = poreskaStopaRepository.findLatestPoreskaStopa(a.getPoreskaKategorija().getId());
        return poreskaStopa.getProcenatPDV();
    }

    public PoreskaKategorijaDTO createPoreskaKategorija(String naziv){
        PoreskaKategorija poreskaKategorija = new PoreskaKategorija();
        poreskaKategorija.setNaziv(naziv);
        poreskaKategorijaRepository.save(poreskaKategorija);

        return new PoreskaKategorijaDTO(poreskaKategorija);

    }

    public void createPoreskaStopa(Integer poreskaKategorijaId, PoreskaStopaDTO poreskaStopa){
        PoreskaStopa ps = new PoreskaStopa();
        System.out.println(poreskaStopa);
        ps.setProcenatPDV(poreskaStopa.getProcenatPDV());
        ps.setDatumStupanja(poreskaStopa.getDatumStupanja());
        ps.setPoreskaKategorija(poreskaKategorijaRepository.findById(poreskaKategorijaId).orElse(null));

        poreskaStopaRepository.save(ps);
    }

    public List<PoreskaKategorijaDTO> findAllPoreskaKategorija(){
        List<PoreskaKategorijaDTO> poreskaKategorijaDTOList = new ArrayList<>();
        for(PoreskaKategorija poreskaKategorija: poreskaKategorijaRepository.findAll()){
            PoreskaKategorijaDTO poreskaKategorijaDTO = new PoreskaKategorijaDTO(poreskaKategorija);

            for(PoreskaStopa ps: poreskaStopaRepository.findAll()){
                PoreskaStopaDTO poreskaStopa = new PoreskaStopaDTO(ps);
                if(ps.getPoreskaKategorija().equals(poreskaKategorija)){
                    poreskaKategorijaDTO.getPoreskaStopaDTO().add(poreskaStopa);

                }
            }

            poreskaKategorijaDTOList.add(poreskaKategorijaDTO);
        }

        return poreskaKategorijaDTOList;

    }


    public PoreskaKategorijaDTO getById(Integer id){
        PoreskaKategorijaDTO poreskaKategorija = new PoreskaKategorijaDTO(poreskaKategorijaRepository.findById(id).orElse(null));

            for(PoreskaStopa ps: poreskaStopaRepository.findAll()){
                PoreskaStopaDTO poreskaStopa = new PoreskaStopaDTO(ps);
                if(ps.getPoreskaKategorija().equals(poreskaKategorija)){
                    poreskaKategorija.getPoreskaStopaDTO().add(poreskaStopa);

                }
            }

            return poreskaKategorija;

    }

}
