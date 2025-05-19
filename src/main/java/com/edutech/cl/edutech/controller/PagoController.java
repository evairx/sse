package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.Pago;
import com.edutech.cl.edutech.services.PagoService;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;
    
    @GetMapping
    public List<Pago> obtenerTodos() {
        return pagoService.getPagos();
    }

    @PostMapping("/crear")
    public Pago crear(@RequestBody Pago pago) {
        pagoService.crearPago(pago);
        return pago;
    }
    
    @GetMapping("/{id}")
    public Pago buscarPago(@PathVariable("id") int id) {
        Pago pago = pagoService.getPagoId(id);
        return pago;
    }

    @DeleteMapping("/eliminar/{id}")
    public Pago borrar(@PathVariable("id") int id) {
        Pago pago = pagoService.getPagoId(id);
        pagoService.eliminarPago(id);
        return pago;
    }
    
    @PutMapping("/actualizar/{id}")
    public Pago actualizar(@PathVariable("id") int id, @RequestBody Pago pago) {
        pagoService.updatePago(id, pago);
        return pago;
    }
}