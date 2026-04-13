using FrontendMVC.Models;
using FrontendMVC.Services;
using Microsoft.AspNetCore.Mvc;

namespace FrontendMVC.Controllers;

public class EstudiantesController : Controller
{
    private readonly EstudianteApiService _estudianteService;

    public EstudiantesController(EstudianteApiService estudianteService)
    {
        _estudianteService = estudianteService;
    }

    // GET: /Estudiantes
    public async Task<IActionResult> Index()
    {
        try
        {
            var estudiantes = await _estudianteService.ObtenerTodosAsync();
            return View(estudiantes);
        }
        catch (HttpRequestException)
        {
            TempData["Error"] = "No se pudo conectar con la API Java. Asegúrese de que esté corriendo en http://localhost:8080";
            return View(new List<Estudiante>());
        }
    }

    // GET: /Estudiantes/Details/5
    public async Task<IActionResult> Details(int id)
    {
        var estudiante = await _estudianteService.ObtenerPorIdAsync(id);
        if (estudiante == null) return NotFound();
        return View(estudiante);
    }

    // GET: /Estudiantes/Create
    public IActionResult Create()
    {
        return View(new Estudiante { Estado = 1 });
    }

    // POST: /Estudiantes/Create
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create(Estudiante estudiante)
    {
        if (!ModelState.IsValid) return View(estudiante);

        try
        {
            await _estudianteService.CrearAsync(estudiante);
            TempData["Success"] = "Estudiante creado exitosamente.";
            return RedirectToAction(nameof(Index));
        }
        catch (Exception ex)
        {
            TempData["Error"] = $"Error al crear el estudiante: {ex.Message}";
            return View(estudiante);
        }
    }

    // GET: /Estudiantes/Edit/5
    public async Task<IActionResult> Edit(int id)
    {
        var estudiante = await _estudianteService.ObtenerPorIdAsync(id);
        if (estudiante == null) return NotFound();
        return View(estudiante);
    }

    // POST: /Estudiantes/Edit/5
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Edit(int id, Estudiante estudiante)
    {
        if (!ModelState.IsValid) return View(estudiante);

        try
        {
            var resultado = await _estudianteService.ActualizarAsync(id, estudiante);
            if (resultado == null) return NotFound();
            TempData["Success"] = "Estudiante actualizado exitosamente.";
            return RedirectToAction(nameof(Index));
        }
        catch (Exception ex)
        {
            TempData["Error"] = $"Error al actualizar: {ex.Message}";
            return View(estudiante);
        }
    }

    // GET: /Estudiantes/Delete/5
    public async Task<IActionResult> Delete(int id)
    {
        var estudiante = await _estudianteService.ObtenerPorIdAsync(id);
        if (estudiante == null) return NotFound();
        return View(estudiante);
    }

    // POST: /Estudiantes/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
        var eliminado = await _estudianteService.EliminarAsync(id);
        TempData[eliminado ? "Success" : "Error"] =
            eliminado ? "Estudiante eliminado." : "No se pudo eliminar el estudiante.";
        return RedirectToAction(nameof(Index));
    }
}
