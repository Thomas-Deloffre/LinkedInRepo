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
    
    public partial class profil
    {
        public string email { get; set; }
        public System.DateTime date_connexion { get; set; }
        public byte[] photo { get; set; }
    
        public virtual espace_utilisateur espace_utilisateur { get; set; }
    }
}