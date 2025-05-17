package com.edutech.cl.edutech.services;
import com.edutech.cl.edutech.model.Evaluacion;
import com.edutech.cl.edutech.repository.EvaluacionRepository;   

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluacionService { 

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> getEvaluaciones(){

        return evaluacionRepository.findAll();

    }

    public Evaluacion getEvaluacionId(Integer id){

        return evaluacionRepository.findById(id).orElse(null);

    }

    public Evaluacion crearEvaluacion(Evaluacion evaluacion){

        return evaluacionRepository.save(evaluacion);

    }

    public void eliminarEvaluacion(Integer id){

        evaluacionRepository.deleteById(id);

    }

    public Evaluacion updateEvaluacion(Integer id, Evaluacion evaluacionUpdate){

        Evaluacion evaluacionExist = evaluacionRepository.findById(id).orElse(null);

        if(evaluacionExist == null){

            return null;

        }

        evaluacionExist.setCurso(evaluacionUpdate.getCurso());
        evaluacionExist.setDesc_eva(evaluacionUpdate.getDesc_eva());
        evaluacionExist.setFecha_evaluacion(evaluacionUpdate.getFecha_evaluacion());
        evaluacionExist.setStatus_eva(evaluacionUpdate.getStatus_eva());
        evaluacionExist.setTitulo_eva(evaluacionUpdate.getTitulo_eva());
        evaluacionExist.setUsuario(evaluacionUpdate.getUsuario());

        return evaluacionRepository.save(evaluacionExist);

    }
    
}
