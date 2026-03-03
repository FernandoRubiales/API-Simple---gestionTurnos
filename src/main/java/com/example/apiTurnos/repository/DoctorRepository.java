package com.example.apiTurnos.repository;

import com.example.apiTurnos.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByMatricula(String matricula);

    List<Doctor> findByEspecialidad(String especialidad);

    boolean existsByMatricula(String matricula);
}
