package com.ftn.adriabike.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtikliPagingResult {


    private List<ArtikalResponseDTO> artikli = new ArrayList<>();
    int pagesCount;

}
