package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.PrometMagacinskeKartice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface PrometMagacinskeKarticeRepository extends JpaRepository<PrometMagacinskeKartice, Integer> {

    @Query(value="select * from promet_magacinske_kartice where datum >= ?1 and datum <= ?2 group by id", nativeQuery = true)
    List<PrometMagacinskeKartice> findBetweenDates(Date dateStart, Date dateEnd);


    @Query(value="select * from promet_magacinske_kartice where datum >= ?1 and datum <= ?2 and magacinska_kartica_id = ?3 group by id", nativeQuery = true)
    List<PrometMagacinskeKartice> findBetweenDates(Date dateStart, Date dateEnd, Integer magacinskaKartica);


}
