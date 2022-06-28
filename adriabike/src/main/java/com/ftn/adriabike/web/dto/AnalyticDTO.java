package com.ftn.adriabike.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalyticDTO {

    private Date startDate;
    private Date endDate;
    private String smer;
}
