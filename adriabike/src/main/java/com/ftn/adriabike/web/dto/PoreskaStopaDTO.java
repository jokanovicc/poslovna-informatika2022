package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.PoreskaStopa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoreskaStopaDTO {


    private Integer id;
    private String naziv;
    private Integer procenatPDV;
    private LocalDate datumStupanja;

    public PoreskaStopaDTO(PoreskaStopa poreskaStopa){
        this.id = poreskaStopa.getId();
        this.procenatPDV = poreskaStopa.getProcenatPDV();
        this.datumStupanja = poreskaStopa.getDatumStupanja();
    }

}
