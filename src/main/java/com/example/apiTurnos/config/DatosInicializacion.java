package com.example.apiTurnos.config;


import com.example.apiTurnos.entity.EstadoTurno;
import com.example.apiTurnos.repository.EstadoTurnoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatosInicializacion implements CommandLineRunner {

    private final EstadoTurnoRepository estadoTurnoRepository;

    @Override
    public void run(String... args){
        // Solo insertar si no existen estados
        if (estadoTurnoRepository.count() == 0) {
            System.out.println("Inicializando estados de turno...");

            crearEstado("CREADO");
            crearEstado("COMPLETADO");
            crearEstado("CANCELADO");
            crearEstado("NO_ASISTIO");

            System.out.println("Inicializando estados de turno...");
        } else {
            System.out.println("Estados de turno ya existen en la base de datos");;
        }
    }

    //Metodo que crea los estados cuando arranca la aplicacion
    private void crearEstado(String nombre) {
        EstadoTurno estadoTurno = new EstadoTurno();
        estadoTurno.setNombreEstado(nombre);
        estadoTurnoRepository.save(estadoTurno);
        System.out.println("Estado creado: " + nombre);
    }


}
