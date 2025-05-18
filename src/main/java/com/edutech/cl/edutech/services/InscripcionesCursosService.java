package com.edutech.cl.edutech.services;
import com.edutech.cl.edutech.model.InscripcionesCursos;
import com.edutech.cl.edutech.repository.InscripcionesCursosRepository;
import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.repository.UsuarioRepository;
import com.edutech.cl.edutech.repository.CursoRepository;

import java.util.List;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionesCursosService { 

    @Autowired
    private InscripcionesCursosRepository inscripcionesCursosRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private CursoRepository cursoRepository;

    public List<InscripcionesCursos> conseguir() {
        return inscripcionesCursosRepository.findAll();
    }

    public List<InscripcionesCursos> cursoUsuario(Integer idUsuario) {
        return inscripcionesCursosRepository.findAll().stream()
            .filter(inscripcion -> inscripcion.getUsuario().getId_usuario().equals(idUsuario))
            .collect(Collectors.toList());
    }
    
    public InscripcionesCursos inscribir(Integer idUsuario, Integer idCurso) {
        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
        Curso curso = cursoRepository.findById(idCurso).orElse(null);
        
        if (usuario == null || curso == null) {
            return null;
        }
        
        InscripcionesCursos inscripcion = new InscripcionesCursos();
        inscripcion.setUsuario(usuario);
        inscripcion.setCurso(curso);
        inscripcion.setEstado("Activo");
        inscripcion.setFechaInscripcion(new Date());
        
        return inscripcionesCursosRepository.save(inscripcion);
    }
}