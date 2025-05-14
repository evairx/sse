package com.edutech.cl.edutech.services;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.repository.CursoRepository;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class CursoService { 

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> getCursos(){

        return cursoRepository.findAll();

    }

    public Curso getCursoId(Integer id){

        return cursoRepository.findById(id).orElse(null);

    }

    public Curso crearCurso(Curso curso){

        if(curso.getFec_curso() == null){

            curso.setFec_curso(new Date());

        }

        return cursoRepository.save(curso);

    }

    public void eliminarCurso(Integer id){

        cursoRepository.deleteById(id);

    }

    public Curso updateCurso(Integer id, Curso cursoUpdate){

        Curso cursoExist = cursoRepository.findById(id).orElse(null);

        if(cursoExist == null){

            return null;

        }

        cursoExist.setNom_curso(cursoUpdate.getNom_curso());
        cursoExist.setDesc_curso(cursoUpdate.getDesc_curso());
        cursoExist.setEstado_curso(cursoUpdate.getEstado_curso());
        cursoExist.setCate_curso(cursoUpdate.getCate_curso());

        if(cursoUpdate.getFec_curso() == null){

            cursoUpdate.setFec_curso(new Date());

        };

        return cursoRepository.save(cursoExist);

    }
    
}
