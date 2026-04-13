using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace FrontendMVC.Models;

/// <summary>
/// Modelo que corresponde a la entidad Curso de la API Java.
/// </summary>
public class Curso
{
    [JsonPropertyName("codCurso")]
    public int? CodCurso { get; set; }

    [Required(ErrorMessage = "El nombre del curso es obligatorio")]
    [StringLength(100, ErrorMessage = "El nombre no puede exceder 100 caracteres")]
    [Display(Name = "Nombre del Curso")]
    [JsonPropertyName("nomCurso")]
    public string NomCurso { get; set; } = string.Empty;

    [Display(Name = "Estado")]
    [JsonPropertyName("estado")]
    public int? Estado { get; set; }

    [Display(Name = "Fecha de Creación")]
    [JsonPropertyName("fecCreacion")]
    public DateTime? FecCreacion { get; set; }

    public string EstadoTexto => Estado == 1 ? "Activo" : "Inactivo";
}
