package com.ftn.adriabike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class MagacinskaKartica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Magacin magacin;

    @ManyToOne
    private PoslovnaGodina poslovnaGodina;

    private Integer pocetnoStanjeKolicina;
    private Integer prometUlazaKolicina;
    private Integer prometIzlazaKolicina;

    private Double pocetnoStanjeVrednost;
    private Double prometUlazaVrednost;
    private Double prometIzlazaVrednost;
    private Double ukupnaVrednost;
    private Double prosecnaCena;

    @ManyToOne
    private Artikal artikal;





}
