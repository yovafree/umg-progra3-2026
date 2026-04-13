package edu.umg.apirest.service;

import edu.umg.apirest.model.Curso;
import edu.umg.apirest.model.Estudiante;
import edu.umg.apirest.model.Inscripcion;
import edu.umg.apirest.repository.CursoRepository;
import edu.umg.apirest.repository.EstudianteRepository;
import edu.umg.apirest.repository.InscripcionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final CursoRepository cursoRepository;

    public InscripcionService(InscripcionRepository inscripcionRepository,
                              EstudianteRepository estudianteRepository,
                              CursoRepository cursoRepository) {
        this.inscripcionRepository = inscripcionRepository;
        this.estudianteRepository = estudianteRepository;
        this.cursoRepository = cursoRepository;
    }

    public List<Inscripcion> listarTodas() {
        return inscripcionRepository.findAll();
    }

    public Optional<Inscripcion> buscarPorId(Integer id) {
        return inscripcionRepository.findById(id);
    }

    public List<Inscripcion> buscarPorEstudiante(Integer codEstudiante) {
        return inscripcionRepository.findByEstudianteCodEstudiante(codEstudiante);
    }

    public List<Inscripcion> buscarPorCurso(Integer codCurso) {
        return inscripcionRepository.findByCursoCodCurso(codCurso);
    }

    public List<Inscripcion> buscarAprobados(Double notaMinima) {
        return inscripcionRepository.findAprobados(notaMinima);
    }

    @Transactional
    public Inscripcion inscribir(Integer codEstudiante, Integer codCurso) {
        Estudiante estudiante = estudianteRepository.findById(codEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + codEstudiante));

        Curso curso = cursoRepository.findById(codCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con ID: " + codCurso));

        Inscripcion inscripcion = new Inscripcion(estudiante, curso);
        inscripcion.setFecInscripcion(new Date());
        inscripcion.setEstado(1);
        return inscripcionRepository.save(inscripcion);
    }

    @Transactional
    public Optional<Inscripcion> asignarNota(Integer codInscripcion, Double nota) {
        return inscripcionRepository.findById(codInscripcion).map(insc -> {
            insc.setNotaFinal(nota);
            return inscripcionRepository.save(insc);
        });
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (inscripcionRepository.existsById(id)) {
            inscripcionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
