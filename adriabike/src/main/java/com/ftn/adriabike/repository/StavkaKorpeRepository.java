package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Korpa;
import com.ftn.adriabike.model.StavkaKorpe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StavkaKorpeRepository extends JpaRepository<StavkaKorpe, Integer> {

    List<StavkaKorpe> findAllByKorpa(Korpa korpa);

    StavkaKorpe findByArtikalAndKorpa(Artikal artikal, Korpa korpa);

}
