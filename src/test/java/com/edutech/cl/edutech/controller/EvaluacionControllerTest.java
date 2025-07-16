package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.model.Evaluacion;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.services.EvaluacionService;
import com.edutech.cl.edutech.assembler.EvaluacionModelAssembler;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EvaluacionController.class)
public class EvaluacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EvaluacionService evaluacionService;

    @MockBean
    private EvaluacionModelAssembler assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodos() throws Exception {
        Usuario usuario = new Usuario();
        Curso curso = new Curso();
        curso.setId_curso(1);
        curso.setNom_curso("Matemáticas");

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId_eva(1);
        evaluacion.setTitulo_eva("Examen Parcial");
        evaluacion.setDesc_eva("Evaluación de mitad de semestre");
        evaluacion.setStatus_eva("Activo");
        evaluacion.setUsuario(usuario);
        evaluacion.setCurso(curso);
        evaluacion.setFecha_evaluacion(new Date());

        EntityModel<Evaluacion> model = EntityModel.of(evaluacion);
        model.add(Link.of("/api/v1/evaluaciones/1").withSelfRel());

        when(evaluacionService.getEvaluaciones()).thenReturn(List.of(evaluacion));
        when(assembler.toModel(evaluacion)).thenReturn(model);

        mockMvc.perform(get("/api/v1/evaluaciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.evaluacionList[0].titulo_eva").value("Examen Parcial"));
    }

    @Test
    void testBuscarEvaluacionPorId() throws Exception {
        Usuario usuario = new Usuario();
        Curso curso = new Curso();
        curso.setId_curso(2);
        curso.setNom_curso("Historia");

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId_eva(2);
        evaluacion.setTitulo_eva("Trabajo Final");
        evaluacion.setDesc_eva("Trabajo final del curso");
        evaluacion.setStatus_eva("Completado");
        evaluacion.setUsuario(usuario);
        evaluacion.setCurso(curso);
        evaluacion.setFecha_evaluacion(new Date());

        EntityModel<Evaluacion> model = EntityModel.of(evaluacion);
        model.add(Link.of("/api/v1/evaluaciones/2").withSelfRel());

        when(evaluacionService.getEvaluacionId(2)).thenReturn(evaluacion);
        when(assembler.toModel(evaluacion)).thenReturn(model);

        mockMvc.perform(get("/api/v1/evaluaciones/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo_eva").value("Trabajo Final"));
    }

    @Test
    void testCrearEvaluacion() throws Exception {
        Usuario usuario = new Usuario();
        Curso curso = new Curso();
        curso.setId_curso(3);
        curso.setNom_curso("Programación");

        Evaluacion input = new Evaluacion();
        input.setTitulo_eva("Quiz 1");
        input.setDesc_eva("Quiz inicial");
        input.setStatus_eva("Pendiente");
        input.setUsuario(usuario);
        input.setCurso(curso);
        input.setFecha_evaluacion(new Date());

        Evaluacion creado = new Evaluacion();
        creado.setId_eva(3);
        creado.setTitulo_eva(input.getTitulo_eva());
        creado.setDesc_eva(input.getDesc_eva());
        creado.setStatus_eva(input.getStatus_eva());
        creado.setUsuario(usuario);
        creado.setCurso(curso);
        creado.setFecha_evaluacion(input.getFecha_evaluacion());

        EntityModel<Evaluacion> model = EntityModel.of(creado);
        model.add(Link.of("/api/v1/evaluaciones/3").withSelfRel());

        when(evaluacionService.crearEvaluacion(any(Evaluacion.class))).thenReturn(creado);
        when(assembler.toModel(creado)).thenReturn(model);

        mockMvc.perform(post("/api/v1/evaluaciones/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo_eva").value("Quiz 1"));
    }

    @Test
    void testActualizarEvaluacion() throws Exception {
        Usuario usuario = new Usuario();
        Curso curso = new Curso();
        curso.setId_curso(4);
        curso.setNom_curso("Física");

        Evaluacion input = new Evaluacion();
        input.setTitulo_eva("Examen Final");
        input.setDesc_eva("Examen final actualizado");
        input.setStatus_eva("Pendiente");
        input.setUsuario(usuario);
        input.setCurso(curso);
        input.setFecha_evaluacion(new Date());

        Evaluacion actualizado = new Evaluacion();
        actualizado.setId_eva(4);
        actualizado.setTitulo_eva(input.getTitulo_eva());
        actualizado.setDesc_eva(input.getDesc_eva());
        actualizado.setStatus_eva(input.getStatus_eva());
        actualizado.setUsuario(usuario);
        actualizado.setCurso(curso);
        actualizado.setFecha_evaluacion(input.getFecha_evaluacion());

        EntityModel<Evaluacion> model = EntityModel.of(actualizado);
        model.add(Link.of("/api/v1/evaluaciones/4").withSelfRel());

        when(evaluacionService.updateEvaluacion(eq(4), any(Evaluacion.class))).thenReturn(actualizado);
        when(assembler.toModel(actualizado)).thenReturn(model);

        mockMvc.perform(put("/api/v1/evaluaciones/actualizar/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo_eva").value("Examen Final"));
    }

    @Test
    void testEliminarEvaluacion() throws Exception {
        Usuario usuario = new Usuario();
        Curso curso = new Curso();
        curso.setId_curso(5);
        curso.setNom_curso("Química");

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setId_eva(5);
        evaluacion.setTitulo_eva("Evaluación para eliminar");
        evaluacion.setDesc_eva("Descripción");
        evaluacion.setStatus_eva("Inactivo");
        evaluacion.setUsuario(usuario);
        evaluacion.setCurso(curso);
        evaluacion.setFecha_evaluacion(new Date());

        EntityModel<Evaluacion> model = EntityModel.of(evaluacion);
        model.add(Link.of("/api/v1/evaluaciones/5").withSelfRel());

        when(evaluacionService.getEvaluacionId(5)).thenReturn(evaluacion);
        when(assembler.toModel(evaluacion)).thenReturn(model);

        mockMvc.perform(delete("/api/v1/evaluaciones/eliminar/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo_eva").value("Evaluación para eliminar"));
    }
}
