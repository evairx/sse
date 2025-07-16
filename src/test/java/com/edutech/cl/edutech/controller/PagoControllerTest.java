package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.model.Pago;
import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.services.PagoService;
import com.edutech.cl.edutech.assembler.PagoModelAssembler;

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

@WebMvcTest(PagoController.class)
public class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagoService pagoService;

    @MockBean
    private PagoModelAssembler assembler;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testObtenerTodos() throws Exception {
        Usuario usuario = new Usuario(); // Ajusta si necesitas campos mínimos

        Pago pago = new Pago();
        pago.setId_pago(1);
        pago.setUsuario(usuario);
        pago.setFecha_pago(new Date());
        pago.setFecha_prox_pago(new Date());
        pago.setStatus_pago("Activo");

        EntityModel<Pago> model = EntityModel.of(pago);
        model.add(Link.of("/api/v1/pagos/1").withSelfRel());

        when(pagoService.getPagos()).thenReturn(List.of(pago));
        when(assembler.toModel(pago)).thenReturn(model);

        mockMvc.perform(get("/api/v1/pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_embedded.pagoList[0].status_pago").value("Activo"));
    }

    @Test
    void testBuscarPagoPorId() throws Exception {
        Usuario usuario = new Usuario();

        Pago pago = new Pago();
        pago.setId_pago(2);
        pago.setUsuario(usuario);
        pago.setFecha_pago(new Date());
        pago.setFecha_prox_pago(new Date());
        pago.setStatus_pago("Pendiente");

        EntityModel<Pago> model = EntityModel.of(pago);
        model.add(Link.of("/api/v1/pagos/2").withSelfRel());

        when(pagoService.getPagoId(2)).thenReturn(pago);
        when(assembler.toModel(pago)).thenReturn(model);

        mockMvc.perform(get("/api/v1/pagos/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_pago").value("Pendiente"));
    }

    @Test
    void testCrearPago() throws Exception {
        Usuario usuario = new Usuario();

        Pago input = new Pago();
        input.setUsuario(usuario);
        input.setFecha_pago(new Date());
        input.setFecha_prox_pago(new Date());
        input.setStatus_pago("Pagado");

        Pago creado = new Pago();
        creado.setId_pago(3);
        creado.setUsuario(usuario);
        creado.setFecha_pago(input.getFecha_pago());
        creado.setFecha_prox_pago(input.getFecha_prox_pago());
        creado.setStatus_pago(input.getStatus_pago());

        EntityModel<Pago> model = EntityModel.of(creado);
        model.add(Link.of("/api/v1/pagos/3").withSelfRel());

        when(pagoService.crearPago(any(Pago.class))).thenReturn(creado);
        when(assembler.toModel(creado)).thenReturn(model);

        mockMvc.perform(post("/api/v1/pagos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_pago").value("Pagado"));
    }

    @Test
    void testActualizarPago() throws Exception {
        Usuario usuario = new Usuario();

        Pago input = new Pago();
        input.setUsuario(usuario);
        input.setFecha_pago(new Date());
        input.setFecha_prox_pago(new Date());
        input.setStatus_pago("Retrasado");

        Pago actualizado = new Pago();
        actualizado.setId_pago(4);
        actualizado.setUsuario(usuario);
        actualizado.setFecha_pago(input.getFecha_pago());
        actualizado.setFecha_prox_pago(input.getFecha_prox_pago());
        actualizado.setStatus_pago(input.getStatus_pago());

        EntityModel<Pago> model = EntityModel.of(actualizado);
        model.add(Link.of("/api/v1/pagos/4").withSelfRel());

        when(pagoService.updatePago(eq(4), any(Pago.class))).thenReturn(actualizado);
        when(assembler.toModel(actualizado)).thenReturn(model);

        mockMvc.perform(put("/api/v1/pagos/actualizar/4")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_pago").value("Retrasado"));
    }

    @Test
    void testEliminarPago() throws Exception {
        Usuario usuario = new Usuario();

        Pago pago = new Pago();
        pago.setId_pago(5);
        pago.setUsuario(usuario);
        pago.setFecha_pago(new Date());
        pago.setFecha_prox_pago(new Date());
        pago.setStatus_pago("Cancelado");

        EntityModel<Pago> model = EntityModel.of(pago);
        model.add(Link.of("/api/v1/pagos/5").withSelfRel());

        when(pagoService.getPagoId(5)).thenReturn(pago);
        when(assembler.toModel(pago)).thenReturn(model);

        mockMvc.perform(delete("/api/v1/pagos/eliminar/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status_pago").value("Cancelado"));
    }
}
