using ConsoleApp1.Model;

string connectionString = "Server=localhost;Port=5432;Database=bd_ejemplo;User Id=postgres;Password=44355q6DfqrdQ;";

// 2. Instantiate the DbContext with the connection string
using (var context = new DbEjemploContext(connectionString))
{
    // Ensure the connection is working (optional, often done via migrations)
    context.Database.CanConnect();
    Console.WriteLine("Successfully connected to the PostgreSQL database!");

    var cursos = context.Curso.ToList();

    foreach (var curso in cursos) { 
        Console.WriteLine($"ID: {curso.CodCurso} Nombre: {curso.NomCurso}");
    }

    var numCursos = cursos.Count();
    Console.WriteLine($"Total cursos: {numCursos}");


    var cursoEliminar = cursos.Where(c => c.CodCurso == 1).FirstOrDefault();

    if (cursoEliminar != null) { 
        context.Curso.Remove(cursoEliminar);
        context.SaveChanges();
    }

    var cursoActualizar = cursos.Where(c => c.CodCurso == 2).FirstOrDefault();

    if (cursoActualizar != null)
    {
        cursoActualizar.NomCurso = "Programación III - Actualizado";
        cursoActualizar.Estado = 0;
        context.Curso.Update(cursoActualizar);
        context.SaveChanges();
    }

    //Curso cursoNuevo = new Curso();

    //cursoNuevo.NomCurso = "Curso de C#";
    //cursoNuevo.Estado = 1;
    ////cursoNuevo.FecCreacion = DateTime.Now;

    //context.Curso.Add(cursoNuevo);
    //context.SaveChanges();

    // Example of data interaction
    // var users = context.Users.ToList();
    // Console.WriteLine($"Found {users.Count} users.");
}
