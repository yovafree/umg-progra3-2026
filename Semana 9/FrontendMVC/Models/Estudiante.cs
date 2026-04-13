using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace FrontendMVC.Models;

/// <summary>
/// Modelo que corresponde a la entidad Estudiante de la API Java.
/// </summary>
public class Estudiante
{
    [JsonPropertyName("codEstudiante")]
    public int? CodEstudiante { get; set; }

    [Required(ErrorMessage = "El nombre es obligatorio")]
    [StringLength(80, ErrorMessage = "El nombre no puede exceder 80 caracteres")]
    [Display(Name = "Nombre")]
    [JsonPropertyName("nombre")]
    public string Nombre { get; set; } = string.Empty;

    [Required(ErrorMessage = "El apellido es obligatorio")]
    [StringLength(80, ErrorMessage = "El apellido no puede exceder 80 caracteres")]
    [Display(Name = "Apellido")]
    [JsonPropertyName("apellido")]
    public string Apellido { get; set; } = string.Empty;

    [EmailAddress(ErrorMessage = "El email debe ser válido")]
    [Display(Name = "Email")]
    [JsonPropertyName("email")]
    public string? Email { get; set; }

    [Display(Name = "Carnet")]
    [JsonPropertyName("carnet")]
    public string? Carnet { get; set; }

    [Display(Name = "Estado")]
    [JsonPropertyName("estado")]
    public int? Estado { get; set; }

    [Display(Name = "Fecha de Creación")]
    [JsonPropertyName("fecCreacion")]
    public DateTime? FecCreacion { get; set; }

    public string NombreCompleto => $"{Nombre} {Apellido}";
    public string EstadoTexto => Estado == 1 ? "Activo" : "Inactivo";
}
