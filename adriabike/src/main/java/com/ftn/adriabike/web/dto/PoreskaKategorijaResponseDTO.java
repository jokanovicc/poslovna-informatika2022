package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.PoreskaKategorija;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoreskaKategorijaResponseDTO {

    private Integer id;

    private String naziv;


    public PoreskaKategorijaResponseDTO(PoreskaKategorija poreskaKategorija){
        this.id = poreskaKategorija.getId();
        this.naziv = poreskaKategorija.getNaziv();
    }


}
