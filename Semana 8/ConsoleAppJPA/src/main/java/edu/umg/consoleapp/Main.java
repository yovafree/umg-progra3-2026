package edu.umg.consoleapp;

import edu.umg.consoleapp.model.Curso;
import jakarta.persistence.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ConsoleAppJPA_PU");
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Successfully connected to the PostgreSQL database!");

            // Listar todos los cursos
            List<Curso> cursos = em.createQuery("SELECT c FROM Curso c", Curso.class)
                    .getResultList();

            for (Curso curso : cursos) {
                System.out.println("ID: " + curso.getCodCurso() + " Nombre: " + curso.getNomCurso());
            }

            System.out.println("Total cursos: " + cursos.size());

            // Eliminar curso con cod_curso = 1
            em.getTransaction().begin();
            Curso cursoEliminar = em.find(Curso.class, 1);
            if (cursoEliminar != null) {
                em.remove(cursoEliminar);
                System.out.println("Curso con ID 1 eliminado.");
            }
            em.getTransaction().commit();

            // Actualizar curso con cod_curso = 2
            em.getTransaction().begin();
            Curso cursoActualizar = em.find(Curso.class, 2);
            if (cursoActualizar != null) {
                cursoActualizar.setNomCurso("Programación III - Actualizado 2");
                cursoActualizar.setEstado(0);
                em.merge(cursoActualizar);
                System.out.println("Curso con ID 2 actualizado.");
            }
            em.getTransaction().commit();

            // Insertar un nuevo curso (comentado, igual que en el proyecto .NET)
            // em.getTransaction().begin();
            // Curso cursoNuevo = new Curso();
            // cursoNuevo.setNomCurso("Curso de Java");
            // cursoNuevo.setEstado(1);
            // // cursoNuevo.setFecCreacion(new Date());
            // em.persist(cursoNuevo);
            // em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
