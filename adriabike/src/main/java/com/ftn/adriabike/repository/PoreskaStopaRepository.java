package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.PoreskaStopa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PoreskaStopaRepository extends JpaRepository<PoreskaStopa, Integer> {

    @Query(value = "select * from poreska_stopa where datum_stupanja in (select min(datum_stupanja) from poreska_stopa)", nativeQuery = true)
    PoreskaStopa findLatestPoreskaStopa();


}
