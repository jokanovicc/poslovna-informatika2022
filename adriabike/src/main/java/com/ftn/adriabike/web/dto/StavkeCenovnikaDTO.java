package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.StavkaCenovnika;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StavkeCenovnikaDTO {

    private Integer id;
    private Double cena;
    private String nazivArtikla;


    public StavkeCenovnikaDTO(StavkaCenovnika stavkaCenovnika){
        this.id = stavkaCenovnika.getId();
        this.cena = stavkaCenovnika.getCena();
        this.nazivArtikla = stavkaCenovnika.getArtikal().getNaziv();

    }
}
