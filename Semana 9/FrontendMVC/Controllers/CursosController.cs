using FrontendMVC.Models;
using FrontendMVC.Services;
using Microsoft.AspNetCore.Mvc;

namespace FrontendMVC.Controllers;

public class CursosController : Controller
{
    private readonly CursoApiService _cursoService;

    public CursosController(CursoApiService cursoService)
    {
        _cursoService = cursoService;
    }

    // GET: /Cursos
    public async Task<IActionResult> Index()
    {
        try
        {
            var cursos = await _cursoService.ObtenerTodosAsync();
            return View(cursos);
        }
        catch (HttpRequestException)
        {
            TempData["Error"] = "No se pudo conectar con la API Java. Asegúrese de que esté corriendo en http://localhost:8080";
            return View(new List<Curso>());
        }
    }

    // GET: /Cursos/Details/5
    public async Task<IActionResult> Details(int id)
    {
        var curso = await _cursoService.ObtenerPorIdAsync(id);
        if (curso == null) return NotFound();
        return View(curso);
    }

    // GET: /Cursos/Create
    public IActionResult Create()
    {
        return View(new Curso { Estado = 1 });
    }

    // POST: /Cursos/Create
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create(Curso curso)
    {
        if (!ModelState.IsValid) return View(curso);

        try
        {
            await _cursoService.CrearAsync(curso);
            TempData["Success"] = "Curso creado exitosamente.";
            return RedirectToAction(nameof(Index));
        }
        catch (Exception ex)
        {
            TempData["Error"] = $"Error al crear el curso: {ex.Message}";
            return View(curso);
        }
    }

    // GET: /Cursos/Edit/5
    public async Task<IActionResult> Edit(int id)
    {
        var curso = await _cursoService.ObtenerPorIdAsync(id);
        if (curso == null) return NotFound();
        return View(curso);
    }

    // POST: /Cursos/Edit/5
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Edit(int id, Curso curso)
    {
        if (!ModelState.IsValid) return View(curso);

        try
        {
            var resultado = await _cursoService.ActualizarAsync(id, curso);
            if (resultado == null) return NotFound();
            TempData["Success"] = "Curso actualizado exitosamente.";
            return RedirectToAction(nameof(Index));
        }
        catch (Exception ex)
        {
            TempData["Error"] = $"Error al actualizar: {ex.Message}";
            return View(curso);
        }
    }

    // GET: /Cursos/Delete/5
    public async Task<IActionResult> Delete(int id)
    {
        var curso = await _cursoService.ObtenerPorIdAsync(id);
        if (curso == null) return NotFound();
        return View(curso);
    }

    // POST: /Cursos/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
        var eliminado = await _cursoService.EliminarAsync(id);
        TempData[eliminado ? "Success" : "Error"] =
            eliminado ? "Curso eliminado." : "No se pudo eliminar el curso.";
        return RedirectToAction(nameof(Index));
    }
}
