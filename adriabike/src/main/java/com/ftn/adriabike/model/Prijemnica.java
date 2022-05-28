package com.ftn.adriabike.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prijemnica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer broj;

    private LocalDate datumDokumenta;

    private LocalDate datumKnjizenja;

    @ManyToOne
    private Magacin magacin;


}
