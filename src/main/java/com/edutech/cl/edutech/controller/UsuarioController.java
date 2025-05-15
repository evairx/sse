package com.edutech.cl.edutech.controller;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UsuarioController {

    @GetMapping("/usuario")
    public String mostrarHoli() {
        return new String("holi");
    }
    

}
