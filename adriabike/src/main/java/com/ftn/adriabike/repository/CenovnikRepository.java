package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CenovnikRepository extends JpaRepository<Cenovnik, Integer> {

    @Query(value = "select * from cenovnik where start in (select max(start) from cenovnik)", nativeQuery = true)
    Cenovnik findLatest();

    //gde se artikal poslednju put pojavljuje
    @Query(value = "select * from cenovnik c  where start in (select max(start) from cenovnik c where c.id in (select cenovnik_id from stavka_cenovnika where artikal_id = 5))", nativeQuery = true)
    Cenovnik findLatestByPocetnoStanje();


}
