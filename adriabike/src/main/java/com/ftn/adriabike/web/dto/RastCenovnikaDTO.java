package com.ftn.adriabike.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RastCenovnikaDTO {

    private Integer procenat;
    private boolean poskupljenje;
    private Date datum;
}
