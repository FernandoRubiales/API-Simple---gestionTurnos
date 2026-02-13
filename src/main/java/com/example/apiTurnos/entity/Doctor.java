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
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obligatorio la matricula")
    private int matricula;
    @NotBlank(message = "Obligatorio el nombre")
    private String nombre;
    @NotBlank(message = "Obligatorio el apellido")
    private String apellido;
    @NotBlank(message = "Obligatorio la especialidad")
    private String especialidad;


}
