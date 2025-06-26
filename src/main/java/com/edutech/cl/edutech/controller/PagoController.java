package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.assembler.PagoModelAssembler;
import com.edutech.cl.edutech.model.Pago;
import com.edutech.cl.edutech.services.PagoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con los pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PagoModelAssembler assembler;

    @GetMapping
    @Operation(summary = "Obtener todos los pagos", description = "Permite obtener todos los pagos")
    public CollectionModel<EntityModel<Pago>> obtenerTodos() {
        List<EntityModel<Pago>> pagos = pagoService.getPagos().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(pagos,
            linkTo(methodOn(PagoController.class).obtenerTodos()).withSelfRel());
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear pago", description = "Permite crear un pago")
    public EntityModel<Pago> crear(@RequestBody Pago pago) {
        Pago nuevoPago = pagoService.crearPago(pago);
        return assembler.toModel(nuevoPago);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pago por ID", description = "Permite buscar un pago por ID")
    public EntityModel<Pago> buscarPago(@PathVariable("id") int id) {
        Pago pago = pagoService.getPagoId(id);
        return assembler.toModel(pago);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar pago por ID", description = "Permite eliminar un pago por ID")
    public EntityModel<Pago> borrar(@PathVariable("id") int id) {
        Pago pago = pagoService.getPagoId(id);
        pagoService.eliminarPago(id);
        return assembler.toModel(pago);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar pago mediante ID", description = "Permite actualizar los datos de un pago por ID")
    public EntityModel<Pago> actualizar(@PathVariable("id") int id, @RequestBody Pago pago) {
        Pago actualizado = pagoService.updatePago(id, pago);
        return assembler.toModel(actualizado);
    }
}
