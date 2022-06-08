package com.ftn.adriabike.service;

import com.ftn.adriabike.model.*;
import com.ftn.adriabike.repository.*;
import com.ftn.adriabike.web.dto.ArtikalResponseDTO;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
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
    private PoreskaKategorijaRepository poreskaKategorijaRepository;

    @Autowired
    private StavkaCenovnikaRepository stavkaCenovnikaRepository;

    @Autowired
    private PrijemnicaService prijemnicaService;

    @Autowired
    private MagacinskaKarticaService magacinskaKarticaService;

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

    public Double getCenaArtikla(Artikal a){
        Cenovnik latestCenovnik = cenovnikRepository.findLatest();
        StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findStavkaCenovnikaByArtikalAndCenovnik(a, latestCenovnik);

        return stavkaCenovnika.getCena() + ((stavkaCenovnika.getCena() * a.getPoreskaKategorija().getPoreskaStopa().getProcenatPDV()) / 100);
    }

    public Double getCenaArtiklaOsnovica(Artikal a){
        Cenovnik latestCenovnik = cenovnikRepository.findLatest();
        StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findStavkaCenovnikaByArtikalAndCenovnik(a, latestCenovnik);
        return stavkaCenovnika.getCena();
    }


    public void dobavljenjeNoveRobe(List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTOList){


        for(DobavljanjeNoveRobeDTO dobavljanjeNoveRobeDTO:dobavljanjeNoveRobeDTOList){
            Artikal artikal = new Artikal();
            artikal.setNaziv(dobavljanjeNoveRobeDTO.getNaziv());
            artikal.setOpis(dobavljanjeNoveRobeDTO.getOpis());
            artikal.setSlika(dobavljanjeNoveRobeDTO.getSlika());
            artikal.setPakovanje(1);

            artikal.setPoreskaKategorija(poreskaKategorijaRepository.findById(dobavljanjeNoveRobeDTO.getPoreskaId()).orElse(null));

            Cenovnik latestCenovnik = cenovnikRepository.findLatest();
            StavkaCenovnika stavkaCenovnika = new StavkaCenovnika();
            stavkaCenovnika.setArtikal(artikal);
            stavkaCenovnika.setCena(dobavljanjeNoveRobeDTO.getCena());
            stavkaCenovnika.setCenovnik(latestCenovnik);

            artikalRepository.save(artikal);
            stavkaCenovnikaRepository.save(stavkaCenovnika);
            magacinskaKarticaService.createMagacinskaKartica(dobavljanjeNoveRobeDTO, artikal);
            dobavljanjeNoveRobeDTO.setArtikalId(artikal.getId());

        }

        prijemnicaService.createPrijemnica(dobavljanjeNoveRobeDTOList);



    }


}
