package com.ftn.adriabike.repository;

import com.ftn.adriabike.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {

    Optional<Korisnik> findFirstByUsername(String username);
}
