package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.IzlaznaFaktura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FakturaRepository extends JpaRepository<IzlaznaFaktura, Integer> {

    @Query(value = "select * from izlazna_faktura where status_fakture = ?1", nativeQuery=true)
    List<IzlaznaFaktura> findByStatusFakture(String status);

    @Query(value = "select * from izlazna_faktura where kupac_id = ?1 order by datum_izdavanja asc", nativeQuery = true)
    List<IzlaznaFaktura> findByKorisnik(Integer idKorisnika);
}
