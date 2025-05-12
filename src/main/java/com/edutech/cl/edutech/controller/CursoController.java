package com.edutech.cl.edutech.controller;
import com.edutech.cl.edutech.model.Curso;
import java.util.List;
import java.util.ArrayList;

public class CursoController { 

    private List<Curso> listaCursos =  new ArrayList<>();

    public List<Curso> getCursos(){

        return listaCursos;

    }

    public Curso showCursos(int id){

        for(Curso cur : listaCursos){

            if(cur.getId_curso() == id){

                return cur;

            }

        }

    return null;

    }
    
}
