package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.Evaluacion;
import com.edutech.cl.edutech.services.EvaluacionService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/evaluaciones")
@Tag(name = "Evaluaciones", description = "Operaciones relacionados con las evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;
    
    @GetMapping
    @Operation(summary = "Obtener todas las evaluaciones", description = "Permite obtener todas las evaluaciones")
    public List<Evaluacion> obtenerTodos() {
        return evaluacionService.getEvaluaciones();
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear una evaluación", description = "Permite la creación de evaluaciones")
    public Evaluacion crear(@RequestBody Evaluacion Evaluacion) {
        evaluacionService.crearEvaluacion(Evaluacion);
        return Evaluacion;
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar evaluación por ID", description = "Permite buscar una evaluación por su ID")
    public Evaluacion buscarEvaluacion(@PathVariable("id") int id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionId(id);
        return evaluacion;
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar evaluación por ID", description = "Permite eliminar una evalaución mediante su ID")
    public Evaluacion borrar(@PathVariable("id") int id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionId(id);
        evaluacionService.eliminarEvaluacion(id);
        return evaluacion;
    }
    
    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar evaluación mediante ID", description = "Permite actualizar los datos de una evaluación por ID")
    public Evaluacion actualizar(@PathVariable("id") int id, @RequestBody Evaluacion evaluacion) {
        evaluacionService.updateEvaluacion(id, evaluacion);
        return evaluacion;
    }
}