using Projet2_Praya.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Projet2_Praya.Controllers
{
    public class AccueilController : Controller
    {
        // connexion à a DAL 
        private IDalAdmin dal;
        public AccueilController() : this(new DalAdmin())
        {

        }

        private AccueilController(IDalAdmin dalIoc)
        {
            dal = dalIoc;
        }

        // GET: Index
        public ActionResult Index()
        {
            utilisateur Utilisateur = null;
            if (HttpContext.User.Identity.IsAuthenticated)
            {
                Utilisateur = dal.ObtenirUtilisateur(HttpContext.User.Identity.Name);
            }

            ViewBag.curdate = DateTime.Now;
            ViewBag.utilisateur = Utilisateur;

            // alimentation drop down liste de test
            List<type_projet> listeTypes = dal.ObtientTousLesTypesProjets();

            List<SelectListItem> demo = new List<SelectListItem>();

            demo.Add(new SelectListItem { Text = "<Selectionnez un type>", Value = "0", Selected = true });
            foreach (type_projet montype in listeTypes)
            {
                demo.Add(new SelectListItem { Text = montype.type, Value = montype.type });
                //items.Add(new SelectListItem { Text = "Drama", Value = "1" });
                //items.Add(new SelectListItem { Text = "Comedy", Value = "2", Selected = true });
                // items.Add(new SelectListItem { Text = "Science Fiction", Value = "3" });
            }
            ViewBag.TypeProjet = demo;

            return View();
        }
    }
}