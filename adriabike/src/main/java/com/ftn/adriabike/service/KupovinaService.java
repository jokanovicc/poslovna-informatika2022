package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Korisnik;
import com.ftn.adriabike.model.Korpa;
import com.ftn.adriabike.model.StavkaKorpe;
import com.ftn.adriabike.repository.ArtikalRepository;
import com.ftn.adriabike.repository.KorpaRepository;
import com.ftn.adriabike.repository.StavkaKorpeRepository;
import com.ftn.adriabike.web.dto.DodajUKorpuDTO;
import com.ftn.adriabike.web.dto.KorpaResponseDTO;
import com.ftn.adriabike.web.dto.StavkaKorpeResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KupovinaService {

    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private StavkaKorpeRepository stavkaKorpeRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private ArtikalService artikalService;



    public Korpa findKorpaByUser(Authentication authentication){
        Korisnik current = korisnikService.getUser(authentication);
        Korpa korpa = korpaRepository.findFirstByKupac(current);

        if(korpa == null){
            korpa = new Korpa();
            korpa.setKupac(current);
            korpaRepository.save(korpa);
            return korpa;
        }

        return korpa;
    }

    public StavkaKorpe findStavkaKorpe(Artikal artikal, Korpa k){
        StavkaKorpe stavkaKorpe = stavkaKorpeRepository.findByArtikalAndKorpa(artikal, k);

        if(stavkaKorpe == null){
            stavkaKorpe = new StavkaKorpe();
            stavkaKorpe.setArtikal(artikal);
            stavkaKorpe.setKorpa(k);
            stavkaKorpeRepository.save(stavkaKorpe);

            return stavkaKorpe;
        }

        return stavkaKorpe;
    }

    public void dodajUKorpu(DodajUKorpuDTO dodajUKorpu, Authentication authentication){
        Korpa korpa = findKorpaByUser(authentication);
        Artikal artikal = artikalRepository.findById(dodajUKorpu.getArtikalId()).orElse(null);
        StavkaKorpe stavkaKorpe = findStavkaKorpe(artikal, korpa);

        if(stavkaKorpe.getKolicina() != null){
            stavkaKorpe.setKolicina(stavkaKorpe.getKolicina() + dodajUKorpu.getKolicina());
        }else{
            stavkaKorpe.setKolicina(dodajUKorpu.getKolicina());
        }

        stavkaKorpeRepository.save(stavkaKorpe);
    }

    public KorpaResponseDTO getKorpa(Authentication authentication){
        Korpa korpa = findKorpaByUser(authentication);
        KorpaResponseDTO response = new KorpaResponseDTO();
        response.setId(korpa.getId());

        for(StavkaKorpe stavkaKorpe : stavkaKorpeRepository.findAllByKorpa(korpa)){
            StavkaKorpeResponseDTO stavkaKorpeResponse = new StavkaKorpeResponseDTO(stavkaKorpe);
            stavkaKorpeResponse.setCena(artikalService.getCenaArtikla(stavkaKorpe.getArtikal()));

            response.getStavkaKorpeResponseDTOs().add(stavkaKorpeResponse);

        }

        return response;
    }

    public void removeFromKorpa(Integer stavkaKorpeId){
        stavkaKorpeRepository.delete(stavkaKorpeRepository.findById(stavkaKorpeId).orElse(null));
    }




}
