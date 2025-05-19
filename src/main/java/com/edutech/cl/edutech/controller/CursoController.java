package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.model.InscripcionesCursos;
import com.edutech.cl.edutech.services.InscripcionesCursosService;
import com.edutech.cl.edutech.services.CursoService;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private InscripcionesCursosService inscripcionesCursosService;
    
    @GetMapping
    public List<Curso> obtenerTodos() {
        return cursoService.getCursos();
    }

    @PostMapping("/crear")
    public Curso crear(@RequestBody Curso curso) {
        return cursoService.crearCurso(curso);
    }

    @PostMapping("/inscribir")
    public ResponseEntity<?> inscribirCurso(@RequestBody Map<String, Integer> datos) {
        try {
            Integer idUsuario = datos.get("idUsuario");
            Integer idCurso = datos.get("idCurso");
            
            if (idUsuario == null || idCurso == null) {
                return ResponseEntity.badRequest().body("Se requieren idUsuario e idCurso");
            }
            
            InscripcionesCursos inscripcion = inscripcionesCursosService.inscribir(idUsuario, idCurso);
            
            if (inscripcion == null) {
                return ResponseEntity.badRequest().body("No se pudo realizar la inscripción. Verifique que los IDs sean válidos.");
            }
            
            return ResponseEntity.ok(inscripcion);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la inscripción: " + e.getMessage());
        }
    }

}