using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace FrontendMVC.Models;

/// <summary>
/// Modelo que corresponde a la entidad Inscripcion de la API Java.
/// </summary>
public class Inscripcion
{
    [JsonPropertyName("codInscripcion")]
    public int? CodInscripcion { get; set; }

    [JsonPropertyName("estudiante")]
    public Estudiante? Estudiante { get; set; }

    [JsonPropertyName("curso")]
    public Curso? Curso { get; set; }

    [Display(Name = "Fecha de Inscripción")]
    [JsonPropertyName("fecInscripcion")]
    public DateTime? FecInscripcion { get; set; }

    [Display(Name = "Nota Final")]
    [JsonPropertyName("notaFinal")]
    public double? NotaFinal { get; set; }

    [Display(Name = "Estado")]
    [JsonPropertyName("estado")]
    public int? Estado { get; set; }

    public string EstadoTexto => Estado == 1 ? "Activa" : "Inactiva";
}

/// <summary>
/// ViewModel para crear una inscripción (solo envía IDs).
/// </summary>
public class InscripcionCreateViewModel
{
    [Required(ErrorMessage = "Seleccione un estudiante")]
    [Display(Name = "Estudiante")]
    public int CodEstudiante { get; set; }

    [Required(ErrorMessage = "Seleccione un curso")]
    [Display(Name = "Curso")]
    public int CodCurso { get; set; }
}

/// <summary>
/// ViewModel para asignar nota.
/// </summary>
public class AsignarNotaViewModel
{
    public int CodInscripcion { get; set; }
    public string? NombreEstudiante { get; set; }
    public string? NombreCurso { get; set; }

    [Required(ErrorMessage = "La nota es obligatoria")]
    [Range(0, 100, ErrorMessage = "La nota debe estar entre 0 y 100")]
    [Display(Name = "Nota Final")]
    public double Nota { get; set; }
}
