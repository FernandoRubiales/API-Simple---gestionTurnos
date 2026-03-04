package com.example.apiTurnos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TurnosDTORequest {

    private LocalDateTime fechaCreacion;
    private Long pacienteId;
    private Long doctorId;


}
