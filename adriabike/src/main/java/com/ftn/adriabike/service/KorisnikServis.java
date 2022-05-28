package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Korisnik;
import com.ftn.adriabike.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikServis {

    @Autowired
    private KorisnikRepository korisnikRepository;


    public Korisnik findByUsername(String username) {
        Optional<Korisnik> user = korisnikRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }
}
