package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.IzlaznaFaktura;
import com.ftn.adriabike.model.StavkaFakture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StavkaFaktureRepository extends JpaRepository<StavkaFakture, Integer> {


    @Query(value = "select sum(osnovica) from stavka_fakture where izlazna_faktura_id = ?1", nativeQuery = true)
    Double getUkupnaOsnovica(Integer fakturaId);

    @Query(value = "select sum(iznospdv) from stavka_fakture where izlazna_faktura_id = ?1", nativeQuery = true)
    Double getUkupnoPDV(Integer fakturaId);


    @Query(value = "select sum(ukupno) from stavka_fakture where izlazna_faktura_id = ?1", nativeQuery = true)
    Double getUkupanIznos(Integer fakturaId);

    List<StavkaFakture> findAllByIzlaznaFaktura(IzlaznaFaktura izlaznaFaktura);


}
