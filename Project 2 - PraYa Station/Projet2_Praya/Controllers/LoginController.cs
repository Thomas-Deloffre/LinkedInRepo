using Projet2_Praya.Models;
using Projet2_Praya.ViewModels;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace Projet2_Praya.Controllers
{
    public class LoginController : Controller
    {
        private IDalAdmin dal;

        public LoginController() : this(new DalAdmin())
        {

        }

        private LoginController(IDalAdmin dalIoc)
        {
            dal = dalIoc;
        }

        public ActionResult Index()
        {
            UtilisateurViewModel viewModel = new UtilisateurViewModel { Authentifie = HttpContext.User.Identity.IsAuthenticated };
            if (HttpContext.User.Identity.IsAuthenticated)
            {
                viewModel.Utilisateur = dal.ObtenirUtilisateur(HttpContext.User.Identity.Name);
            }
            return View(viewModel);
        }

        [HttpPost]
        public ActionResult Index(UtilisateurViewModel viewModel, string returnUrl)
        {
            if (ModelState.IsValid)
            {
                utilisateur utilisateur = dal.Authentifier(viewModel.Utilisateur.utilisateur_email, viewModel.Utilisateur.mot_de_passe);
                if (utilisateur != null)
                {
                    FormsAuthentication.SetAuthCookie(utilisateur.utilisateur_email, false);
                    if (!string.IsNullOrWhiteSpace(returnUrl) && Url.IsLocalUrl(returnUrl))
                        return Redirect(returnUrl);
                    return Redirect("/");
                }
                ModelState.AddModelError("Utilisateur.utilisateur_email", "Email et/ou mot de passe incorrect(s)");
            }
            return View(viewModel);
        }

        public ActionResult CreerCompte()
        {
            return View();
        }

        [HttpPost]
        public ActionResult CreerCompte(utilisateur utilisateur)
        {
            if (ModelState.IsValid)
            {
                if (dal.AjouterUtilisateur(utilisateur.utilisateur_email, utilisateur.mot_de_passe,
                    utilisateur.nom, utilisateur.prenom))
                {
                    FormsAuthentication.SetAuthCookie(utilisateur.utilisateur_email, false);
                    return Redirect("/");
                }
                ModelState.AddModelError("utilisateur_email", "Compte déjà existant !");
            }
            return View(utilisateur);
        }

        // Modifier utilisateur courant
        [Authorize]
        [HttpGet]
        public ActionResult ModifierUtilisateur()
        {
            utilisateur user = dal.ObtenirUtilisateur(HttpContext.User.Identity.Name);
            if (user == null)
                return View("Erreur");

            return View(user);
        }

        // Modifier l'utilisateur courant
        [HttpPost]
        public ActionResult ModifierUtilisateur(utilisateur user)
        {
            dal.EditUtilisateur(user.utilisateur_email, user.mot_de_passe, user.nom, user.prenom);
            return Redirect("/");
        }

        public ActionResult Deconnexion()
        {
            FormsAuthentication.SignOut();
            return Redirect("/");
        }

    }
}