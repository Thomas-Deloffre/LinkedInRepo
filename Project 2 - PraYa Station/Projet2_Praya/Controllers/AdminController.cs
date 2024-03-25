using Projet2_Praya.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Projet2_Praya.Controllers
{
    [Authorize]
    public class AdminController : Controller
    {
        // connexion à a DAL 
        private IDalAdmin dal;
        public AdminController() : this(new DalAdmin())
        {

        }

        private AdminController(IDalAdmin dalIoc)
        {
            dal = dalIoc;
        }

        // Menu index Admin
        public ActionResult Index()
        {
            if (!ChkAdmin())
                return View("ErreurNotAdm");

            utilisateur Utilisateur = dal.ObtenirUtilisateur(HttpContext.User.Identity.Name);
            ViewBag.utilisateur = Utilisateur;
            return View();
        }

        // liste des utilisateurs
        public ActionResult AfficheUtilisateurs()
        {
            if (!ChkAdmin())
                return View("ErreurNotAdm");


            List<utilisateur> listeUtilisateurs = dal.ObtientTousLesUtilisateurs();

            return View(listeUtilisateurs);
        }


        // Ajouter un utilisateur
        [HttpGet]
        public ActionResult AjouterUtilisateur()
        {
            return View();
        }

        [HttpPost]
        public ActionResult AjouterUtilisateur(utilisateur utilisateur)
        {
            if (ModelState.IsValid)
            {
                if (dal.AjouterUtilisateur(utilisateur.utilisateur_email, utilisateur.mot_de_passe,
                    utilisateur.nom, utilisateur.prenom))
                {
                    return RedirectToAction("AfficheUtilisateurs");
                    // return Redirect("/");
                }
                ModelState.AddModelError("utilisateur_email", "Compte déjà existant !");
            }
            return View(utilisateur);
        }

        // Modifier un utilisateur
        [HttpGet]
        public ActionResult ModifierUtilisateur(string id)
        {
            if (!ChkAdmin())
                return View("ErreurNotAdm");



            utilisateur user = dal.ObtenirUtilisateur(id);
            if (user == null)
                return View("Erreur");

            return View(user);
        }

        // Modifier un utilisateur
        [HttpPost]
        public ActionResult ModifierUtilisateur(utilisateur user)
        {
            dal.EditUtilisateur(user.utilisateur_email, user.mot_de_passe, user.nom, user.prenom);
            return RedirectToAction("AfficheUtilisateurs");
        }

        // liste des types projets
        public ActionResult AfficheTypeProjet()
        {
            if (!ChkAdmin())
                return View("ErreurNotAdm");


            List<type_projet> listeTypesProjet = dal.ObtientTousLesTypesProjets();

            return View(listeTypesProjet);
        }


        // Modifier un type de projet
        [HttpGet]
        public ActionResult ModifierTypeProjet(string id)
        {
            if (!ChkAdmin())
                return View("ErreurNotAdm");



            type_projet typeproj = dal.ObtenirTypeProjet(id);
            if (typeproj == null)
                return View("Erreur");

            return View(typeproj);
        }

        // Modifier un type de projet
        [HttpPost]
        public ActionResult ModifierTypeProjet(type_projet typeProj)
        {
            dal.ModifierTypeProjet(typeProj.type, typeProj.libelle);
            return RedirectToAction("AfficheTypeProjet");
        }

        // créer type projet
        public ActionResult AjouterTypeProjet()
        {
            return View();
        }

        [HttpPost]
        public ActionResult AjouterTypeProjet(type_projet typeProj)
        {
            if (ModelState.IsValid)
            {
                if (dal.CreerTypeProjet(typeProj.type, typeProj.libelle))
                {
                    return RedirectToAction("AfficheTypeProjet");
                    // return Redirect("/");
                }
                ModelState.AddModelError("type", "Type de projet déjà existant !");
            }
            return View(typeProj);
        }




        // check user connecté et reconnu comme admin
        private bool ChkAdmin()
        {
            administrateur Admin = null;
            if (HttpContext.User.Identity.IsAuthenticated)
            {
                Admin = dal.ObtenirAdmin(HttpContext.User.Identity.Name);
            }
            if (Admin == null)
            {
                return false;
            }
            return true;
        }
    }
}