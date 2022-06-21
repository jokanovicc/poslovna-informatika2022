package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Korisnik;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {

    private String ime;
    private String prezime;
    private String username;
    private String lozinka;
    private String email;
    private String brojTelefona;
    private String adresa;
    private String novaLozinka;


    public UserInfoDTO(Korisnik korisnik){
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.lozinka = korisnik.getLozinka();
        this.email = korisnik.getEmail();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.adresa = korisnik.getAdresa();
    }



}
