package com.ftn.adriabike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StavkaPrometnogDokumenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer kolicina;

    private Double cena;

    private Double vrednost;

    @ManyToOne
    private Prijemnica prijemnica;

    @ManyToOne
    private Artikal artikal;

}
