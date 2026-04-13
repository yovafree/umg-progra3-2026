package edu.umg.apirest.repository;

import edu.umg.apirest.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Inscripcion.
 * Demuestra consultas con relaciones.
 */
@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Integer> {

    // Inscripciones por estudiante
    List<Inscripcion> findByEstudianteCodEstudiante(Integer codEstudiante);

    // Inscripciones por curso
    List<Inscripcion> findByCursoCodCurso(Integer codCurso);

    // Consulta JPQL: inscripciones con nota mayor a X
    @Query("SELECT i FROM Inscripcion i WHERE i.notaFinal >= :nota")
    List<Inscripcion> findAprobados(@Param("nota") Double nota);
}
