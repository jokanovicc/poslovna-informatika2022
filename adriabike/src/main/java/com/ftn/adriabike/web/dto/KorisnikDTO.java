package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Korisnik;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KorisnikDTO {

    private Integer id;

    private String ime;

    private String prezime;

    private String username;

    private String email;

    private String brojTelefona;

    private String adresa;

    public KorisnikDTO(Korisnik korisnik){
        this.id = korisnik.getId();
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.email = korisnik.getEmail();
        this.brojTelefona = korisnik.getBrojTelefona();
        this.adresa = korisnik.getAdresa();
    }
}
