package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Korisnik;
import com.ftn.adriabike.model.Roles;
import com.ftn.adriabike.repository.KorisnikRepository;
import com.ftn.adriabike.web.dto.KorisnikDTO;
import com.ftn.adriabike.web.dto.RegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class KorisnikService {

    @Autowired
    private KorisnikRepository korisnikRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Korisnik findByUsername(String username) {
        Optional<Korisnik> user = korisnikRepository.findFirstByUsername(username);
        if (!user.isEmpty()) {
            return user.get();
        }
        return null;
    }

    public KorisnikDTO findById(Integer id){
        return new KorisnikDTO(korisnikRepository.findById(id).orElse(null));
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

    public void register(RegistrationDTO registrationDTO){
        Korisnik korisnik = new Korisnik();

        korisnik.setIme(registrationDTO.getIme());
        korisnik.setPrezime(registrationDTO.getPrezime());
        korisnik.setAdresa(registrationDTO.getAdresa());
        korisnik.setBrojTelefona(registrationDTO.getBrojTelefona());
        korisnik.setUsername(registrationDTO.getUsername());
        korisnik.setEmail(registrationDTO.getEmail());
        korisnik.setLozinka(passwordEncoder.encode(registrationDTO.getLozinka()));
        korisnik.setRoles(Roles.KUPAC);

        korisnikRepository.save(korisnik);
    }
}
