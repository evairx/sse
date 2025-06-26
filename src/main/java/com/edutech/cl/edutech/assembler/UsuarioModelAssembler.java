package com.edutech.cl.edutech.assembler;

import com.edutech.cl.edutech.controller.UsuarioController;
import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.model.UsuarioModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioModelAssembler implements RepresentationModelAssembler<Usuario, UsuarioModel> {

    @Override
    public UsuarioModel toModel(Usuario usuario) {
        UsuarioModel model = new UsuarioModel(
            usuario.getId_usuario(),
            usuario.getPnombre_usu(),
            usuario.getSnombre_usu(),
            usuario.getAppaterno_usu(),
            usuario.getApmaterno_usu(),
            usuario.getCorreo_usu(),
            usuario.getTipo_rol_usu(),
            usuario.getFec_registro_usu()
        );

        model.add(linkTo(methodOn(UsuarioController.class).buscar(usuario.getId_usuario())).withSelfRel());
        model.add(linkTo(methodOn(UsuarioController.class).obtenerTodos()).withRel("todos"));
        model.add(linkTo(methodOn(UsuarioController.class).eliminar(usuario.getId_usuario())).withRel("eliminar"));
        model.add(linkTo(methodOn(UsuarioController.class).actualizar(usuario.getId_usuario(), usuario)).withRel("actualizar"));

        return model;
    }
}
