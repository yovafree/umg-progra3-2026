package edu.umg.apirest.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad Inscripcion - ejemplo de relación ManyToOne entre Estudiante y Curso.
 * Demuestra cómo modelar relaciones en JPA.
 */
@Entity
@Table(name = "inscripcion", schema = "public")
public class Inscripcion implements Serializable {

    @Id
    @Column(name = "cod_inscripcion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codInscripcion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_estudiante", nullable = false)
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cod_curso", nullable = false)
    private Curso curso;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fec_inscripcion")
    private Date fecInscripcion;

    @Column(name = "nota_final")
    private Double notaFinal;

    @Column(name = "estado")
    private Integer estado;

    public Inscripcion() {
    }

    public Inscripcion(Estudiante estudiante, Curso curso) {
        this.estudiante = estudiante;
        this.curso = curso;
        this.fecInscripcion = new Date();
        this.estado = 1;
    }

    // Getters y Setters

    public Integer getCodInscripcion() {
        return codInscripcion;
    }

    public void setCodInscripcion(Integer codInscripcion) {
        this.codInscripcion = codInscripcion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Date getFecInscripcion() {
        return fecInscripcion;
    }

    public void setFecInscripcion(Date fecInscripcion) {
        this.fecInscripcion = fecInscripcion;
    }

    public Double getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Double notaFinal) {
        this.notaFinal = notaFinal;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Inscripcion{codInscripcion=" + codInscripcion + "}";
    }
}
