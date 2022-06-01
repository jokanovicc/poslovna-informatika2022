package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Prijemnica;
import com.ftn.adriabike.model.StavkaPrometnogDokumenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrijemnicaResponseDTO {

    private Integer id;

    private Integer broj;

    private Date datumDokumenta;
    private Date datumKnjizenja;
    private String magacinNaziv;
    private List<StavkaPrometnogDokumentaDTO> stavkaPrometnogDokumenta = new ArrayList<>();


    public PrijemnicaResponseDTO(Prijemnica prijemnica){
        this.id = prijemnica.getId();
        this.broj = prijemnica.getBroj();
        this.magacinNaziv = prijemnica.getMagacin().getNaziv();
        this.datumDokumenta = prijemnica.getDatumDokumenta();
        this.datumKnjizenja = prijemnica.getDatumKnjizenja();
    }

}
