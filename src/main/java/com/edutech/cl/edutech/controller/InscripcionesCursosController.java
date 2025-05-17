package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.InscripcionesCursos;
import com.edutech.cl.edutech.services.InscripcionesCursosService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/PnscripcionesCursoss")
public class InscripcionesCursosController {

    @Autowired
    private InscripcionesCursosService inscripcionesCursosService;
    
    @GetMapping
    public List<InscripcionesCursos> obtenerTodos() {
        return inscripcionesCursosService.getInscripcionesCursos();
    }

    @PostMapping("/crear")
    public InscripcionesCursos crear(@RequestBody InscripcionesCursos inscripcionesCursos) {
        inscripcionesCursosService.crearInscripcionesCursos(inscripcionesCursos);
        return inscripcionesCursos;
    }
    
    @GetMapping("/{id}")
    public InscripcionesCursos buscarInscripcionesCursos(@PathVariable("id") int id) {
        InscripcionesCursos inscripcionesCursos = inscripcionesCursosService.getInscripcionesCursosId(id);
        return inscripcionesCursos;
    }

    @DeleteMapping("/eliminar/{id}")
    public InscripcionesCursos borrar(@PathVariable("id") int id) {
        InscripcionesCursos inscripcionesCursos = inscripcionesCursosService.getInscripcionesCursosId(id);
        inscripcionesCursosService.eliminarInscripcionesCursos(id);
        return inscripcionesCursos;
    }
    
    @PutMapping("/actualizar/{id}")
    public InscripcionesCursos actualizar(@PathVariable("id") int id, @RequestBody InscripcionesCursos inscripcionesCursos) {
        inscripcionesCursosService.updateInscripcionesCursos(id, inscripcionesCursos);
        return inscripcionesCursos;
    }
}