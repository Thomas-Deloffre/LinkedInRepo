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
    
    public partial class facture
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public facture()
        {
            this.porfefeuille_factures = new HashSet<porfefeuille_factures>();
        }
    
        public short id_facture { get; set; }
        public System.DateTime date_facture { get; set; }
        public string libelle { get; set; }
        public decimal montant { get; set; }
        public string numero { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<porfefeuille_factures> porfefeuille_factures { get; set; }
    }
}