package com.edutech.cl.edutech.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gerente")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Gerente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_gerente;

    @Column(nullable = false)
    private String pnombre_ger;

    @Column(nullable = true)
    private String snombre_ger;

    @Column(nullable = false)
    private String appaterno_ger;

    @Column(nullable = false)
    private String apmaterno_ger;

    @Column(nullable = false)
    private String correo_ger;

    @Column(nullable = false)
    private Date fec_registro_ger;

}
