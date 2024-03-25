using Projet2_Praya.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Projet2_Praya.Controllers
{
    public class CatalogueController : Controller
    {
        DalCatalogue dal = new DalCatalogue();

        // GET: Catalogue

        [AllowAnonymous]
        public ActionResult Index()
        {

            List<projet> listeProjets = dal.ObtenirTousLesProjets();

            List<DisplayInformationCatalogue> ListeDIC = new List<DisplayInformationCatalogue>();

            foreach (projet monProjet in listeProjets)
            {
                medium monMedia = dal.GetId_Media(monProjet.id_projet);
                if (monMedia == null)
                    continue;
                ListeDIC.Add(new DisplayInformationCatalogue
                {
                    Id_projet = monProjet.id_projet,
                    Libelle = monProjet.libelle,
                    Description = monProjet.description,
                    Id_media = monMedia.id_media,
                    Lib_image = monMedia.lib_image
                });
            }

            return View(ListeDIC);
        }

        public ActionResult getMedia(int id)
        {

            var picture = dal.GetPic(id);
            return File(picture.media, "image/jpg");

        }

    }
}