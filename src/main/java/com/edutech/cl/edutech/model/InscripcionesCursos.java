package com.edutech.cl.edutech.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "inscripciones_curso")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InscripcionesCursos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @Column(name = "estado")
    private String estado;

    @Column(name = "fecha_inscripcion")
    private java.util.Date fechaInscripcion;
}