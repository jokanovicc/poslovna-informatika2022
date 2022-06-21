package com.ftn.adriabike.service;

import com.ftn.adriabike.model.Korisnik;
import com.ftn.adriabike.model.Roles;
import com.ftn.adriabike.repository.KorisnikRepository;
import com.ftn.adriabike.web.dto.KorisnikDTO;
import com.ftn.adriabike.web.dto.UserInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    public void register(UserInfoDTO userInfoDTO){
        Korisnik korisnik = new Korisnik();

        korisnik.setIme(userInfoDTO.getIme());
        korisnik.setPrezime(userInfoDTO.getPrezime());
        korisnik.setAdresa(userInfoDTO.getAdresa());
        korisnik.setBrojTelefona(userInfoDTO.getBrojTelefona());
        korisnik.setUsername(userInfoDTO.getUsername());
        korisnik.setEmail(userInfoDTO.getEmail());
        korisnik.setLozinka(passwordEncoder.encode(userInfoDTO.getLozinka()));
        korisnik.setRoles(Roles.KUPAC);

        korisnikRepository.save(korisnik);
    }

    public UserInfoDTO myInfo(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        String username = userPrincipal.getUsername();
        Optional<Korisnik> user = korisnikRepository.findFirstByUsername(username);

        if(user.isEmpty()) {
            return null;
        }

        return new UserInfoDTO(user.get());

    }

    public void editKorisnik(Authentication authentication, UserInfoDTO body){
        Korisnik current = getUser(authentication);

        if(body.getNovaLozinka() != null){
            current.setLozinka(passwordEncoder.encode(body.getLozinka()));
        }
        current.setEmail(body.getEmail());
        current.setPrezime(body.getPrezime());
        current.setAdresa(body.getAdresa());
        current.setBrojTelefona(body.getBrojTelefona());
        current.setIme(body.getIme());

        korisnikRepository.save(current);
    }


}
