package com.edutech.cl.edutech.assembler;

import com.edutech.cl.edutech.controller.CursoController;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.model.CursoModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, CursoModel> {

    @Override
    public CursoModel toModel(Curso curso) {
        CursoModel model = new CursoModel(
            curso.getId_curso(),
            curso.getNom_curso(),
            curso.getDesc_curso(),
            curso.getCate_curso(),
            curso.getEstado_curso(),
            curso.getFec_curso()
        );

        model.add(linkTo(methodOn(CursoController.class).buscar(curso.getId_curso())).withSelfRel());
        model.add(linkTo(methodOn(CursoController.class).obtenerTodos()).withRel("todos"));

        return model;
    }
}
