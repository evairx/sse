package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.assembler.EvaluacionModelAssembler;
import com.edutech.cl.edutech.model.Evaluacion;
import com.edutech.cl.edutech.services.EvaluacionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@Tag(name = "Evaluaciones", description = "Operaciones relacionados con las evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private EvaluacionModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todas las evaluaciones", description = "Permite obtener todas las evaluaciones")
    public CollectionModel<EntityModel<Evaluacion>> obtenerTodos() {
        List<EntityModel<Evaluacion>> evaluaciones = evaluacionService.getEvaluaciones().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(evaluaciones,
            linkTo(methodOn(EvaluacionController.class).obtenerTodos()).withSelfRel());
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear una evaluación", description = "Permite la creación de evaluaciones")
    public EntityModel<Evaluacion> crear(@RequestBody Evaluacion evaluacion) {
        Evaluacion nuevaEvaluacion = evaluacionService.crearEvaluacion(evaluacion);
        return assembler.toModel(nuevaEvaluacion);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar evaluación por ID", description = "Permite buscar una evaluación por su ID")
    public EntityModel<Evaluacion> buscarEvaluacion(@PathVariable("id") int id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionId(id);
        return assembler.toModel(evaluacion);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar evaluación por ID", description = "Permite eliminar una evaluación mediante su ID")
    public EntityModel<Evaluacion> borrar(@PathVariable("id") int id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionId(id);
        evaluacionService.eliminarEvaluacion(id);
        return assembler.toModel(evaluacion);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar evaluación mediante ID", description = "Permite actualizar los datos de una evaluación por ID")
    public EntityModel<Evaluacion> actualizar(@PathVariable("id") int id, @RequestBody Evaluacion evaluacion) {
        Evaluacion actualizada = evaluacionService.updateEvaluacion(id, evaluacion);
        return assembler.toModel(actualizada);
    }
}
