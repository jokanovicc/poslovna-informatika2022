package com.ftn.adriabike.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DobavljanjeNoveRobeDTO {


    private String naziv;

    private String opis;

    private Integer pakovanje;

    private String slika;

    private Integer poreskaId;

    //cena ce bidi dodana u poslenji cenovnik
    private Double cena;

    private Double nabavnaCena;

    private Integer kolicina;

    private Integer magacinId;

    //kasnije treba
    private Integer artikalId;

}
