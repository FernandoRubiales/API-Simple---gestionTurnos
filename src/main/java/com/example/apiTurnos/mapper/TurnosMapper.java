package com.example.apiTurnos.mapper;

import com.example.apiTurnos.dto.DoctorDTORequest;
import com.example.apiTurnos.dto.TurnosDTORequest;
import com.example.apiTurnos.dto.TurnosDTOResponse;
import com.example.apiTurnos.entity.Doctor;
import com.example.apiTurnos.entity.Turnos;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TurnosMapper {

    private final PacienteMapper pacienteMapper;
    private final DoctorMapper doctorMapper;

    //Leer de la DB y devolver al cliente
    public TurnosDTOResponse toResponse(Turnos turno){
        if(turno == null){
            return null;
        }

        TurnosDTOResponse turnoResponse = new TurnosDTOResponse();
        turnoResponse.setId(turno.getId());
        turnoResponse.setPaciente(pacienteMapper.toResponse(turno.getPaciente()));
        turnoResponse.setDoctor(doctorMapper.toResponse(turno.getDoctor()));
        turnoResponse.setFechaCreacion(turno.getFechaCreacion());

        return turnoResponse;
    }

    //Del cliente a la DB
    public Turnos toEntity(TurnosDTORequest turnoRequest){
        if (turnoRequest == null){
            return null;
        }

        Turnos turnos = new Turnos();
        turnos.setFechaCreacion(turnoRequest.getFechaCreacion());
        return turnos;
    }

    //Lista de entidades a lista de response para el cliente
    public List<TurnosDTOResponse> toResponseList(List<Turnos> turnos) {
        if (turnos == null) {
            return null;
        }

        return turnos.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
