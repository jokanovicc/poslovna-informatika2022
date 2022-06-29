package com.ftn.adriabike.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PorukaDTO {

    private Integer fakturaId;
    private String poruka;
}
