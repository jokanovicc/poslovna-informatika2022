package com.ftn.adriabike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class IzlaznaFaktura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer brojFakture;

    private Date datumIzdavanja;

    private Date datumValute;

    private Double ukupnaOsnovica;

    private Double ukupanRabat;

    private Double ukupanPDV;

    private Double ukupanIznos;

    @Enumerated(EnumType.STRING)
    private Status statusFakture;
}
