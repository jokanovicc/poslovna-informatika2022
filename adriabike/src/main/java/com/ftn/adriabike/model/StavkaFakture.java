package com.ftn.adriabike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StavkaFakture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer kolicina;

    private Double jedinicnaCena;

    private Double rabat;

    private Double osnovica;

    private Integer procenatPDV;

    private Double iznosPDV;

    private Double ukupno;

    @ManyToOne
    private Artikal artikal;

    @ManyToOne
    private IzlaznaFaktura izlaznaFaktura;
}
