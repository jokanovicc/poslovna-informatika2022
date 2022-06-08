package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.StavkaKorpe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StavkaKorpeResponseDTO {

    private Integer id;
    private String nazivArtikla;
    private Integer kolicina;
    private Double cena;


    public StavkaKorpeResponseDTO(StavkaKorpe stavkaKorpe){
        this.id = stavkaKorpe.getId();
        this.nazivArtikla = stavkaKorpe.getArtikal().getNaziv();
        this.kolicina = stavkaKorpe.getKolicina();
    }
}
