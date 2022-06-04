package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Cenovnik;
import com.ftn.adriabike.model.StavkaCenovnika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Integer> {

    StavkaCenovnika findStavkaCenovnikaByArtikalAndCenovnik(Artikal artikal, Cenovnik cenovnik);
    List<StavkaCenovnika> findAllByCenovnik(Cenovnik cenovnik);

}
