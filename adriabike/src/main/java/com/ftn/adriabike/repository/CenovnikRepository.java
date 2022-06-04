package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CenovnikRepository extends JpaRepository<Cenovnik, Integer> {

    @Query(value = "select * from cenovnik where start in (select max(start) from cenovnik)", nativeQuery = true)
    Cenovnik findLatest();


}
