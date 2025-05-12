package com.edutech.cl.edutech.model;

import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(nullable = false)
    private String pnombre_usu;

    @Column(nullable = true)
    private String snombre_usu;

    @Column(nullable = false)
    private String appaterno_usu;

    @Column(nullable = false)
    private String apmaterno_usu;

    @Column(nullable = false)
    private String correo_usu;

    @Column(nullable = false)
    private String tipo_rol_user;

    @Column(nullable = false)
    private Date fec_registro_usu;

}
