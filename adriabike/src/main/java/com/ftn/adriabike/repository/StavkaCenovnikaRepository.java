package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Cenovnik;
import com.ftn.adriabike.model.StavkaCenovnika;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Integer> {

    StavkaCenovnika findStavkaCenovnikaByArtikalAndCenovnik(Artikal artikal, Cenovnik cenovnik);
}
