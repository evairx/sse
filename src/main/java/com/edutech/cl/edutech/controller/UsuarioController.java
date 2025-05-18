package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.edutech.cl.edutech.model.InscripcionesCursos;
import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.services.UsuarioService;
import com.edutech.cl.edutech.services.InscripcionesCursosService;

import java.util.List;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private InscripcionesCursosService inscripcionesCursosService;
    
    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.conseguir();
    }

    @PostMapping("/crear")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
    }
    
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable("id") int id) {
        return usuarioService.conseguirPorId(id);
    }

    @GetMapping("/{id}/cursos")
    public List<InscripcionesCursos> obtenerCursosUsuario(@PathVariable("id") Integer id) {
        return inscripcionesCursosService.cursoUsuario(id);
    }
    
    @DeleteMapping("/eliminar/{id}")
    public Usuario eliminar(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.conseguirPorId(id);
        usuarioService.eliminar(id);
        return usuario;
    }
    
}