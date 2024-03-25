using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Projet2_Praya.Models
{
    interface IDalCreationProjet : IDisposable
    {

        bool ProjetExiste(string description);
        List<projet> ObtenirTousLesProjets();
        int CreerProjet(string libelle, string description, string type_du_projet, decimal montant_attendu, System.DateTime date_debut, System.DateTime date_butoir,string resp_projet);
        void ModifierProjet(int id_projet, string libelle, string description, string type_du_projet, decimal montant_attendu, DateTime date_debut, DateTime date_butoir);

        List<type_projet> ObtientTypeProjet();

        projet get_Projet(int id);
        bool NomAssoExiste(string nom);
        bool RNAAssoExiste(string RNA);
        void SaveFirstForm(string RNA, string IBAN, string nom_association);
        int CreerAssociation(string RNA, string IBAN, string nom_association);

        void AjouterMedia(byte[] media, string lien, int id_projet, string type_media, string lib_image);
        int AddMedia(Byte[] media, string lien, int id_projet, string type_media, string lib_image);
        int DeleteMedia(int id);
        medium GetPic(int id);
        int GetId_Media(int id_projet);
        int GetId_Media_all(int id_projet, string lib_image);


        void TestCreatePortefeuille(string resp_projet, string libelle, string niveau_habilitation, short Id_association);
        int CreerPortefeuilleProj(string resp_proj, string libelle);

        string CreerRespProj(string resp_email, string niveau_habilitation, short Id_association);
        resp_projet TestCreateResponsableP(string resp_projet);





        
    }
}