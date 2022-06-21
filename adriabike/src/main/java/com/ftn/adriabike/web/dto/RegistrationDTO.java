package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Korisnik;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {

    private String ime;
    private String prezime;
    private String username;
    private String lozinka;
    private String email;
    private String brojTelefona;
    private String adresa;



}
