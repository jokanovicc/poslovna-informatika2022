package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.PoslovnaGodina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina, Integer> {

    @Query(value="select * from poslovna_godina where godina=year(curdate())", nativeQuery = true)
    PoslovnaGodina findLatest();
}
