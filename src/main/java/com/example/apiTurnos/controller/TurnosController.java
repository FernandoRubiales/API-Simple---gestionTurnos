package com.example.apiTurnos.controller;

import com.example.apiTurnos.dto.TurnosDTORequest;
import com.example.apiTurnos.dto.TurnosDTOResponse;
import com.example.apiTurnos.services.TurnosService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/turnos")
@RequiredArgsConstructor
public class TurnosController {

    private final TurnosService turnoService;

    //CREAR un turno
    @PostMapping
    public ResponseEntity<TurnosDTOResponse> create(@RequestBody TurnosDTORequest turnoRequest) {
        TurnosDTOResponse turnoResponse = turnoService.create(turnoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(turnoResponse);
    }
    //OBTENER turno por ID
    @GetMapping("/{id}")
    public ResponseEntity<TurnosDTOResponse> findById(@PathVariable Long id) {
        TurnosDTOResponse turnoResponse = turnoService.findById(id);
        return ResponseEntity.ok(turnoResponse);
    }

    //OBTENER todos los turnos
    @GetMapping
    public ResponseEntity<List<TurnosDTOResponse>> findAll() {
        List<TurnosDTOResponse> turnosResponse = turnoService.findAll();
        return ResponseEntity.ok(turnosResponse);
    }

    //OBTENER turnos por PACIENTE
    @GetMapping("/paciente/{pacienteId}")
    public ResponseEntity<List<TurnosDTOResponse>> findByPaciente(@PathVariable Long pacienteId) {
        List<TurnosDTOResponse> turnosResponse = turnoService.findByPaciente(pacienteId);
        return ResponseEntity.ok(turnosResponse);
    }

    //OBTENER turnos por DOCTOR
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<TurnosDTOResponse>> findByDoctor(@PathVariable Long doctorId) {
        List<TurnosDTOResponse> turnosResponse = turnoService.findByDoctor(doctorId);
        return ResponseEntity.ok(turnosResponse);
    }

    //OBTENER turnos entre fechas
    @GetMapping("/fechas")
    public ResponseEntity<List<TurnosDTOResponse>> findEntreFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {

        List<TurnosDTOResponse> turnosResponse = turnoService.findTurnosEntreFechas(inicio, fin);
        return ResponseEntity.ok(turnosResponse);
    }

    //CAMBIAR estado de un turno
    @PutMapping("/{turnoId}/estado/{estadoTurnoNombre}")
    public ResponseEntity<TurnosDTOResponse> cambiarEstado(
            @PathVariable Long turnoId,
            @PathVariable String estadoTurnoNombre) {

        TurnosDTOResponse turnoResponse = turnoService.cambiarEstado(turnoId, estadoTurnoNombre);
        return ResponseEntity.ok(turnoResponse);
    }

    //ELIMINAR un turno
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        turnoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
