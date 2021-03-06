package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.IzlaznaFaktura;
import com.ftn.adriabike.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IzlaznaFakturaDTO {

    private Integer id;

    private Integer brojFakture;

    private Date datumIzdavanja;

    private Date datumValute;

    private Double ukupnaOsnovica;

    private Double ukupanRabat;

    private Double ukupanPDV;

    private Double ukupanIznos;

    private String statusFakture;

    private String kupacImePrezime;
    private String kupacAdresa;
    private String kupacBrojTelefona;

    private ArrayList<StavkaIzlazneFaktureDTO> stavkaFakture = new ArrayList<>();

    public IzlaznaFakturaDTO(IzlaznaFaktura izlaznaFaktura){
        this.id = izlaznaFaktura.getId();
        this.brojFakture = izlaznaFaktura.getBrojFakture();
        this.datumIzdavanja = izlaznaFaktura.getDatumIzdavanja();
        this.datumValute = izlaznaFaktura.getDatumValute();
        this.ukupnaOsnovica = izlaznaFaktura.getUkupnaOsnovica();
        this.ukupanRabat = izlaznaFaktura.getUkupanRabat();
        this.ukupanIznos = izlaznaFaktura.getUkupanIznos();
        this.kupacImePrezime = izlaznaFaktura.getKupac().getIme() + izlaznaFaktura.getKupac().getPrezime();
        this.kupacAdresa = izlaznaFaktura.getKupac().getAdresa();
        this.kupacBrojTelefona = izlaznaFaktura.getKupac().getBrojTelefona();
        this.statusFakture = String.valueOf(izlaznaFaktura.getStatusFakture());
    }


}
