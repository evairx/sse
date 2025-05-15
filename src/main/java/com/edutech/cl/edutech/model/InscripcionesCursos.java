package com.edutech.cl.edutech.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inscripciones_curso")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class InscripcionesCursos {

    @OneToMany
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

}