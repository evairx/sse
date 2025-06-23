package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.Pago;
import com.edutech.cl.edutech.services.PagoService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/pagos")
@Tag(name = "Pagos", description = "Operaciones relacionadas con los pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;
    
    @GetMapping
    @Operation(summary = "Obtener todos los pagos", description = "Permite obtener todos los pagos")
    public List<Pago> obtenerTodos() {
        return pagoService.getPagos();
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear pago", description = "Permite crear un pago")
    public Pago crear(@RequestBody Pago pago) {
        pagoService.crearPago(pago);
        return pago;
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar pago por ID", description = "Permite buscar un pago por ID")
    public Pago buscarPago(@PathVariable("id") int id) {
        Pago pago = pagoService.getPagoId(id);
        return pago;
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar pago por ID", description = "Permite eliminar un pago por ID")
    public Pago borrar(@PathVariable("id") int id) {
        Pago pago = pagoService.getPagoId(id);
        pagoService.eliminarPago(id);
        return pago;
    }
    
    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar pago mediante ID", description = "Permite actualizar los datos de un pago por ID")
    public Pago actualizar(@PathVariable("id") int id, @RequestBody Pago pago) {
        pagoService.updatePago(id, pago);
        return pago;
    }
}