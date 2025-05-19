package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.model.Evaluacion;
import com.edutech.cl.edutech.model.Pago;
import com.edutech.cl.edutech.services.UsuarioService;
import com.edutech.cl.edutech.services.PagoService;
import com.edutech.cl.edutech.services.EvaluacionService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private PagoService pagoService;
    
    @Autowired
    private EvaluacionService evaluacionService;
    

    @GetMapping
    public List<Usuario> obtenerTodos() {
        return usuarioService.conseguir();
    }

    @PostMapping("/crear")
    public Usuario crear(@RequestBody Usuario usuario) {
        return usuarioService.crear(usuario);
    }
    
    @GetMapping("/{id}")
    public Usuario buscar(@PathVariable("id") int id) {
        return usuarioService.conseguirPorId(id);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.conseguirPorId(id);
            
            if (usuario == null) {
                Map<String, Object> error = new HashMap<>();
                error.put("mensaje", "Usuario no encontrado");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }

            List<Evaluacion> evaluaciones = evaluacionService.getEvaluaciones();
            
            List<Evaluacion> evaluacionesUsuario = evaluaciones.stream()
                .filter(e -> e.getUsuario().getId_usuario() == usuario.getId_usuario())
                .collect(Collectors.toList());
            
            for (Evaluacion evaluacion : evaluacionesUsuario) {
                evaluacionService.eliminarEvaluacion(evaluacion.getId_eva());
            }
            
            List<Pago> pagos = pagoService.getPagos();
            
            List<Pago> pagosUsuario = pagos.stream()
                .filter(p -> p.getUsuario().getId_usuario() == usuario.getId_usuario())
                .collect(Collectors.toList());
            
            for (Pago pago : pagosUsuario) {
                pagoService.eliminarPago(pago.getId_pago());
            }

            usuarioService.eliminar(id);

            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Usuario y sus pagos eliminados correctamente");
            response.put("pagosEliminados", pagosUsuario.size());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("mensaje", "Ocurrió un error al intentar eliminar el usuario");
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
        
        if (usuarioActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        
        return ResponseEntity.ok(usuarioActualizado);
    }
}