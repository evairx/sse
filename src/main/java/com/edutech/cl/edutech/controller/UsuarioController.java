package com.edutech.cl.edutech.controller;

import com.edutech.cl.edutech.assembler.UsuarioModelAssembler;
import com.edutech.cl.edutech.model.Evaluacion;
import com.edutech.cl.edutech.model.Pago;
import com.edutech.cl.edutech.model.Usuario;
import com.edutech.cl.edutech.model.UsuarioModel;
import com.edutech.cl.edutech.services.EvaluacionService;
import com.edutech.cl.edutech.services.PagoService;
import com.edutech.cl.edutech.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v1/usuario")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con los usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PagoService pagoService;

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    @Operation(summary = "Obtener todos los usuarios", description = "Permite obtener todos los usuarios")
    public CollectionModel<UsuarioModel> obtenerTodos() {
        List<Usuario> usuarios = usuarioService.conseguir();

        List<UsuarioModel> modelos = usuarios.stream()
                .map(usuarioModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(modelos,
                linkTo(methodOn(UsuarioController.class).obtenerTodos()).withSelfRel());
    }

    @PostMapping("/crear")
    @Operation(summary = "Crear usuario", description = "Permite crear un usuario")
    public ResponseEntity<UsuarioModel> crear(@RequestBody Usuario usuario) {
        Usuario creado = usuarioService.crear(usuario);
        UsuarioModel model = usuarioModelAssembler.toModel(creado);

        return ResponseEntity
                .created(model.getRequiredLink("self").toUri())
                .body(model);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuario por ID", description = "Permite buscar un usuario por ID")
    public ResponseEntity<UsuarioModel> buscar(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.conseguirPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        UsuarioModel model = usuarioModelAssembler.toModel(usuario);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar usuario mediante ID", description = "Permite actualizar los datos de un usuario por ID")
    public ResponseEntity<UsuarioModel> actualizar(@PathVariable int id, @RequestBody Usuario usuario) {
        Usuario actualizado = usuarioService.actualizar(id, usuario);
        if (actualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UsuarioModel model = usuarioModelAssembler.toModel(actualizado);
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar usuario por ID", description = "Permite eliminar un usuario por ID")
    public ResponseEntity<UsuarioModel> eliminar(@PathVariable int id) {
        Usuario usuario = usuarioService.conseguirPorId(id);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }

        List<Evaluacion> evaluaciones = evaluacionService.getEvaluaciones().stream()
                .filter(e -> e.getUsuario().getId_usuario() == usuario.getId_usuario())
                .collect(Collectors.toList());
        evaluaciones.forEach(e -> evaluacionService.eliminarEvaluacion(e.getId_eva()));

        List<Pago> pagos = pagoService.getPagos().stream()
                .filter(p -> p.getUsuario().getId_usuario() == usuario.getId_usuario())
                .collect(Collectors.toList());
        pagos.forEach(p -> pagoService.eliminarPago(p.getId_pago()));

        usuarioService.eliminar(id);

        UsuarioModel model = usuarioModelAssembler.toModel(usuario);
        return ResponseEntity.ok(model);
    }
}
