var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();

// Configurar HttpClient para consumir la API Java (Spring Boot)
var apiBaseUrl = builder.Configuration["ApiSettings:BaseUrl"] ?? "http://localhost:8080/api";
builder.Services.AddHttpClient("JavaApi", client =>
{
    client.BaseAddress = new Uri(apiBaseUrl);
    client.DefaultRequestHeaders.Add("Accept", "application/json");
});

builder.Services.AddScoped<FrontendMVC.Services.CursoApiService>();
builder.Services.AddScoped<FrontendMVC.Services.EstudianteApiService>();
builder.Services.AddScoped<FrontendMVC.Services.InscripcionApiService>();

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

app.UseAuthorization();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
