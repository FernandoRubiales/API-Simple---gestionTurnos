package com.example.apiTurnos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obligatorio el nombre")
    private String nombre;
    @NotBlank(message = "Obligatorio el apellido")
    private String apellido;
    @NotBlank(message = "Obligatorio el DNI")
    private int dni;
    @NotBlank(message = "Obligatorio el email")
    private String email;
    @NotBlank(message = "Obligatorio el numero de telefono")
    private int phone;



}
