package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Korpa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KorpaResponseDTO {

    private Integer id;

    private ArrayList<StavkaKorpeResponseDTO> stavkaKorpeResponseDTOs= new ArrayList<>();


    public KorpaResponseDTO(Korpa korpa){
        this.id = korpa.getId();
    }
}
