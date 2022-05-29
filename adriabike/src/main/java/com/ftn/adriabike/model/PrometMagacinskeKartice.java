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
public class PrometMagacinskeKartice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date datum;

    private Integer kolicina;

    private Double cena;

    private Double vrednost;

    private Smer smer;

    @ManyToOne
    private MagacinskaKartica magacinskaKartica;

}

