package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.cl.edutech.model.InscripcionesCursos;
import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.services.UsuarioService;
import com.edutech.cl.edutech.services.InscripcionesCursosService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> obtenerCursosUsuario(@PathVariable("id") Integer id) {
        List<InscripcionesCursos> cursos = inscripcionesCursosService.cursoUsuario(id);
        
        if (cursos.isEmpty()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "El usuario no tiene cursos inscritos");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        return ResponseEntity.ok(cursos);
    }

    @DeleteMapping("/eliminar/{id}")
    public Usuario eliminar(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.conseguirPorId(id);
        usuarioService.eliminar(id);
        return usuario;
    }
    
}