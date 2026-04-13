package edu.umg.apirest.controller;

import edu.umg.apirest.model.Curso;
import edu.umg.apirest.service.CursoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller REST para Cursos.
 *
 * Endpoints:
 *   GET    /api/cursos              - Listar todos
 *   GET    /api/cursos/{id}         - Obtener por ID
 *   GET    /api/cursos/activos      - Listar activos
 *   GET    /api/cursos/buscar?q=... - Buscar por nombre
 *   POST   /api/cursos              - Crear nuevo
 *   PUT    /api/cursos/{id}         - Actualizar
 *   DELETE /api/cursos/{id}         - Eliminar
 */
@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> listarTodos() {
        return cursoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> obtenerPorId(@PathVariable Integer id) {
        return cursoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/activos")
    public List<Curso> listarActivos() {
        return cursoService.buscarActivos();
    }

    @GetMapping("/buscar")
    public List<Curso> buscarPorNombre(@RequestParam("q") String nombre) {
        return cursoService.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Curso> crear(@Valid @RequestBody Curso curso) {
        Curso creado = cursoService.crear(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> actualizar(@PathVariable Integer id, @Valid @RequestBody Curso curso) {
        return cursoService.actualizar(id, curso)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        if (cursoService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Curso eliminado correctamente"));
        }
        return ResponseEntity.notFound().build();
    }
}
