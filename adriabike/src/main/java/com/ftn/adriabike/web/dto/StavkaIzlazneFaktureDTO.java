package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.StavkaFakture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StavkaIzlazneFaktureDTO {

    private Integer id;

    private Integer kolicina;

    private Double jedinicnaCena;

    private Double rabat;

    private Double osnovica;

    private Integer procenatPDV;

    private Double iznosPDV;

    private Double ukupno;

    private String nazivArtikla;

    public StavkaIzlazneFaktureDTO(StavkaFakture stavkaFakture){
        this.id = stavkaFakture.getId();
        this.kolicina = stavkaFakture.getKolicina();
        this.jedinicnaCena = stavkaFakture.getJedinicnaCena();
        this.rabat = stavkaFakture.getRabat();
        this.osnovica = stavkaFakture.getOsnovica();
        this.procenatPDV = stavkaFakture.getProcenatPDV();
        this.iznosPDV = stavkaFakture.getIznosPDV();
        this.ukupno = stavkaFakture.getUkupno();
        this.nazivArtikla = stavkaFakture.getArtikal().getNaziv();
    }
}
