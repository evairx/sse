package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.model.UsuarioModel;
import com.edutech.cl.edutech.services.UsuarioService;
import com.edutech.cl.edutech.services.PagoService;
import com.edutech.cl.edutech.services.EvaluacionService;
import com.edutech.cl.edutech.assembler.UsuarioModelAssembler;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @MockBean
    private PagoService pagoService;

    @MockBean
    private EvaluacionService evaluacionService;

    @MockBean
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodosUsuarios() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(1);
        usuario.setPnombre_usu("Juan");
        usuario.setSnombre_usu("Carlos");
        usuario.setAppaterno_usu("Perez");
        usuario.setApmaterno_usu("Lopez");
        usuario.setCorreo_usu("juan.perez@example.com");
        usuario.setTipo_rol_usu("admin");
        usuario.setFec_registro_usu(new Date());

        UsuarioModel model = new UsuarioModel();
        model.setId_usuario(1);
        model.setPnombre_usu("Juan");
        model.setSnombre_usu("Carlos");
        model.setAppaterno_usu("Perez");
        model.setApmaterno_usu("Lopez");
        model.setCorreo_usu("juan.perez@example.com");
        model.setTipo_rol_usu("admin");
        model.setFec_registro_usu(usuario.getFec_registro_usu());
        model.add(Link.of("/api/v1/usuario/1").withSelfRel());

        when(usuarioService.conseguir()).thenReturn(List.of(usuario));
        when(usuarioModelAssembler.toModel(usuario)).thenReturn(model);

        mockMvc.perform(get("/api/v1/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.usuarioModelList[0].pnombre_usu").value("Juan"))
                .andExpect(jsonPath("_embedded.usuarioModelList[0].correo_usu").value("juan.perez@example.com"));
    }

    @Test
    void testBuscarUsuarioPorId_Encontrado() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(2);
        usuario.setPnombre_usu("Ana");
        usuario.setSnombre_usu("Maria");
        usuario.setAppaterno_usu("Gomez");
        usuario.setApmaterno_usu("Diaz");
        usuario.setCorreo_usu("ana.gomez@example.com");
        usuario.setTipo_rol_usu("user");
        usuario.setFec_registro_usu(new Date());

        UsuarioModel model = new UsuarioModel();
        model.setId_usuario(2);
        model.setPnombre_usu("Ana");
        model.setSnombre_usu("Maria");
        model.setAppaterno_usu("Gomez");
        model.setApmaterno_usu("Diaz");
        model.setCorreo_usu("ana.gomez@example.com");
        model.setTipo_rol_usu("user");
        model.setFec_registro_usu(usuario.getFec_registro_usu());
        model.add(Link.of("/api/v1/usuario/2").withSelfRel());

        when(usuarioService.conseguirPorId(2)).thenReturn(usuario);
        when(usuarioModelAssembler.toModel(usuario)).thenReturn(model);

        mockMvc.perform(get("/api/v1/usuario/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pnombre_usu").value("Ana"))
                .andExpect(jsonPath("$.correo_usu").value("ana.gomez@example.com"));
    }

    @Test
    void testBuscarUsuarioPorId_NoEncontrado() throws Exception {
        when(usuarioService.conseguirPorId(999)).thenReturn(null);

        mockMvc.perform(get("/api/v1/usuario/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testCrearUsuario() throws Exception {
        Usuario input = new Usuario();
        input.setPnombre_usu("Luis");
        input.setSnombre_usu("Miguel");
        input.setAppaterno_usu("Ramirez");
        input.setApmaterno_usu("Soto");
        input.setCorreo_usu("luis.ramirez@example.com");
        input.setTipo_rol_usu("user");
        input.setFec_registro_usu(new Date());

        Usuario creado = new Usuario();
        creado.setId_usuario(3);
        creado.setPnombre_usu(input.getPnombre_usu());
        creado.setSnombre_usu(input.getSnombre_usu());
        creado.setAppaterno_usu(input.getAppaterno_usu());
        creado.setApmaterno_usu(input.getApmaterno_usu());
        creado.setCorreo_usu(input.getCorreo_usu());
        creado.setTipo_rol_usu(input.getTipo_rol_usu());
        creado.setFec_registro_usu(input.getFec_registro_usu());

        UsuarioModel model = new UsuarioModel();
        model.setId_usuario(3);
        model.setPnombre_usu(creado.getPnombre_usu());
        model.setSnombre_usu(creado.getSnombre_usu());
        model.setAppaterno_usu(creado.getAppaterno_usu());
        model.setApmaterno_usu(creado.getApmaterno_usu());
        model.setCorreo_usu(creado.getCorreo_usu());
        model.setTipo_rol_usu(creado.getTipo_rol_usu());
        model.setFec_registro_usu(creado.getFec_registro_usu());
        model.add(Link.of("/api/v1/usuario/3").withSelfRel());

        when(usuarioService.crear(any(Usuario.class))).thenReturn(creado);
        when(usuarioModelAssembler.toModel(creado)).thenReturn(model);

        mockMvc.perform(post("/api/v1/usuario/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/usuario/3"))
                .andExpect(jsonPath("$.pnombre_usu").value("Luis"))
                .andExpect(jsonPath("$.correo_usu").value("luis.ramirez@example.com"));
    }

    @Test
    void testActualizarUsuario() throws Exception {
        Usuario input = new Usuario();
        input.setPnombre_usu("Carla");
        input.setSnombre_usu("Elena");
        input.setAppaterno_usu("Morales");
        input.setApmaterno_usu("Perez");
        input.setCorreo_usu("carla.morales@example.com");
        input.setTipo_rol_usu("user");
        input.setFec_registro_usu(new Date());

        Usuario actualizado = new Usuario();
        actualizado.setId_usuario(4);
        actualizado.setPnombre_usu(input.getPnombre_usu());
        actualizado.setSnombre_usu(input.getSnombre_usu());
        actualizado.setAppaterno_usu(input.getAppaterno_usu());
        actualizado.setApmaterno_usu(input.getApmaterno_usu());
        actualizado.setCorreo_usu(input.getCorreo_usu());
        actualizado.setTipo_rol_usu(input.getTipo_rol_usu());
        actualizado.setFec_registro_usu(input.getFec_registro_usu());

        UsuarioModel model = new UsuarioModel();
        model.setId_usuario(4);
        model.setPnombre_usu(actualizado.getPnombre_usu());
        model.setSnombre_usu(actualizado.getSnombre_usu());
        model.setAppaterno_usu(actualizado.getAppaterno_usu());
        model.setApmaterno_usu(actualizado.getApmaterno_usu());
        model.setCorreo_usu(actualizado.getCorreo_usu());
        model.setTipo_rol_usu(actualizado.getTipo_rol_usu());
        model.setFec_registro_usu(actualizado.getFec_registro_usu());
        model.add(Link.of("/api/v1/usuario/4").withSelfRel());

        when(usuarioService.actualizar(eq(4), any(Usuario.class))).thenReturn(actualizado);
        when(usuarioModelAssembler.toModel(actualizado)).thenReturn(model);

        mockMvc.perform(put("/api/v1/usuario/actualizar/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pnombre_usu").value("Carla"))
                .andExpect(jsonPath("$.correo_usu").value("carla.morales@example.com"));
    }

    @Test
    void testEliminarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setId_usuario(5);
        usuario.setPnombre_usu("Sofia");
        usuario.setSnombre_usu("Isabel");
        usuario.setAppaterno_usu("Ramirez");
        usuario.setApmaterno_usu("Ortiz");
        usuario.setCorreo_usu("sofia.ramirez@example.com");
        usuario.setTipo_rol_usu("admin");
        usuario.setFec_registro_usu(new Date());

        when(usuarioService.conseguirPorId(5)).thenReturn(usuario);
        doNothing().when(evaluacionService).eliminarEvaluacion(any(Integer.class));
        doNothing().when(pagoService).eliminarPago(any(Integer.class));
        doNothing().when(usuarioService).eliminar(5);

        UsuarioModel model = new UsuarioModel();
        model.setId_usuario(5);
        model.setPnombre_usu("Sofia");
        model.setSnombre_usu("Isabel");
        model.setAppaterno_usu("Ramirez");
        model.setApmaterno_usu("Ortiz");
        model.setCorreo_usu("sofia.ramirez@example.com");
        model.setTipo_rol_usu("admin");
        model.setFec_registro_usu(usuario.getFec_registro_usu());
        model.add(Link.of("/api/v1/usuario/5").withSelfRel());

        when(usuarioModelAssembler.toModel(usuario)).thenReturn(model);

        mockMvc.perform(delete("/api/v1/usuario/eliminar/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.pnombre_usu").value("Sofia"))
                .andExpect(jsonPath("$.correo_usu").value("sofia.ramirez@example.com"));
    }
}
