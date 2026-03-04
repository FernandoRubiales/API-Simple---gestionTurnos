package com.example.apiTurnos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDTORequest {
    private String nombre;
    private String apellido;
    private String matricula;
    private String especialidad;
}
