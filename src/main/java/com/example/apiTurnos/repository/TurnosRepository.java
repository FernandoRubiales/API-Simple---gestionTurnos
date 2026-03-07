package com.example.apiTurnos.repository;

import com.example.apiTurnos.entity.Turnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnosRepository extends JpaRepository<Turnos, Long> {

    //Buscamos el turno por paciente
    List<Turnos> findByPaciente_Id(Long pacienteId);

    //Buscamos los turnos asignados el doctor
    List<Turnos> findByDoctor_Id(Long doctorId);

    //Buscamos turnos por los estados
    List<Turnos> findByEstadoTurno_Id (Long estadoId);

    //Buscamos turnos entre diferentes fechas
    @Query(value = "SELECT * FROM turnos WHERE fecha_creacion BETWEEN :inicio AND :fin",
            nativeQuery = true)
    List<Turnos> findTurnosEntreFechas(
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin
    );
}
