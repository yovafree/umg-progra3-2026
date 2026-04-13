using FrontendMVC.Models;
using FrontendMVC.Services;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace FrontendMVC.Controllers;

public class InscripcionesController : Controller
{
    private readonly InscripcionApiService _inscripcionService;
    private readonly EstudianteApiService _estudianteService;
    private readonly CursoApiService _cursoService;

    public InscripcionesController(
        InscripcionApiService inscripcionService,
        EstudianteApiService estudianteService,
        CursoApiService cursoService)
    {
        _inscripcionService = inscripcionService;
        _estudianteService = estudianteService;
        _cursoService = cursoService;
    }

    // GET: /Inscripciones
    public async Task<IActionResult> Index()
    {
        try
        {
            var inscripciones = await _inscripcionService.ObtenerTodasAsync();
            return View(inscripciones);
        }
        catch (HttpRequestException)
        {
            TempData["Error"] = "No se pudo conectar con la API Java. Asegúrese de que esté corriendo en http://localhost:8080";
            return View(new List<Inscripcion>());
        }
    }

    // GET: /Inscripciones/Create
    public async Task<IActionResult> Create()
    {
        await CargarListasDesplegables();
        return View(new InscripcionCreateViewModel());
    }

    // POST: /Inscripciones/Create
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> Create(InscripcionCreateViewModel modelo)
    {
        if (!ModelState.IsValid)
        {
            await CargarListasDesplegables();
            return View(modelo);
        }

        try
        {
            await _inscripcionService.InscribirAsync(modelo.CodEstudiante, modelo.CodCurso);
            TempData["Success"] = "Inscripción creada exitosamente.";
            return RedirectToAction(nameof(Index));
        }
        catch (Exception ex)
        {
            TempData["Error"] = $"Error al inscribir: {ex.Message}";
            await CargarListasDesplegables();
            return View(modelo);
        }
    }

    // GET: /Inscripciones/AsignarNota/5
    public async Task<IActionResult> AsignarNota(int id)
    {
        var inscripcion = await _inscripcionService.ObtenerPorIdAsync(id);
        if (inscripcion == null) return NotFound();

        var modelo = new AsignarNotaViewModel
        {
            CodInscripcion = inscripcion.CodInscripcion ?? 0,
            NombreEstudiante = inscripcion.Estudiante?.NombreCompleto,
            NombreCurso = inscripcion.Curso?.NomCurso,
            Nota = inscripcion.NotaFinal ?? 0
        };
        return View(modelo);
    }

    // POST: /Inscripciones/AsignarNota/5
    [HttpPost]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> AsignarNota(AsignarNotaViewModel modelo)
    {
        if (!ModelState.IsValid) return View(modelo);

        try
        {
            await _inscripcionService.AsignarNotaAsync(modelo.CodInscripcion, modelo.Nota);
            TempData["Success"] = "Nota asignada exitosamente.";
            return RedirectToAction(nameof(Index));
        }
        catch (Exception ex)
        {
            TempData["Error"] = $"Error al asignar nota: {ex.Message}";
            return View(modelo);
        }
    }

    // GET: /Inscripciones/Delete/5
    public async Task<IActionResult> Delete(int id)
    {
        var inscripcion = await _inscripcionService.ObtenerPorIdAsync(id);
        if (inscripcion == null) return NotFound();
        return View(inscripcion);
    }

    // POST: /Inscripciones/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public async Task<IActionResult> DeleteConfirmed(int id)
    {
        var eliminado = await _inscripcionService.EliminarAsync(id);
        TempData[eliminado ? "Success" : "Error"] =
            eliminado ? "Inscripción eliminada." : "No se pudo eliminar la inscripción.";
        return RedirectToAction(nameof(Index));
    }

    private async Task CargarListasDesplegables()
    {
        var estudiantes = await _estudianteService.ObtenerTodosAsync();
        var cursos = await _cursoService.ObtenerTodosAsync();

        ViewBag.Estudiantes = new SelectList(
            estudiantes.Select(e => new { e.CodEstudiante, Texto = $"{e.Nombre} {e.Apellido} ({e.Carnet})" }),
            "CodEstudiante", "Texto");

        ViewBag.Cursos = new SelectList(
            cursos.Select(c => new { c.CodCurso, c.NomCurso }),
            "CodCurso", "NomCurso");
    }
}
