package edu.umg.apirest.service;

import edu.umg.apirest.model.Estudiante;
import edu.umg.apirest.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> listarTodos() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiante> buscarPorId(Integer id) {
        return estudianteRepository.findById(id);
    }

    public Optional<Estudiante> buscarPorCarnet(String carnet) {
        return estudianteRepository.findByCarnet(carnet);
    }

    public List<Estudiante> buscar(String termino) {
        return estudianteRepository
                .findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(termino, termino);
    }

    @Transactional
    public Estudiante crear(Estudiante estudiante) {
        estudiante.setFecCreacion(new Date());
        if (estudiante.getEstado() == null) {
            estudiante.setEstado(1);
        }
        return estudianteRepository.save(estudiante);
    }

    @Transactional
    public Optional<Estudiante> actualizar(Integer id, Estudiante datos) {
        return estudianteRepository.findById(id).map(est -> {
            est.setNombre(datos.getNombre());
            est.setApellido(datos.getApellido());
            est.setEmail(datos.getEmail());
            est.setCarnet(datos.getCarnet());
            est.setEstado(datos.getEstado());
            return estudianteRepository.save(est);
        });
    }

    @Transactional
    public boolean eliminar(Integer id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
