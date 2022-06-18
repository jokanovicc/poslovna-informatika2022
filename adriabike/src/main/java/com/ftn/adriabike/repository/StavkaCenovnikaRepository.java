package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.Cenovnik;
import com.ftn.adriabike.model.StavkaCenovnika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StavkaCenovnikaRepository extends JpaRepository<StavkaCenovnika, Integer> {

    StavkaCenovnika findStavkaCenovnikaByArtikalAndCenovnik(Artikal artikal, Cenovnik cenovnik);
    @Query(value = "select cena from stavka_cenovnika where cenovnik_id =?1 and artikal_id = ?2", nativeQuery = true)
    Double findCenaByArtikalICenovnik(Integer cenovnik, Integer artikal);
    List<StavkaCenovnika> findAllByCenovnik(Cenovnik cenovnik);

}
