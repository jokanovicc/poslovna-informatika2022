package com.ftn.adriabike.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakturaEndResponse {

    private String poruka;
    private boolean potvrdjena;
}
