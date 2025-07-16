package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.model.CursoModel;
import com.edutech.cl.edutech.services.CursoService;
import com.edutech.cl.edutech.services.EvaluacionService;
import com.edutech.cl.edutech.assembler.CursoModelAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CursoService cursoService;

    @MockBean
    private EvaluacionService evaluacionService;

    @MockBean
    private CursoModelAssembler cursoModelAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodos() throws Exception {
        Curso curso = new Curso();
        curso.setId_curso(1);
        curso.setNom_curso("Matemáticas");

        CursoModel model = new CursoModel();
        model.setId(1);
        model.setNombre("Matemáticas");

        when(cursoService.getCursos()).thenReturn(List.of(curso));
        when(cursoModelAssembler.toModel(curso)).thenReturn(model);

        mockMvc.perform(get("/api/v1/cursos"))
                .andExpect(status().isOk());

    }

    @Test
    void testBuscarPorId_Encontrado() throws Exception {
        Curso curso = new Curso();
        curso.setId_curso(1);
        curso.setNom_curso("Historia");

        CursoModel model = new CursoModel();
        model.setId(1);
        model.setNombre("Historia");

        when(cursoService.getCursoId(1)).thenReturn(curso);
        when(cursoModelAssembler.toModel(curso)).thenReturn(model);

        mockMvc.perform(get("/api/v1/cursos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Historia"));
    }

    @Test
    void testBuscarPorId_NoEncontrado() throws Exception {
        when(cursoService.getCursoId(999)).thenReturn(null);

        mockMvc.perform(get("/api/v1/cursos/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrearCurso() throws Exception {
        Curso curso = new Curso();
        curso.setNom_curso("Programación");

        Curso cursoCreado = new Curso();
        cursoCreado.setId_curso(10);
        cursoCreado.setNom_curso("Programación");

        CursoModel model = new CursoModel();
        model.setId(10);
        model.setNombre("Programación");

        Link selfLink = Link.of("/api/v1/cursos/10").withSelfRel();
        model.add(selfLink);

        when(cursoService.crearCurso(any(Curso.class))).thenReturn(cursoCreado);
        when(cursoModelAssembler.toModel(cursoCreado)).thenReturn(model);

        mockMvc.perform(post("/api/v1/cursos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(curso)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/cursos/10"))
                .andExpect(jsonPath("$.nombre").value("Programación"));
    }
}
