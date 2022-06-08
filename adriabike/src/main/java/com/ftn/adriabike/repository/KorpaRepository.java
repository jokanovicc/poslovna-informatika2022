package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Korisnik;
import com.ftn.adriabike.model.Korpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorpaRepository extends JpaRepository<Korpa, Integer> {

    Korpa findFirstByKupac(Korisnik kupac);

}
