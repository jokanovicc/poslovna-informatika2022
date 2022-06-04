package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.PrometMagacinskeKartice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrometMagacinskeKarticeDTO {


    private Integer id;
    private Double cena;
    private Date datum;
    private Integer kolicina;
    private String smer;
    private Double vrednost;

    private String nazivArtikla;

    private String magacin;



    public PrometMagacinskeKarticeDTO(PrometMagacinskeKartice prometMagacinskeKartice){
        this.id = prometMagacinskeKartice.getId();
        this.cena = prometMagacinskeKartice.getCena();
        this.datum = prometMagacinskeKartice.getDatum();
        this.kolicina = prometMagacinskeKartice.getKolicina();
        this.smer = prometMagacinskeKartice.getSmer().toString();
        this.vrednost = prometMagacinskeKartice.getVrednost();
        this.nazivArtikla = prometMagacinskeKartice.getMagacinskaKartica().getArtikal().getNaziv();
        this.magacin = prometMagacinskeKartice.getMagacinskaKartica().getMagacin().getNaziv();
    }

}
