using System.Text;
using System.Text.Json;
using FrontendMVC.Models;

namespace FrontendMVC.Services;

/// <summary>
/// Servicio para consumir los endpoints de Estudiantes de la API Java.
/// </summary>
public class EstudianteApiService
{
    private readonly HttpClient _httpClient;
    private static readonly JsonSerializerOptions _jsonOptions = new()
    {
        PropertyNameCaseInsensitive = true
    };

    public EstudianteApiService(IHttpClientFactory httpClientFactory)
    {
        _httpClient = httpClientFactory.CreateClient("JavaApi");
    }

    public async Task<List<Estudiante>> ObtenerTodosAsync()
    {
        var response = await _httpClient.GetAsync("/api/estudiantes");
        response.EnsureSuccessStatusCode();
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<List<Estudiante>>(json, _jsonOptions) ?? new List<Estudiante>();
    }

    public async Task<Estudiante?> ObtenerPorIdAsync(int id)
    {
        var response = await _httpClient.GetAsync($"/api/estudiantes/{id}");
        if (!response.IsSuccessStatusCode) return null;
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Estudiante>(json, _jsonOptions);
    }

    public async Task<List<Estudiante>> BuscarAsync(string termino)
    {
        var response = await _httpClient.GetAsync($"/api/estudiantes/buscar?q={Uri.EscapeDataString(termino)}");
        response.EnsureSuccessStatusCode();
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<List<Estudiante>>(json, _jsonOptions) ?? new List<Estudiante>();
    }

    public async Task<Estudiante?> CrearAsync(Estudiante estudiante)
    {
        var content = new StringContent(
            JsonSerializer.Serialize(estudiante, _jsonOptions),
            Encoding.UTF8,
            "application/json");
        var response = await _httpClient.PostAsync("/api/estudiantes", content);
        response.EnsureSuccessStatusCode();
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Estudiante>(json, _jsonOptions);
    }

    public async Task<Estudiante?> ActualizarAsync(int id, Estudiante estudiante)
    {
        var content = new StringContent(
            JsonSerializer.Serialize(estudiante, _jsonOptions),
            Encoding.UTF8,
            "application/json");
        var response = await _httpClient.PutAsync($"/api/estudiantes/{id}", content);
        if (!response.IsSuccessStatusCode) return null;
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Estudiante>(json, _jsonOptions);
    }

    public async Task<bool> EliminarAsync(int id)
    {
        var response = await _httpClient.DeleteAsync($"/api/estudiantes/{id}");
        return response.IsSuccessStatusCode;
    }
}
