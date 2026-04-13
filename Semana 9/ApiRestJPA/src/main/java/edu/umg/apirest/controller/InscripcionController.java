package edu.umg.apirest.controller;

import edu.umg.apirest.model.Inscripcion;
import edu.umg.apirest.service.InscripcionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller REST para Inscripciones.
 * Demuestra el manejo de relaciones entre entidades en una API.
 *
 * Endpoints:
 *   GET    /api/inscripciones                         - Listar todas
 *   GET    /api/inscripciones/{id}                    - Obtener por ID
 *   GET    /api/inscripciones/estudiante/{codEst}     - Por estudiante
 *   GET    /api/inscripciones/curso/{codCurso}        - Por curso
 *   GET    /api/inscripciones/aprobados?nota=61       - Aprobados con nota >= X
 *   POST   /api/inscripciones?codEstudiante=1&codCurso=2 - Inscribir
 *   PUT    /api/inscripciones/{id}/nota?valor=85.5    - Asignar nota
 *   DELETE /api/inscripciones/{id}                    - Eliminar
 */
@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

    @GetMapping
    public List<Inscripcion> listarTodas() {
        return inscripcionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerPorId(@PathVariable Integer id) {
        return inscripcionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estudiante/{codEstudiante}")
    public List<Inscripcion> porEstudiante(@PathVariable Integer codEstudiante) {
        return inscripcionService.buscarPorEstudiante(codEstudiante);
    }

    @GetMapping("/curso/{codCurso}")
    public List<Inscripcion> porCurso(@PathVariable Integer codCurso) {
        return inscripcionService.buscarPorCurso(codCurso);
    }

    @GetMapping("/aprobados")
    public List<Inscripcion> aprobados(@RequestParam(defaultValue = "61") Double nota) {
        return inscripcionService.buscarAprobados(nota);
    }

    @PostMapping
    public ResponseEntity<Inscripcion> inscribir(@RequestParam Integer codEstudiante,
                                                  @RequestParam Integer codCurso) {
        Inscripcion inscripcion = inscripcionService.inscribir(codEstudiante, codCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(inscripcion);
    }

    @PutMapping("/{id}/nota")
    public ResponseEntity<Inscripcion> asignarNota(@PathVariable Integer id,
                                                    @RequestParam Double valor) {
        return inscripcionService.asignarNota(id, valor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminar(@PathVariable Integer id) {
        if (inscripcionService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Inscripción eliminada correctamente"));
        }
        return ResponseEntity.notFound().build();
    }
}
