package com.ftn.adriabike.web.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
