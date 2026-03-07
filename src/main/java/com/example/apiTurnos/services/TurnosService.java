package com.example.apiTurnos.services;

import com.example.apiTurnos.dto.TurnosDTORequest;
import com.example.apiTurnos.dto.TurnosDTOResponse;
import com.example.apiTurnos.entity.Doctor;
import com.example.apiTurnos.entity.EstadoTurno;
import com.example.apiTurnos.entity.Paciente;
import com.example.apiTurnos.entity.Turnos;
import com.example.apiTurnos.mapper.TurnosMapper;
import com.example.apiTurnos.repository.DoctorRepository;
import com.example.apiTurnos.repository.EstadoTurnoRepository;
import com.example.apiTurnos.repository.PacienteRepository;
import com.example.apiTurnos.repository.TurnosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TurnosService {

    private final TurnosRepository turnosRepository;
    private final PacienteRepository pacienteRepository;
    private final DoctorRepository doctorRepository;
    private final EstadoTurnoRepository estadoTurnoRepository;
    private final TurnosMapper turnosMapper;


    //CREATE turno
    public TurnosDTOResponse create(TurnosDTORequest turnosRequest){
        //Validar que el paciente exista
        Paciente paciente = pacienteRepository.findById(turnosRequest.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + turnosRequest.getPacienteId()));

        //Validar que el doctor exista
        Doctor doctor = doctorRepository.findById(turnosRequest.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + turnosRequest.getDoctorId()));

        //Buscar el estado CREADO para asignarselo apenas se crea un turno
        EstadoTurno estadoTurno = estadoTurnoRepository.findByNombreEstado("CREADO")
                .orElseThrow(() -> new RuntimeException("Estado CREADO no encontrado"));

        Turnos turno = turnosMapper.toEntity(turnosRequest);
        turno.setPaciente(paciente);
        turno.setDoctor(doctor);
        turno.setEstadoTurno(estadoTurno);

        Turnos turnoGuardado = turnosRepository.save(turno);
        return turnosMapper.toResponse(turnoGuardado);
    }

    //GET turno por ID
    public TurnosDTOResponse findById(Long id){
        Turnos turno = turnosRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado con ID: " + id));

        return turnosMapper.toResponse(turno);
    }

    //GET todos los turnos
    public List<TurnosDTOResponse> findAll(){
        List<Turnos> turnos = turnosRepository.findAll();
        return turnosMapper.toResponseList(turnos);
    }

    //DELETE un turno
    public void delete(Long id){

        if (!turnosRepository.existsById(id)) {
            throw new RuntimeException("Turno no encontrado con ID: " + id);
        }
        turnosRepository.deleteById(id);
    }

    //GET turnos por paciente
    public List<TurnosDTOResponse> findByPaciente(Long pacienteId){
        if(!pacienteRepository.existsById(pacienteId)){
            throw new RuntimeException("Paciente no encontrado con ID:" +pacienteId);
        }

        List<Turnos> turnos = turnosRepository.findByPaciente_Id(pacienteId);
        return turnosMapper.toResponseList(turnos);
    }

    //GET turnos por doctor
    public List<TurnosDTOResponse> findByDoctor(Long doctorId){
        if(!doctorRepository.existsById((doctorId))){
            throw new RuntimeException("Doctor no encontrado con ID:" +doctorId);
        }
        List<Turnos> turnos = turnosRepository.findByDoctor_Id(doctorId);
        return turnosMapper.toResponseList(turnos);
    }

    //GET turnos entre fechas
    public List<TurnosDTOResponse> findTurnosEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
        List<Turnos> turnos = turnosRepository.findTurnosEntreFechas(inicio, fin);
        return turnosMapper.toResponseList(turnos);
    }

    //Cambiar estado de un turno
    public TurnosDTOResponse cambiarEstado(Long turnoId, String estadoTurnoNombre){
        Turnos turno = turnosRepository.findById(turnoId)
                .orElseThrow(() -> new RuntimeException("Turno no encontrado con ID: " + turnoId));

        EstadoTurno estadoTurno = estadoTurnoRepository.findByNombreEstado(estadoTurnoNombre)
                .orElseThrow(() -> new RuntimeException("Estado turno  no encontrado con nombre: " + estadoTurnoNombre));

        turno.setEstadoTurno(estadoTurno);
        Turnos turnoGuardado = turnosRepository.save(turno);
        return turnosMapper.toResponse(turnoGuardado);

    }

    //
}
