package com.edutech.cl.edutech.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_eva;

    @Column(nullable = false)
    private String titulo_eva;

    @Column(nullable = false)
    private String desc_eva;

    @Column(nullable = false)
    private String status_eva;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;

    @Column(nullable = false)
    private Date fecha_evaluacion;


}