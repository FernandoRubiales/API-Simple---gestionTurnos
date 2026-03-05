package com.example.apiTurnos.services;

import com.example.apiTurnos.dto.PacienteDTORequest;
import com.example.apiTurnos.dto.PacienteDTOResponse;
import com.example.apiTurnos.entity.Paciente;
import com.example.apiTurnos.mapper.PacienteMapper;
import com.example.apiTurnos.repository.PacienteRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;

    //CREATE paciente
    public PacienteDTOResponse create(PacienteDTORequest pacienteRequest){
        if(pacienteRepository.existsByDni(pacienteRequest.getDni())){
            throw new RuntimeException("Ya existe un paciente con DNI: " + pacienteRequest.getDni());
        }

        if(pacienteRepository.existsByEmail(pacienteRequest.getEmail())){
            throw new RuntimeException("Ya existe un paciente con Email: " + pacienteRequest.getEmail());
        }
        Paciente paciente = pacienteMapper.toEntity(pacienteRequest);
        Paciente pacienteGuardado = pacienteRepository.save(paciente);

        return pacienteMapper.toResponse(pacienteGuardado);
    }

    //GET paciente por ID
    public PacienteDTOResponse findById(Long id){
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        return pacienteMapper.toResponse(paciente);
    }

    //GET todos los pacientes
    public List<PacienteDTOResponse> findAll(){
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacienteMapper.toResponseList(pacientes);

    }

    //UPDATE paciente
    public PacienteDTOResponse update(Long id,PacienteDTORequest pacienteRequest){
        //Buscar al paciente existente con su id
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

        //Si se cambia el dni hay q validar que no se repita
        String dniIngresado = pacienteRequest.getDni();
        if(dniIngresado != paciente.getDni()) {
            if (pacienteRepository.existsByDni(dniIngresado)) {
                throw new RuntimeException("Ya existe un paciente con DNI: " + dniIngresado);
            }
        }

        //Si se cambia el email verificar tambien que no este repetido
        String emailIngresado = pacienteRequest.getEmail();
        if(emailIngresado != paciente.getEmail()){
            if(pacienteRepository.existsByEmail(emailIngresado)){
                throw new RuntimeException("Ya existe un paciente con Email: " + emailIngresado);
            }
        }

        //Update de los nuevos datos del paciente
        pacienteMapper.updateEntityfromRequest(paciente, pacienteRequest);
        Paciente updatePaciente = pacienteRepository.save(paciente);
        return pacienteMapper.toResponse(updatePaciente);

    }

    //DELETE paciente
    public void delete(Long id){
        //Verificar que exista
        if(!pacienteRepository.existsById(id)){
            throw new RuntimeException("Paciente no encontrado con ID: " + id);
        }

        pacienteRepository.deleteById(id);

    }

    //GET por DNI
    public PacienteDTOResponse findByDni(String dni){
        Paciente paciente = pacienteRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con DNI: " + dni));

        return pacienteMapper.toResponse(paciente);
    }
}
