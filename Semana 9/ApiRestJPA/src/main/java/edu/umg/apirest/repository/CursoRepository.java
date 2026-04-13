package edu.umg.apirest.repository;

import edu.umg.apirest.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para la entidad Curso.
 * Spring Data JPA genera automáticamente las implementaciones CRUD.
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    // Ejemplo: buscar cursos por estado
    List<Curso> findByEstado(Integer estado);

    // Ejemplo: buscar cursos por nombre (contiene, ignorando mayúsculas)
    List<Curso> findByNomCursoContainingIgnoreCase(String nombre);

    // Ejemplo: consulta JPQL personalizada
    @Query("SELECT c FROM Curso c WHERE c.estado = :estado ORDER BY c.nomCurso ASC")
    List<Curso> findCursosActivosOrdenados(@Param("estado") Integer estado);
}
