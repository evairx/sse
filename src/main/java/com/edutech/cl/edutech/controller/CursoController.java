package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.services.CursoService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;
    
    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursoService.getCursos();
    }

    @PostMapping("/crear")
    public String crear(@RequestBody Curso curso) {
        cursoService.crearCurso(curso);
        return curso.toString();
    }
    
    @GetMapping("/{id}")
    public String buscarCurso(@PathVariable("id") int id,@RequestParam String param) {
        Curso curso = cursoService.getCursoId(id);
        return curso.toString();
    }

    @GetMapping("/eliminar/{id}")
    public String borrarString(@PathVariable("id") int id,@RequestParam String param) {
        Curso curso = cursoService.getCursoId(id);
        cursoService.eliminarCurso(id);
        return curso.toString();
    }
    
    @PutMapping("/actualizar/{id}")
    public String putMethodName(@PathVariable int id, @RequestBody Curso curso) {
        cursoService.updateCurso(id, curso);
        return curso.toString();
    }
}