package com.edutech.cl.edutech.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.edutech.cl.edutech.controller.PagoController;
import com.edutech.cl.edutech.model.Pago;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class PagoModelAssembler implements RepresentationModelAssembler<Pago, EntityModel<Pago>> {

    @Override
    public EntityModel<Pago> toModel(Pago pago) {
        return EntityModel.of(pago,
            linkTo(methodOn(PagoController.class).buscarPago(pago.getId_pago())).withSelfRel(),
            linkTo(methodOn(PagoController.class).obtenerTodos()).withRel("pagos")
        );
    }
}
