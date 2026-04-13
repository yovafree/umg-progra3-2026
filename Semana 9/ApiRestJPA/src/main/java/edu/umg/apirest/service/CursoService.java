package edu.umg.apirest.service;

import edu.umg.apirest.model.Curso;
import edu.umg.apirest.repository.CursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    // Listar todos los cursos
    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    // Buscar curso por ID
    public Optional<Curso> buscarPorId(Integer id) {
        return cursoRepository.findById(id);
    }

    // Buscar cursos activos
    public List<Curso> buscarActivos() {
        return cursoRepository.findByEstado(1);
    }

    // Buscar cursos por nombre
    public List<Curso> buscarPorNombre(String nombre) {
        return cursoRepository.findByNomCursoContainingIgnoreCase(nombre);
    }

    // Crear un nuevo curso
    @Transactional
    public Curso crear(Curso curso) {
        curso.setFecCreacion(new Date());
        return cursoRepository.save(curso);
    }

    // Actualizar un curso existente
    @Transactional
    public Optional<Curso> actualizar(Integer id, Curso cursoActualizado) {
        return cursoRepository.findById(id).map(curso -> {
            curso.setNomCurso(cursoActualizado.getNomCurso());
            curso.setEstado(cursoActualizado.getEstado());
            return cursoRepository.save(curso);
        });
    }

    // Eliminar un curso
    @Transactional
    public boolean eliminar(Integer id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
