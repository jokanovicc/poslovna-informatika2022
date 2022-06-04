package com.ftn.adriabike.web.dto;

import com.ftn.adriabike.model.Cenovnik;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CenovnikDTO {

    private Integer id;
    private Date stupioNaSnagu;
    private List<StavkeCenovnikaDTO> stavkeCenovnikaDTO = new ArrayList<>();

    public CenovnikDTO(Cenovnik cenovnik){
        this.id = cenovnik.getId();
        this.stupioNaSnagu = cenovnik.getStart();
    }


}
