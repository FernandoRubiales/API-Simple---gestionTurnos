package com.example.apiTurnos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteDTOResponse {

    private Long id;
    private String nombre;
    private String email;
    private String apellido;
    private String dni;
    private String phone;

}
