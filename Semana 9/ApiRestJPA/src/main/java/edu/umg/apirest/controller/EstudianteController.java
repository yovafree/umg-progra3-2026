package edu.umg.apirest.controller;

import edu.umg.apirest.model.Estudiante;
import edu.umg.apirest.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller REST para Estudiantes.
 *
 * Endpoints:
 *   GET    /api/estudiantes              - Listar todos
 *   GET    /api/estudiantes/{id}         - Obtener por ID
 *   GET    /api/estudiantes/carnet/{c}   - Buscar por carnet
 *   GET    /api/estudiantes/buscar?q=... - Buscar por nombre/apellido
 *   POST   /api/estudiantes              - Crear nuevo
 *   PUT    /api/estudiantes/{id}         - Actualizar
 *   DELETE /api/estudiantes/{id}         - Eliminar
 */
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public List<Estudiante> listarTodos() {
        return estudianteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerPorId(@PathVariable Integer id) {
        return estudianteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/carnet/{carnet}")
    public ResponseEntity<Estudiante> buscarPorCarnet(@PathVariable String carnet) {
        return estudianteService.buscarPorCarnet(carnet)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Estudiante> buscar(@RequestParam("q") String termino) {
        return estudianteService.buscar(termino);
    }

    @PostMapping
    public ResponseEntity<Estudiante> crear(@Valid @RequestBody Estudiante estudiante) {
        Estudiante creado = estudianteService.crear(estudiante);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizar(@PathVariable Integer id,
                                                  @Valid @RequestBody Estudiante estudiante) {
        return estudianteService.actualizar(id, estudiante)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        if (estudianteService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Estudiante eliminado correctamente"));
        }
        return ResponseEntity.notFound().build();
    }
}
