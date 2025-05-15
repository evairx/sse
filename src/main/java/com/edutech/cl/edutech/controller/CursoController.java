package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.services.CursoService;
import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;
    
    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursoService.getCursos();
    }

}