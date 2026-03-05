package com.example.apiTurnos.mapper;

import com.example.apiTurnos.dto.PacienteDTORequest;
import com.example.apiTurnos.dto.PacienteDTOResponse;
import com.example.apiTurnos.entity.Paciente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component  //Spring lo detecta y lo pone donde lo necesites
public class PacienteMapper {

    //Leer de la DB y devolver al cliente
    public PacienteDTOResponse toResponse (Paciente paciente){
        if(paciente == null){
            return null;
        }

        PacienteDTOResponse pacienteResponse = new PacienteDTOResponse();
        pacienteResponse.setId(paciente.getId());
        pacienteResponse.setNombre(paciente.getNombre());
        pacienteResponse.setApellido(paciente.getApellido());
        pacienteResponse.setDni(paciente.getDni());
        pacienteResponse.setEmail(paciente.getEmail());
        pacienteResponse.setPhone(paciente.getPhone());
        return pacienteResponse;
    }

    //Del cliente a la DB
    public Paciente toEntity(PacienteDTORequest pacienteRequest){
        if (pacienteRequest == null){
            return null;
        }

        Paciente paciente = new Paciente();
        paciente.setNombre(pacienteRequest.getNombre());
        paciente.setApellido(pacienteRequest.getApellido());
        paciente.setDni(pacienteRequest.getDni());
        paciente.setEmail(pacienteRequest.getEmail());
        paciente.setPhone(pacienteRequest.getPhone());
        return paciente;

    }

    //Lista de entidades a lista de response para el cliente
    public List<PacienteDTOResponse> toResponseList(List<Paciente>pacientes){
        if (pacientes==null){
            return null;
        }

        return pacientes.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    //Realizar un update desde una request
    public void updateEntityfromRequest(Paciente paciente, PacienteDTORequest pacienteRequest){
        if (pacienteRequest == null || paciente == null) {
            return;
        }

        paciente.setNombre(pacienteRequest.getNombre());
        paciente.setApellido(pacienteRequest.getApellido());
        paciente.setEmail(pacienteRequest.getEmail());
        paciente.setDni(pacienteRequest.getDni());
        paciente.setPhone(pacienteRequest.getPhone());
    }
}
