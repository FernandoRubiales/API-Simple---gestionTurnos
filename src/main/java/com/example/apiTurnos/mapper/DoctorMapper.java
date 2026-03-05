package com.example.apiTurnos.mapper;

import com.example.apiTurnos.dto.DoctorDTORequest;
import com.example.apiTurnos.dto.DoctorDTOResponse;
import com.example.apiTurnos.dto.PacienteDTORequest;
import com.example.apiTurnos.dto.PacienteDTOResponse;
import com.example.apiTurnos.entity.Doctor;
import com.example.apiTurnos.entity.Paciente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DoctorMapper {

    //Leer de la DB y devolver al cliente
    public DoctorDTOResponse toResponse(Doctor doctor) {
        if (doctor == null) {
            return null;
        }
        DoctorDTOResponse doctorResponse = new DoctorDTOResponse();
        doctorResponse.setId(doctor.getId());
        doctorResponse.setNombre(doctor.getNombre());
        doctorResponse.setApellido(doctor.getApellido());
        doctorResponse.setEspecialidad(doctor.getEspecialidad());
        doctorResponse.setMatricula(doctor.getMatricula());

        return doctorResponse;
    }

    //Del cliente a la DB
    public Doctor toEntity(DoctorDTORequest doctorRequest){
        if (doctorRequest == null){
            return null;
        }

        Doctor doctor = new Doctor();
        doctor.setNombre(doctorRequest.getNombre());
        doctor.setApellido(doctorRequest.getApellido());
        doctor.setEspecialidad(doctorRequest.getEspecialidad());
        doctor.setMatricula(doctorRequest.getMatricula());
        return doctor;
    }

    //Lista de entidades a lista de response para el cliente
    public List<DoctorDTOResponse> toResponseList(List<Doctor>doctores){
        if (doctores==null){
            return null;
        }

        return doctores.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    //Realizar un update desde una request
    public void updateEntitytoRequest(Doctor doctor, DoctorDTORequest doctorRequest){
        if (doctorRequest == null || doctor == null) {
            return;
        }
        doctor.setNombre(doctorRequest.getNombre());
        doctor.setApellido(doctorRequest.getApellido());
        doctor.setEspecialidad(doctorRequest.getEspecialidad());
        doctor.setMatricula(doctorRequest.getMatricula());
    }
}