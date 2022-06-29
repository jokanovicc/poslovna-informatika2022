package com.ftn.adriabike.service;

import com.ftn.adriabike.model.*;
import com.ftn.adriabike.repository.*;
import com.ftn.adriabike.web.dto.ArtikalResponseDTO;
import com.ftn.adriabike.web.dto.ArtikliListboxDTO;
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

    @Autowired
    private PoreskaStopaRepository poreskaStopaRepository;

    public List<ArtikalResponseDTO> findAll() {
        List<Artikal> artikli = artikalRepository.getAll();
        List<ArtikalResponseDTO> artikliResponse = new ArrayList<ArtikalResponseDTO>();
        Cenovnik latestCenovnik = cenovnikRepository.findLatest();

        for (Artikal a : artikli) {
            MagacinskaKartica magacinskaKartica = magacinskaKarticaRepository.findFirstByArtikal(a.getId());
            if (magacinskaKartica.getPrometUlazaKolicina() > magacinskaKartica.getPrometIzlazaKolicina()) {

                ArtikalResponseDTO artikalResponseDTO = new ArtikalResponseDTO(a);
                StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findStavkaCenovnikaByArtikalAndCenovnik(a, latestCenovnik);
                System.out.println("######" + a);
                PoreskaStopa poreskaStopa = poreskaStopaRepository.findLatestPoreskaStopa(a.getPoreskaKategorija().getId());
                Double cenaSaPorezom = stavkaCenovnika.getCena() + ((stavkaCenovnika.getCena() * poreskaStopa.getProcenatPDV()) / 100);
                artikalResponseDTO.setUkupnaCena(cenaSaPorezom);
                artikliResponse.add(artikalResponseDTO);

            }
        }

        return artikliResponse;


    }

    public void updateArtikal(ArtikalResponseDTO artikliResponse, Integer artikalId){
        Artikal artikal = artikalRepository.findById(artikalId).orElse(null);

        artikal.setSlika(artikliResponse.getSlika());
        artikal.setPakovanje(artikliResponse.getPakovanje());
        artikal.setOpis(artikliResponse.getOpis());
        artikal.setNaziv(artikliResponse.getNaziv());

        artikalRepository.save(artikal);


    }

    public ArtikalResponseDTO getById(Integer artikalId){
        ArtikalResponseDTO artikal = new ArtikalResponseDTO(artikalRepository.findById(artikalId).orElse(null));
        artikal.setUkupnaCena(getCenaArtikla(artikalRepository.findById(artikalId).orElse(null)));

        return artikal;
    }

    public List<ArtikliListboxDTO> getForListbox(){

        List<ArtikliListboxDTO> artikli = new ArrayList<>();
        for(Artikal artikal: artikalRepository.findAll()){
            artikli.add(new ArtikliListboxDTO(artikal));
        }
        return artikli;
    }

    public Double getCenaArtikla(Artikal a){
        Cenovnik latestCenovnik = cenovnikRepository.findLatest();
        StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findStavkaCenovnikaByArtikalAndCenovnik(a, latestCenovnik);
        PoreskaStopa poreskaStopa = poreskaStopaRepository.findLatestPoreskaStopa(a.getPoreskaKategorija().getId());

        return stavkaCenovnika.getCena() + ((stavkaCenovnika.getCena() * poreskaStopa.getProcenatPDV()) / 100);
    }

    public Double getCenaArtiklaOsnovica(Artikal a){
        Cenovnik latestCenovnik = cenovnikRepository.findLatest();
        StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findStavkaCenovnikaByArtikalAndCenovnik(a, latestCenovnik);
        return stavkaCenovnika.getCena();
    }


    public void dobavljenjeNoveRobe(List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTOList){


        for(DobavljanjeNoveRobeDTO dobavljanjeNoveRobeDTO:dobavljanjeNoveRobeDTOList){

            if(dobavljanjeNoveRobeDTO.getArtikalId() == null){
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

            }else{
                Artikal artikal = artikalRepository.findById(dobavljanjeNoveRobeDTO.getArtikalId()).orElse(null);
                MagacinskaKartica magacinskaKartica = magacinskaKarticaRepository.findFirstByArtikal(artikal.getId());
                magacinskaKartica.setPrometUlazaKolicina(magacinskaKartica.getPrometUlazaKolicina() + dobavljanjeNoveRobeDTO.getKolicina());
                magacinskaKarticaRepository.save(magacinskaKartica);
            }


        }

        prijemnicaService.createPrijemnica(dobavljanjeNoveRobeDTOList);



    }


}
