package com.example.apiTurnos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnosDTOResponse {

    private Long id;
    private PacienteDTOResponse paciente;
    private DoctorDTOResponse doctor;
    private LocalDateTime fechaCreacion;

}
