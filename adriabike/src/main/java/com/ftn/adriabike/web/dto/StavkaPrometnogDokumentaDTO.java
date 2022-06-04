package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.StavkaPrometnogDokumenta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StavkaPrometnogDokumentaDTO {

    private Integer id;

    private Integer kolicina;
    private Double cena;
    private Double vrednost;
    private String nazivArtikla;
    private Integer poreskaStopa;


    public StavkaPrometnogDokumentaDTO(StavkaPrometnogDokumenta stavkaPrometnogDokumenta){
        this.id = stavkaPrometnogDokumenta.getId();
        this.kolicina = stavkaPrometnogDokumenta.getKolicina();
        this.cena = stavkaPrometnogDokumenta.getCena();
        this.vrednost = stavkaPrometnogDokumenta.getVrednost();
        this.nazivArtikla = stavkaPrometnogDokumenta.getArtikal().getNaziv();
        this.poreskaStopa = stavkaPrometnogDokumenta.getArtikal().getPoreskaKategorija().getPoreskaStopa().getProcenatPDV();
    }
}
