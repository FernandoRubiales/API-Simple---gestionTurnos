package com.example.apiTurnos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnosDTOResponse {

    private Long id;
    private PacienteDTOResponse paciente;
    private DoctorDTOResponse doctor;
}
