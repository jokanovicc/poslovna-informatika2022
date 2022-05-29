package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Artikal;
import com.ftn.adriabike.model.MagacinskaKartica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MagacinskaKarticaRepository extends JpaRepository<MagacinskaKartica, Integer> {

    MagacinskaKartica findFirstByArtikal(Artikal artikal);

    List<MagacinskaKartica> findAllByMagacin_Id(Integer id);

    MagacinskaKartica findFirstByArtikal_Id(Integer artikalId);
}
