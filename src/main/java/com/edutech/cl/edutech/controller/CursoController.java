package com.edutech.cl.edutech.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class CursoController {

    @GetMapping("/curso")
    public String mostrarHoli() {
        return new String("holiwi");
    }
    
}
