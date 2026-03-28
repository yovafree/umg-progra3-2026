package edu.umg.consoleapp.model;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "curso", schema = "public")
public class Curso implements Serializable {

    @Id
    @Column(name = "cod_curso")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codCurso;

    @Column(name = "nom_curso", nullable = false)
    private String nomCurso;

    @Column(name = "estado")
    private int estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fec_creacion")
    private Date fecCreacion;

    public Curso() {
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getNomCurso() {
        return nomCurso;
    }

    public void setNomCurso(String nomCurso) {
        this.nomCurso = nomCurso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
        return "ID: " + codCurso + " Nombre: " + nomCurso;
    }
}
