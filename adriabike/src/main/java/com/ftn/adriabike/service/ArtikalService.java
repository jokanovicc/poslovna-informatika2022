package com.ftn.adriabike.service;

import com.ftn.adriabike.model.*;
import com.ftn.adriabike.repository.*;
import com.ftn.adriabike.web.dto.ArtikalResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;
    @Autowired
    private MagacinskaKarticaRepository magacinskaKarticaRepository;

    @Autowired
    private CenovnikRepository cenovnikRepository;

    @Autowired
    private StavkaCenovnikaRepository stavkaCenovnikaRepository;


    public List<ArtikalResponseDTO> findAll() {
        List<Artikal> artikli = artikalRepository.findAll();
        List<ArtikalResponseDTO> artikliResponse = new ArrayList<ArtikalResponseDTO>();
        Cenovnik latestCenovnik = cenovnikRepository.findLatest();

        for (Artikal a : artikli) {
            MagacinskaKartica magacinskaKartica = magacinskaKarticaRepository.findFirstByArtikal(a);
            if (magacinskaKartica.getPrometUlazaKolicina() > magacinskaKartica.getPrometIzlazaKolicina()) {

                ArtikalResponseDTO artikalResponseDTO = new ArtikalResponseDTO(a);
                StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findStavkaCenovnikaByArtikalAndCenovnik(a, latestCenovnik);
                Double cenaSaPorezom = stavkaCenovnika.getCena() + ((stavkaCenovnika.getCena() * a.getPoreskaKategorija().getPoreskaStopa().getProcenatPDV()) / 100);
                artikalResponseDTO.setUkupnaCena(cenaSaPorezom);
                artikliResponse.add(artikalResponseDTO);

            }
        }

        return artikliResponse;


    }


}
