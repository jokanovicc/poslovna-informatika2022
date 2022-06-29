package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArtikalRepository extends JpaRepository<Artikal, Integer> {

    @Query(value = "select * from artikal a where a.id in (select artikal_id from magacinska_kartica where poslovna_godina_id in (select id from poslovna_godina where godina = year(curdate())))", nativeQuery = true)
    List<Artikal> getAll();
}
