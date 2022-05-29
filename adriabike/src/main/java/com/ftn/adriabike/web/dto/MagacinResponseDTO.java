package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Magacin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MagacinResponseDTO {

    private Integer id;
    private String naziv;
    private String lokacija;


    public MagacinResponseDTO(Magacin magacin){
        this.id = magacin.getId();
        this.naziv = magacin.getNaziv();
        this.lokacija = magacin.getLokacija();
    }




}
