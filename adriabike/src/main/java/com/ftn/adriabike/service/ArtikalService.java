package com.ftn.adriabike.service;

import com.ftn.adriabike.model.*;
import com.ftn.adriabike.repository.*;
import com.ftn.adriabike.web.dto.ArtikalResponseDTO;
import com.ftn.adriabike.web.dto.ArtikliListboxDTO;
import com.ftn.adriabike.web.dto.ArtikliPagingResult;
import com.ftn.adriabike.web.dto.DobavljanjeNoveRobeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private PrometMagacinskeKarticeService prometMagacinskeKarticeService;

    public ArtikliPagingResult findAll(Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo,3);
        Page<Artikal> artikli = artikalRepository.getAll(paging);
        List<ArtikalResponseDTO> artikliResponse = new ArrayList<ArtikalResponseDTO>();

        Cenovnik latestCenovnik = cenovnikRepository.findLatest();
        for (Artikal a : artikli.getContent()) {
            MagacinskaKartica magacinskaKartica = magacinskaKarticaRepository.findFirstByArtikal(a.getId());
            if (magacinskaKartica.getPrometUlazaKolicina() > magacinskaKartica.getPrometIzlazaKolicina()) {
                ArtikalResponseDTO artikalResponseDTO = new ArtikalResponseDTO(a);
                StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findStavkaCenovnikaByArtikalAndCenovnik(a, latestCenovnik);
                PoreskaStopa poreskaStopa = poreskaStopaRepository.findLatestPoreskaStopa(a.getPoreskaKategorija().getId());
                Double cenaSaPorezom = stavkaCenovnika.getCena() + ((stavkaCenovnika.getCena() * poreskaStopa.getProcenatPDV()) / 100);
                artikalResponseDTO.setUkupnaCena(cenaSaPorezom);

                artikliResponse.add(artikalResponseDTO);
            }
        }
        ArtikliPagingResult artikliPaging = new ArtikliPagingResult();
        artikliPaging.setArtikli(artikliResponse);
        artikliPaging.setPagesCount(artikli.getTotalPages());

        return artikliPaging;

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
        for(Artikal artikal: artikalRepository.getAll()){
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

    public Double getCenaPocetnoStanje(Artikal a){
        Cenovnik cenovnik = cenovnikRepository.findLatestByPocetnoStanje();
        StavkaCenovnika stavkaCenovnika = stavkaCenovnikaRepository.findStavkaCenovnikaByArtikalAndCenovnik(a, cenovnik);
        return stavkaCenovnika.getCena();
    }


    public void dobavljenjeNoveRobe(List<DobavljanjeNoveRobeDTO> dobavljanjeNoveRobeDTOList){
        for(DobavljanjeNoveRobeDTO dobavljanjeNoveRobeDTO:dobavljanjeNoveRobeDTOList){
            if(dobavljanjeNoveRobeDTO.getArtikalId() == null){
                Artikal artikal = createArtikal(dobavljanjeNoveRobeDTO);
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
                magacinskaKartica.setPrometUlazaVrednost(magacinskaKartica.getPrometUlazaVrednost() + (dobavljanjeNoveRobeDTO.getKolicina() * getCenaArtiklaOsnovica(artikal)));
                prometMagacinskeKarticeService.createUlaznogPrometaMagacinskeKartice(dobavljanjeNoveRobeDTO, magacinskaKartica);

                magacinskaKarticaRepository.save(magacinskaKartica);
            }
        }

        prijemnicaService.createPrijemnica(dobavljanjeNoveRobeDTOList);

    }

    public Artikal createArtikal(DobavljanjeNoveRobeDTO dobavljanjeNoveRobeDTO){
        Artikal artikal = new Artikal();
        artikal.setNaziv(dobavljanjeNoveRobeDTO.getNaziv());
        artikal.setOpis(dobavljanjeNoveRobeDTO.getOpis());
        artikal.setSlika(dobavljanjeNoveRobeDTO.getSlika());
        artikal.setPakovanje(1);
        artikal.setPoreskaKategorija(poreskaKategorijaRepository.findById(dobavljanjeNoveRobeDTO.getPoreskaId()).orElse(null));

        return artikal;
    }


}
