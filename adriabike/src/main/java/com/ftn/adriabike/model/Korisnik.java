package com.ftn.adriabike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ime;

    private String prezime;

    private String username;

    private String lozinka;

    private String email;

    private String brojTelefona;

    private String adresa;

    @Enumerated(EnumType.STRING)
    private Roles roles;

}
