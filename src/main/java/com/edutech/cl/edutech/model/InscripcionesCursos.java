package com.edutech.cl.edutech.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inscripciones_curso")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class InscripcionesCursos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_curso;

    @Column(nullable = false)
    private String id_usuario;

}