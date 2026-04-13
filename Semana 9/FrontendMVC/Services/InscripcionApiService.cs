using System.Text.Json;
using FrontendMVC.Models;

namespace FrontendMVC.Services;

/// <summary>
/// Servicio para consumir los endpoints de Inscripciones de la API Java.
/// </summary>
public class InscripcionApiService
{
    private readonly HttpClient _httpClient;
    private static readonly JsonSerializerOptions _jsonOptions = new()
    {
        PropertyNameCaseInsensitive = true
    };

    public InscripcionApiService(IHttpClientFactory httpClientFactory)
    {
        _httpClient = httpClientFactory.CreateClient("JavaApi");
    }

    public async Task<List<Inscripcion>> ObtenerTodasAsync()
    {
        var response = await _httpClient.GetAsync("/api/inscripciones");
        response.EnsureSuccessStatusCode();
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<List<Inscripcion>>(json, _jsonOptions) ?? new List<Inscripcion>();
    }

    public async Task<Inscripcion?> ObtenerPorIdAsync(int id)
    {
        var response = await _httpClient.GetAsync($"/api/inscripciones/{id}");
        if (!response.IsSuccessStatusCode) return null;
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Inscripcion>(json, _jsonOptions);
    }

    public async Task<Inscripcion?> InscribirAsync(int codEstudiante, int codCurso)
    {
        var response = await _httpClient.PostAsync(
            $"/api/inscripciones?codEstudiante={codEstudiante}&codCurso={codCurso}", null);
        response.EnsureSuccessStatusCode();
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Inscripcion>(json, _jsonOptions);
    }

    public async Task<Inscripcion?> AsignarNotaAsync(int codInscripcion, double nota)
    {
        var response = await _httpClient.PutAsync(
            $"/api/inscripciones/{codInscripcion}/nota?valor={nota}", null);
        if (!response.IsSuccessStatusCode) return null;
        var json = await response.Content.ReadAsStringAsync();
        return JsonSerializer.Deserialize<Inscripcion>(json, _jsonOptions);
    }

    public async Task<bool> EliminarAsync(int id)
    {
        var response = await _httpClient.DeleteAsync($"/api/inscripciones/{id}");
        return response.IsSuccessStatusCode;
    }
}
