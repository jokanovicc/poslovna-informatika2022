package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.IzlaznaFaktura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FakturaRepository extends JpaRepository<IzlaznaFaktura, Integer> {
}
