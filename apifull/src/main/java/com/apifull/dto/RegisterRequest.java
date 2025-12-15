package com.apifull.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String nombre; // ✅ Nuevo campo
    private String email;
    private String password;
    private String rut;
    private String phone;
}