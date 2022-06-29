package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Artikal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtikliListboxDTO {

    private String naziv;
    private Integer id;




    public ArtikliListboxDTO(Artikal a){
        this.naziv = a.getNaziv();
        this.id = a.getId();
    }
}
