package edu.umg.apirest.repository;

import edu.umg.apirest.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para la entidad Estudiante.
 */
@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    // Buscar por carnet
    Optional<Estudiante> findByCarnet(String carnet);

    // Buscar por email
    Optional<Estudiante> findByEmail(String email);

    // Buscar por nombre o apellido
    List<Estudiante> findByNombreContainingIgnoreCaseOrApellidoContainingIgnoreCase(String nombre, String apellido);

    // Buscar estudiantes activos
    List<Estudiante> findByEstado(Integer estado);
}
