package edu.umg.apirest.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad Estudiante - ejemplo adicional de entidad JPA.
 * Se crea automáticamente en la base de datos con ddl-auto=update.
 */
@Entity
@Table(name = "estudiante", schema = "public")
public class Estudiante implements Serializable {

    @Id
    @Column(name = "cod_estudiante")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codEstudiante;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 80, message = "El nombre no puede exceder 80 caracteres")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 80, message = "El apellido no puede exceder 80 caracteres")
    @Column(name = "apellido", nullable = false)
    private String apellido;

    @Email(message = "El email debe ser válido")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "carnet", unique = true)
    private String carnet;

    @Column(name = "estado")
    private Integer estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fec_creacion")
    private Date fecCreacion;

    public Estudiante() {
    }

    public Estudiante(String nombre, String apellido, String email, String carnet) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.carnet = carnet;
        this.estado = 1;
        this.fecCreacion = new Date();
    }

    // Getters y Setters

    public Integer getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(Integer codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
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
        return "Estudiante{codEstudiante=" + codEstudiante + ", nombre='" + nombre + " " + apellido + "'}";
    }
}
