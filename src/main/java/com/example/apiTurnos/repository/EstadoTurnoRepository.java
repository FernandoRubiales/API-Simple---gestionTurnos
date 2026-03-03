package com.example.apiTurnos.repository;

import com.example.apiTurnos.entity.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, Long> {
    Optional<EstadoTurno> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
