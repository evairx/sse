package com.edutech.cl.edutech.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.edutech.cl.edutech.controller.EvaluacionController;
import com.edutech.cl.edutech.model.Evaluacion;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {

    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion) {
        return EntityModel.of(evaluacion,
            linkTo(methodOn(EvaluacionController.class).buscarEvaluacion(evaluacion.getId_eva())).withSelfRel(),
            linkTo(methodOn(EvaluacionController.class).obtenerTodos()).withRel("evaluaciones")
        );
    }
}
