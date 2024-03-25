using Projet2_Praya.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.UI.WebControls;

namespace Projet2_Praya.Controllers
{
    [Authorize]
    public class CreationProjetController : Controller
    {
        private IDalCreationProjet dalCProj;

        public IEnumerable<object> Controls { get; private set; }

        public CreationProjetController() : this(new DalCreationProjet())
        {

        }

        public CreationProjetController(DalCreationProjet dalCreate)
        {
            dalCProj = dalCreate;
        }


        // GET: CreationAsso
        public ActionResult Index()
        {

            return View();
        }

        [HttpPost]
        public ActionResult Index(association asso)
        {
            if (dalCProj.NomAssoExiste(asso.nom_association))
            {
                ModelState.AddModelError("Nom", "Ce nom d'association existe déjà");
                return View(asso);
            }

            if (dalCProj.RNAAssoExiste(asso.RNA))
            {
                ModelState.AddModelError("RNA", "Ce RNA d'association existe déjà");
                return View(asso);
            }

            if (ModelState.IsValid)
            {
                if (asso.RNA.Length > 10) { 
                    ModelState.AddModelError("RNA", "Ce RNA d'association existe déjà");
                    return View(asso);
                }
                int IdAssos = dalCProj.CreerAssociation(asso.nom_association, asso.RNA, asso.IBAN);


                dalCProj.TestCreatePortefeuille(HttpContext.User.Identity.Name, "Defaut", "1", (short)IdAssos);
            }

            return RedirectToAction("CreationProjet");
        }


        // GET: CreationProjet
        public ActionResult CreationProjet()
        {
            get_type_projets();
            return View();
        }

        [HttpPost]
        public ActionResult CreationProjet(projet projet)
        {
            get_type_projets();
            if (dalCProj.ProjetExiste(projet.libelle))
            {
                ModelState.AddModelError("libelle", "Ce projet existe déjà ! Veuillez modifier le titre de votre projet");
                return View(projet);
            }
            if ( projet.type_du_projet == null)
            {
                ModelState.AddModelError("type_du_projet", "Séléctionnez un type valable");
                return View(projet);
            }
            //   return View(projet);
            if (!ModelState.IsValid)
                return View(projet);

            int projid = dalCProj.CreerProjet(projet.libelle, projet.description, projet.type_du_projet,
                projet.montant_attendu, (DateTime)projet.date_debut, (DateTime)projet.date_butoir, HttpContext.User.Identity.Name);
            return RedirectToAction("Index", "Media", new { id = projid });
        }


        //Get: ModifierProjet
        public ActionResult ModifierProjet(int? id_projet)
        {
            if (id_projet.HasValue)
            {
                projet projet = dalCProj.ObtenirTousLesProjets().FirstOrDefault(p => p.id_projet == id_projet.Value);
                if (projet == null)
                    return View("Error");
                return View(projet);
            }
            else
                return HttpNotFound();
        }

        [HttpPost]
        public ActionResult ModifierProjet(projet projet)
        {
            if (!ModelState.IsValid)
                return View(projet);
            dalCProj.ModifierProjet(projet.id_projet, projet.libelle, projet.description, projet.type_du_projet, projet.montant_attendu, projet.date_debut, projet.date_butoir);
            return RedirectToAction("Index");
        }



        /*public ActionResult ComboBTypesProjet()
        {
            List<type_projet> TypesProjet = dalCProj.ObtientTypeProjet();
            SelectList SlTypeP = new SelectList(TypesProjet, "Type");
            ViewBag.TypesProjet = SlTypeP;
            return View();
        }*/

        void ResetForm(object sender, EventArgs e)
        {
            foreach (var control in this.Controls)
            {
                var textbox = control as TextBox;
                if (textbox != null)
                    textbox.Text = string.Empty;

                var dropDownList = control as DropDownList;
                if (dropDownList != null)
                    dropDownList.SelectedIndex = 0;
            }
        }

        void get_type_projets()
        {
            List<type_projet> TypesProjet = dalCProj.ObtientTypeProjet();
            List<SelectListItem> TypesSelect = new List<SelectListItem>();
            foreach (type_projet IdType in TypesProjet)
            {
                TypesSelect.Add(new SelectListItem { Text = IdType.type, Value = IdType.type });
            }

            // SelectList SlTypeP = new SelectList(TypesProjet, "Value", "Text");
            ViewBag.TypesProjet = TypesSelect;
        }
    }
}