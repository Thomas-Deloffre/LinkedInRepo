//------------------------------------------------------------------------------
// <auto-generated>
//     Ce code a été généré à partir d'un modèle.
//
//     Des modifications manuelles apportées à ce fichier peuvent conduire à un comportement inattendu de votre application.
//     Les modifications manuelles apportées à ce fichier sont remplacées si le code est régénéré.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Projet2_Praya.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class preference
    {
        public short id_preference { get; set; }
        public string mot_cle { get; set; }
        public string valeur { get; set; }
        public string email_donateur { get; set; }
    
        public virtual donateur donateur { get; set; }
    }
}