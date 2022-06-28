package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.PoreskaStopa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PoreskaStopaRepository extends JpaRepository<PoreskaStopa, Integer> {

    @Query(value = "select * from poreska_stopa where poreska_kategorija_id = ?1 and datum_stupanja in (select max(datum_stupanja) from poreska_stopa where poreska_kategorija_id = ?1)", nativeQuery = true)
    PoreskaStopa findLatestPoreskaStopa(Integer poreskaId);


}
