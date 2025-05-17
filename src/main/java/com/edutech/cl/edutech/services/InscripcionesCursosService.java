package com.edutech.cl.edutech.services;
import com.edutech.cl.edutech.model.InscripcionesCursos;
import com.edutech.cl.edutech.repository.InscripcionesCursosRepository;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionesCursosService { 

    @Autowired
    private InscripcionesCursosRepository inscripcionesCursosRepository;

    public List<InscripcionesCursos> getInscripcionesCursos(){

        return inscripcionesCursosRepository.findAll();

    }

    public InscripcionesCursos getInscripcionesCursosId(Integer id){

        return inscripcionesCursosRepository.findById(id).orElse(null);

    }

    public InscripcionesCursos crearInscripcionesCursos(InscripcionesCursos inscripcionesCursos){

        return inscripcionesCursosRepository.save(inscripcionesCursos);

    }

    public void eliminarInscripcionesCursos(Integer id){

        inscripcionesCursosRepository.deleteById(id);

    }

    public InscripcionesCursos updateInscripcionesCursos(Integer id, InscripcionesCursos inscripcionesCursosUpdate){

        InscripcionesCursos inscripcionesCursosExist = inscripcionesCursosRepository.findById(id).orElse(null);

        if(inscripcionesCursosExist == null){

            return null;

        }
        inscripcionesCursosExist.setCurso(inscripcionesCursosUpdate.getCurso());
        inscripcionesCursosExist.setFechaInscripcion(inscripcionesCursosUpdate.getFechaInscripcion());
        inscripcionesCursosExist.setUsuario(inscripcionesCursosUpdate.getUsuario());

        return inscripcionesCursosRepository.save(inscripcionesCursosExist);

    }
    
}
