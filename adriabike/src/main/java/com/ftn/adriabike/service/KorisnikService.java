package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Korisnik;
import com.ftn.adriabike.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;


    public Korisnik findByUsername(String username) {
        Optional<Korisnik> user = korisnikRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }


    public Korisnik getUser(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();

        Optional<Korisnik> user = korisnikRepository.findFirstByUsername(username);

        if(user.isEmpty()) {
            return null;
        }
        return user.get();

    }
}
