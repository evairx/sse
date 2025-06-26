package com.edutech.cl.edutech.model;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioModel extends RepresentationModel<UsuarioModel> {

    private Integer id_usuario;
    private String pnombre_usu;
    private String snombre_usu;
    private String appaterno_usu;
    private String apmaterno_usu;
    private String correo_usu;
    private String tipo_rol_usu;
    private Date fec_registro_usu;

}
