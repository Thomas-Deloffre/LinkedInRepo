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
    
    public partial class rendez_vous
    {
        public short id_rendezvous { get; set; }
        public System.DateTime date_evenement { get; set; }
        public string moyen { get; set; }
        public string personne_associee { get; set; }
        public string sujet { get; set; }
        public string email { get; set; }
    
        public virtual agendum agendum { get; set; }
    }
}
