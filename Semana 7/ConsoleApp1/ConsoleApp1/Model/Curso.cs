using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Text;

namespace ConsoleApp1.Model
{
    [Table("curso", Schema = "public")]
    public class Curso
    {
        [Key]
        [Column("cod_curso")]
        public int CodCurso { get; set; }
        
        [Column("nom_curso")]
        public string NomCurso { get; set; }

        [Column("estado")]
        public int Estado {  get; set; }

        [Column("fec_creacion")]
        public DateTime? FecCreacion { get; set; }
    }
}
