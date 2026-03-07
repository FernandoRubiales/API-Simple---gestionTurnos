package com.example.apiTurnos.controller;

import com.example.apiTurnos.dto.DoctorDTORequest;
import com.example.apiTurnos.dto.DoctorDTOResponse;
import com.example.apiTurnos.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctores")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    //CREAR un doctor
    @PostMapping
    public ResponseEntity<DoctorDTOResponse> create(@RequestBody DoctorDTORequest doctorRequest) {
        DoctorDTOResponse doctorResponse = doctorService.create(doctorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorResponse);
    }

    //OBTENER doctor por ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTOResponse> findById(@PathVariable Long id) {
        DoctorDTOResponse doctorResponse = doctorService.findById(id);
        return ResponseEntity.ok(doctorResponse);
    }

    //OBTENER todos los doctores
    @GetMapping
    public ResponseEntity<List<DoctorDTOResponse>> findAll() {
        List<DoctorDTOResponse> doctoresResponse = doctorService.findAll();
        return ResponseEntity.ok(doctoresResponse);
    }

    //OBTENER doctores por ESPECIALIDAD
    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<DoctorDTOResponse>> findByEspecialidad(@PathVariable String especialidad) {
        List<DoctorDTOResponse> doctoresResponse = doctorService.findByEspecialidad(especialidad);
        return ResponseEntity.ok(doctoresResponse);
    }

    //OBTENER doctores por matricula
    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<DoctorDTOResponse> findByMatricula(@PathVariable String matricula) {
        DoctorDTOResponse doctorResponse = doctorService.findByMatricula(matricula);
        return ResponseEntity.ok(doctorResponse);
    }

    //ACTUALIZAR doctor
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDTOResponse> update(@PathVariable Long id, @RequestBody DoctorDTORequest doctorRequest) {
        DoctorDTOResponse doctorResponse = doctorService.update(id, doctorRequest);
        return ResponseEntity.ok(doctorResponse);
    }

    //ELIMINAR doctor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        doctorService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
