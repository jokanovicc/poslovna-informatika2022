package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.PoreskaKategorija;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PoreskaKategorijaDTO {


    private Integer id;
    private String naziv;

    private List<PoreskaStopaDTO> poreskaStopaDTO = new ArrayList<>();

    public PoreskaKategorijaDTO(PoreskaKategorija poreskaKategorija){
        this.id = poreskaKategorija.getId();
        this.naziv =poreskaKategorija.getNaziv();
    }

}
