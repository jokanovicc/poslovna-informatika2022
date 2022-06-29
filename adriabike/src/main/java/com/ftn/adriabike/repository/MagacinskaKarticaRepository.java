package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.MagacinskaKartica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MagacinskaKarticaRepository extends JpaRepository<MagacinskaKartica, Integer> {

    @Query(value = "select * from magacinska_kartica where artikal_id = ?1 and poslovna_godina_id in (select id from poslovna_godina where godina = year(curdate()))", nativeQuery = true)
    MagacinskaKartica findFirstByArtikal(Integer artikalId);

    @Query(value = "select * from magacinska_kartica where magacin_id = ?1 and poslovna_godina_id in (select id from poslovna_godina where godina = year(curdate()))", nativeQuery = true)
    List<MagacinskaKartica> findAllByMagacin_Id(Integer id);

    MagacinskaKartica findFirstByArtikal_Id(Integer artikalId);

    @Query(value="SELECT * FROM magacinska_kartica  where poslovna_godina_id not in (select id from poslovna_godina where godina = year(curdate())) and artikal_id not in (SELECT artikal_id FROM magacinska_kartica  where poslovna_godina_id in (select id from poslovna_godina where godina = year(curdate())))", nativeQuery = true)
    List<MagacinskaKartica> findProslogodinjeKartice();


}
