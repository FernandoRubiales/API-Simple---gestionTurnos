package com.example.apiTurnos.controller;

import com.example.apiTurnos.dto.PacienteDTORequest;
import com.example.apiTurnos.dto.PacienteDTOResponse;
import com.example.apiTurnos.services.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    //CREAR paciente - METODO POST
    @PostMapping
    public ResponseEntity<PacienteDTOResponse> create(@RequestBody PacienteDTORequest pacienteRequest){
        PacienteDTOResponse pacienteResponse = pacienteService.create(pacienteRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteResponse);
    }

    //OBTENER paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> findById(@PathVariable Long id){
        PacienteDTOResponse pacienteResponse = pacienteService.findById(id);

        return ResponseEntity.ok(pacienteResponse);
    }

    //OBTENER todos los pacientes
    @GetMapping
    public ResponseEntity<List<PacienteDTOResponse>> findAll(){
        List<PacienteDTOResponse> pacientesResponse = pacienteService.findAll();

        return ResponseEntity.ok(pacientesResponse);
    }

    //OBTENER paciente por DNI
    @GetMapping("/dni/{dni}")
    public ResponseEntity<PacienteDTOResponse> findByDni(@PathVariable String dni){
        PacienteDTOResponse pacienteResponse = pacienteService.findByDni(dni);

        return ResponseEntity.ok(pacienteResponse);
    }

    //ACTUALIZAR un paciente
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTOResponse> update(@PathVariable Long id, @RequestBody PacienteDTORequest pacienteRequest){
        PacienteDTOResponse pacienteResponse = pacienteService.update(id, pacienteRequest);

        return ResponseEntity.ok(pacienteResponse);
    }

    //ELIMINAR un paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
