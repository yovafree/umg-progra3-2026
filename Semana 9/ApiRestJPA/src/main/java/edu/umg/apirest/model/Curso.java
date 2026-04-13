package edu.umg.apirest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad Curso - mapeada a la tabla "curso" en PostgreSQL.
 * Reutiliza la misma tabla de la Semana 8.
 */
@Entity
@Table(name = "curso", schema = "public")
public class Curso implements Serializable {

    @Id
    @Column(name = "cod_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codCurso;

    @NotBlank(message = "El nombre del curso es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    @Column(name = "nom_curso", nullable = false)
    private String nomCurso;

    @Column(name = "estado")
    private Integer estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fec_creacion")
    private Date fecCreacion;

    public Curso() {
    }

    public Curso(String nomCurso, Integer estado) {
        this.nomCurso = nomCurso;
        this.estado = estado;
        this.fecCreacion = new Date();
    }

    // Getters y Setters

    public Integer getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(Integer codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    @Override
    public String toString() {
        return "Curso{codCurso=" + codCurso + ", nomCurso='" + nomCurso + "'}";
    }
}
