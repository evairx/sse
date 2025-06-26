package com.edutech.cl.edutech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edutech.cl.edutech.model.Curso;
import com.edutech.cl.edutech.model.Evaluacion;
import com.edutech.cl.edutech.services.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.edutech.cl.edutech.model.CursoModel;
import com.edutech.cl.edutech.assembler.CursoModelAssembler;
import org.springframework.hateoas.CollectionModel;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/v1/cursos")
@Tag(name = "Cursos", description = "Operaciones relacionadas con los Cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler cursoModelAssembler;


    @Autowired
    private com.edutech.cl.edutech.services.EvaluacionService evaluacionService;

    
    @GetMapping
    @Operation(summary = "Obtener todos los cursos", description = "Obtiene una lista de todos los cursos")
    public CollectionModel<CursoModel> obtenerTodos() {
        List<Curso> cursos = cursoService.getCursos();

            List<CursoModel> modelos = cursos.stream()
                .map(cursoModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(modelos,
            linkTo(methodOn(CursoController.class).obtenerTodos()).withSelfRel());
}

    @PostMapping("/crear")
    @Operation(summary = "Crear un curso", description = "Permite crear un curso")
    public ResponseEntity<CursoModel> crear(@RequestBody Curso curso) {
        Curso cursoCreado = cursoService.crearCurso(curso);
        CursoModel model = cursoModelAssembler.toModel(cursoCreado);

        return ResponseEntity
            .created(model.getRequiredLink("self").toUri())
            .body(model);
}

    @GetMapping("/{id}")
    @Operation(summary = "Buscar curso por ID", description = "Permite buscar un Curso por su ID")
    public ResponseEntity<CursoModel> buscar(@PathVariable("id") int id) {
        Curso curso = cursoService.getCursoId(id);
    
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        CursoModel model = cursoModelAssembler.toModel(curso);
        return ResponseEntity.ok(model);
}

    @DeleteMapping("/eliminar/{id}")
    @Operation(summary = "Eliminar curso por ID", description = "Permite eliminar un Curso por su ID")
    public ResponseEntity<CursoModel> eliminar(@PathVariable int id) {
        Curso curso = cursoService.getCursoId(id);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        List<Evaluacion> evaluaciones = evaluacionService.getEvaluaciones().stream()
            .filter(e -> e.getCurso().getId_curso().equals(curso.getId_curso()))
            .collect(Collectors.toList());
        evaluaciones.forEach(e -> evaluacionService.eliminarEvaluacion(e.getId_eva()));

        cursoService.eliminarCurso(id);

        CursoModel model = cursoModelAssembler.toModel(curso);
        return ResponseEntity.ok(model);
    }

    @PutMapping("/actualizar/{id}")
    @Operation(summary = "Actualizar Curso por ID", description = "Permite actualizar los datos de un curso mediante su ID")
    public ResponseEntity<CursoModel> actualizar(@PathVariable("id") int id, @RequestBody Curso curso) {
        Curso cursoActualizado = cursoService.updateCurso(id, curso);
    
        if (cursoActualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        CursoModel model = cursoModelAssembler.toModel(cursoActualizado);
        return ResponseEntity.ok(model);
    }

}