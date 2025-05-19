package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.services.CursoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    
    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursoService.getCursos();
    }

    @PostMapping("/crear")
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.crearCurso(curso);
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable("id") int id) {
        return cursoService.getCursoId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable int id) {
        try {
            Curso curso = cursoService.getCursoId(id);
            
            if (curso == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("mensaje", "Curso no encontrado");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            cursoService.eliminarCurso(id);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Curso eliminado correctamente");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Ocurrió un error al intentar eliminar el curso");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("/actualizar/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable("id") int id, @RequestBody Curso curso) {
        Curso cursoActualizado = cursoService.updateCurso(id, curso);
        
        if (cursoActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(cursoActualizado);
    }

}