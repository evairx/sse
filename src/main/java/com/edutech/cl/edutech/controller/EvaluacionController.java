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




@RestController
@RequestMapping("/api/v1/evaluaciones")
public class EvaluacionController {

    @Autowired
    private EvaluacionService evaluacionService;
    
    @GetMapping
    public List<Evaluacion> obtenerTodos() {
        return evaluacionService.getEvaluaciones();
    }

    @PostMapping("/crear")
    public Evaluacion crear(@RequestBody Evaluacion Evaluacion) {
        evaluacionService.crearEvaluacion(Evaluacion);
        return Evaluacion;
    }
    
    @GetMapping("/{id}")
    public Evaluacion buscarEvaluacion(@PathVariable("id") int id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionId(id);
        return evaluacion;
    }

    @DeleteMapping("/eliminar/{id}")
    public Evaluacion borrar(@PathVariable("id") int id) {
        Evaluacion evaluacion = evaluacionService.getEvaluacionId(id);
        evaluacionService.eliminarEvaluacion(id);
        return evaluacion;
    }
    
    @PutMapping("/actualizar/{id}")
    public Evaluacion actualizar(@PathVariable("id") int id, @RequestBody Evaluacion evaluacion) {
        evaluacionService.updateEvaluacion(id, evaluacion);
        return evaluacion;
    }
}