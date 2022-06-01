package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Prijemnica;
import com.ftn.adriabike.model.StavkaPrometnogDokumenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StavkaPrometnogDokumentaRepository extends JpaRepository<StavkaPrometnogDokumenta, Integer> {

    List<StavkaPrometnogDokumenta> findAllByPrijemnica(Prijemnica prijemnica);
}
