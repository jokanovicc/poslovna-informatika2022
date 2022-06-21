package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.MagacinskaKartica;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagacinskaKarticaResponse {

    private Integer id;

    private Integer pocetnoStanjeKolicina;
    private Integer prometUlazaKolicina;
    private Integer prometIzlazaKolicina;
    private Double pocetnoStanjeVrednost;
    private Double prometUlazaVrednost;
    private Double prometIzlazaVrednost;
    private Double ukupnaVrednost;
    private Double prosecnaCena;

    private String nazivArtikla;
    private Integer idArtikla;

    private Integer poslovnaGodina;
    private String magacinMesto;


    public MagacinskaKarticaResponse(MagacinskaKartica magacinskaKartica){

        this.id = magacinskaKartica.getId();
        this.pocetnoStanjeVrednost = magacinskaKartica.getPocetnoStanjeVrednost();
        this.pocetnoStanjeKolicina = magacinskaKartica.getPocetnoStanjeKolicina();
        this.prometUlazaKolicina = magacinskaKartica.getPrometUlazaKolicina();
        this.pocetnoStanjeKolicina = magacinskaKartica.getPocetnoStanjeKolicina();
        this.prometIzlazaKolicina = magacinskaKartica.getPrometIzlazaKolicina();
        this.prometIzlazaVrednost = magacinskaKartica.getPrometIzlazaVrednost();
        this.ukupnaVrednost = magacinskaKartica.getUkupnaVrednost();
        this.prosecnaCena = magacinskaKartica.getProsecnaCena();
        this.nazivArtikla = magacinskaKartica.getArtikal().getNaziv();
        this.idArtikla = magacinskaKartica.getArtikal().getId();
        this.poslovnaGodina = magacinskaKartica.getPoslovnaGodina().getGodina();
        this.magacinMesto = magacinskaKartica.getMagacin().getLokacija();

    }

}
