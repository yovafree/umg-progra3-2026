using System.Text;
using System.Text.Json;
using FrontendMVC.Models;

namespace FrontendMVC.Services;

/// <summary>
/// Servicio para consumir los endpoints de Cursos de la API Java.
/// </summary>
public class CursoApiService
{
    private readonly HttpClient _httpClient;
    private static readonly JsonSerializerOptions _jsonOptions = new()
    {
        PropertyNameCaseInsensitive = true
    };

    public CursoApiService(IHttpClientFactory httpClientFactory)
    {
        _httpClient = httpClientFactory.CreateClient("JavaApi");
    }

    public async Task<List<Curso>> ObtenerTodosAsync()
    {
        var response = await _httpClient.GetAsync("/api/cursos");
        response.EnsureSuccessStatusCode();
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<List<Curso>>(json, _jsonOptions) ?? new List<Curso>();
    }

    public async Task<Curso?> ObtenerPorIdAsync(int id)
    {
        var response = await _httpClient.GetAsync($"/api/cursos/{id}");
        if (!response.IsSuccessStatusCode) return null;
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Curso>(json, _jsonOptions);
    }

    public async Task<List<Curso>> BuscarPorNombreAsync(string nombre)
    {
        var response = await _httpClient.GetAsync($"/api/cursos/buscar?q={Uri.EscapeDataString(nombre)}");
        response.EnsureSuccessStatusCode();
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<List<Curso>>(json, _jsonOptions) ?? new List<Curso>();
    }

    public async Task<Curso?> CrearAsync(Curso curso)
    {
        var content = new StringContent(
            JsonSerializer.Serialize(curso, _jsonOptions),
            Encoding.UTF8,
            "application/json");
        var response = await _httpClient.PostAsync("/api/cursos", content);
        response.EnsureSuccessStatusCode();
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Curso>(json, _jsonOptions);
    }

    public async Task<Curso?> ActualizarAsync(int id, Curso curso)
    {
        var content = new StringContent(
            JsonSerializer.Serialize(curso, _jsonOptions),
            Encoding.UTF8,
            "application/json");
        var response = await _httpClient.PutAsync($"/api/cursos/{id}", content);
        if (!response.IsSuccessStatusCode) return null;
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Curso>(json, _jsonOptions);
    }

    public async Task<bool> EliminarAsync(int id)
    {
        var response = await _httpClient.DeleteAsync($"/api/cursos/{id}");
        return response.IsSuccessStatusCode;
    }
}
