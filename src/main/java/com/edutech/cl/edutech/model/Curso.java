package com.edutech.cl.edutech.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "curso")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_curso;

    @Column(nullable = false)
    private String nom_curso;

    @Column(nullable = true)
    private String desc_curso;

    @Column(nullable = false)
    private String cate_curso;

    @Column(nullable = false)
    private String estado_curso;

    @Column(nullable = false)
    private Date fec_curso;

}