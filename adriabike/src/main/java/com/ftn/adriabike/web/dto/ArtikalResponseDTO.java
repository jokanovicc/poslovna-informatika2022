package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Artikal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtikalResponseDTO {

    private Integer id;
    private String naziv;
    private String opis;
    private Integer pakovanje;
    private String slika;
    private Double ukupnaCena;

    public ArtikalResponseDTO(Artikal artikal){
        this.id = artikal.getId();
        this.naziv = artikal.getNaziv();
        this.opis = artikal.getOpis();
        this.pakovanje = artikal.getPakovanje();
        this.slika = artikal.getSlika();
    }



}
