using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Reflection.Metadata;
using System.Text;

namespace ConsoleApp1.Model
{
    public class DbEjemploContext : DbContext
    {
        private readonly string _connectionString;
        public DbSet<Curso> Curso { get; set; }
        public DbEjemploContext(DbContextOptions<DbEjemploContext> options)
        : base(options)
        {
        }

        // Constructor to receive the connection string
        public DbEjemploContext(string connectionString)
        {
            _connectionString = connectionString;
        }


        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Curso>()
                .Property(b => b.NomCurso)
                .IsRequired();


        }

        // Configure the database connection using the passed string
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
                optionsBuilder.UseNpgsql(_connectionString);
            }
        }
    }
}
