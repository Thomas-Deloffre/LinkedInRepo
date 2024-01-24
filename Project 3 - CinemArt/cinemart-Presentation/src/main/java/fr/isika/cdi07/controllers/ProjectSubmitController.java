package fr.isika.cdi07.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import fr.isika.cdi07.model.exceptions.IllegalAttributeSizeException;
import fr.isika.cdi07.model.projet.Documents;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetStatut;
import fr.isika.cdi07.model.utilisateur.PortFolio;
import fr.isika.cdi07.model.utilisateur.Utilisateur;
import fr.isika.cdi07.services.DocumentService;
import fr.isika.cdi07.services.PortfolioService;
import fr.isika.cdi07.services.ProjetService;


@Controller
@Scope("session")
@SessionAttributes({"utilisateur", "listeProjets", "projet", "projetForm", "listeProjetsActive"})
public class ProjectSubmitController {

	private static final Logger LOGGER = Logger.getLogger(ProjectSubmitController.class.getSimpleName());
	
	private Model model;

	@Autowired
	private ProjetService projetService;
	@Autowired
	private DocumentService documentService;
	@Autowired
	PortfolioService portfolioService;

	@GetMapping("/creationProjet")
	public String creationProjet(Model model) {
		this.model = model;
		ProjetForm projetForm = new ProjetForm();
		model.addAttribute("documents" , new Documents());
		model.addAttribute("projetForm", projetForm);
		model.addAttribute("projet", new Projet());
		model.addAttribute("gestionnaireProjet", model.getAttribute("utilisateur"));
		model.addAttribute("portfolio", new PortFolio());
		setTextMenuUn(model);
		return ("ProjetForm");
	}

	@PostMapping("/soumissionProjet")
	public String soumissionProjet(@RequestParam("document1") MultipartFile document1,@RequestParam("document2") MultipartFile document2,
			@RequestParam("document3") MultipartFile document3, @RequestParam("imageProjet")  MultipartFile imageProjet, 
			RedirectAttributes attributes, @ModelAttribute("projetForm") ProjetForm projetForm, Model model)
			throws IllegalAttributeSizeException, IllegalStateException, IOException {
		projetForm.setDocuments1(document1);
		projetForm.setDocument2(document2);
		projetForm.setDocument3(document3);
		LOGGER.info("Document  : " + projetForm.getDocument1().getBytes().length);
		
		LOGGER.info("Ceci est une description qui est censé passer : " + projetForm);
		Utilisateur utilisateur = (Utilisateur) this.model.getAttribute("utilisateur");
		
			
	//	LOGGER.info("il y a " + projetForm.getDocuments().size());
		
		
	// Création du projet ------------------------------------------	
		
		try {
			Projet	projet;
			projet = projetService.createProject(
					
					projetForm.getTitre(),
					projetForm.setStatusNewProject(ProjetStatut.ACTIVE),			
					projetForm.getCatTest(),
					projetForm.getGenreMain(),
					projetForm.getGenreAlt(),
					projetForm.getCourteDescription(),
					projetForm.getLongueDescription(),
					projetForm.getWebLink(),
					0, // cagnotte		
					null,
					projetForm.getImageProjet().getBytes(),
					null, //commentaire
					null, //portfolio				
					null, //utilisateur
					projetForm.getMontantDemande(),
					projetForm.getDateCreation(),
					projetForm.getDateFinCampagne(),
					projetForm.getDureeCampagne());
		projet.setUtilisateur(utilisateur);
		
	//	Création des Documents-------------------------------------------
		
		LOGGER.info("ID DU PROJET EST  " + projet.getIdProjet());
		documentService.createDocument(document1.getOriginalFilename(), projet, document1.getBytes());
		documentService.createDocument(document2.getOriginalFilename(), projet, document2.getBytes());
		documentService.createDocument(document3.getOriginalFilename(), projet, document3.getBytes());
		
		
	// Création du PortFolio
		List<Projet> listProj = new ArrayList<Projet>();
		portfolioService.createPortfolio(projet.getUtilisateur(), listProj);
		
			if(projet != null) {
				List<Projet> listeProjets = projetService.getProjetUtilisateur(utilisateur.getIdUtilisateur());
				model.addAttribute("listeProjets", listeProjets);
				model.addAttribute("utilisateur", utilisateur);
				setTextMenuUn(model);
				return "profil";
			}
		} catch (IllegalAttributeSizeException | IOException e) {
			LOGGER.severe("Erreur: " + e.getMessage());
		}
		setTextMenuUn(model);
		return "profil";
	}
	
	@GetMapping("/modifierSonProjet")
	public String modifierSonProjet(Model model, Long idProjet) {
		ProjetForm projetForm = new ProjetForm();
		Projet projet = projetService.getProjet(idProjet);
		model.addAttribute("documents" , projet.getDocument());
		model.addAttribute("projetForm", projetForm);
		model.addAttribute("projet", projet);
		model.addAttribute("gestionnaireProjet", model.getAttribute("utilisateur"));
		model.addAttribute("portfolio", projet.getPortfolio());
		setTextMenuUn(model);
		return "modifierSonProjet";
	}
	
	@PostMapping("/modificationProjet")
	public String modificationProjet(Model model, ProjetForm projetForm, String courtedescription) throws IllegalAttributeSizeException {
		Projet projet =  (Projet) model.getAttribute("projet");
		Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
		projet.setTitre(projetForm.getTitre());
		projet.setCourteDescription(courtedescription);
		projet.setLongueDescription(projetForm.getLongueDescription());
		projet.setMontantDemande(projetForm.getMontantDemande());
		projet.setWebLink(projetForm.getWebLink());
		projet.setGenreMain(projetForm.getGenreMain());
		projet.setGenreAlt(projetForm.getGenreAlt());
		projet.setCategorie(projetForm.getCatTest());
		projetService.saveModificationProjet(projet);
		List<Projet> listeProjets = projetService.getProjetActiveUtilisateur(utilisateur.getIdUtilisateur());
		model.addAttribute("listeProjetsActive", listeProjets);
		setTextMenuUn(model);
		return "profil";
	}
	
	@GetMapping("/validerSonProjet")
	public String validerSonProjet(Model model, Long idProjet) {
		projetService.pendingActiveProject(idProjet);
		Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
		List<Projet> listeProjets = projetService.getProjetActiveUtilisateur(utilisateur.getIdUtilisateur());
		model.addAttribute("listeProjetsActive", listeProjets);
		listeProjets = projetService.getProjetValiderUtilisateur(utilisateur.getIdUtilisateur());
		model.addAttribute("listeProjetsValider", listeProjets);
		listeProjets = projetService.getProjetAutresUtilisateur(utilisateur.getIdUtilisateur());
		model.addAttribute("listeProjetsAutres", listeProjets);
		setTextMenuUn(model);
		return "profil";
	}
	
	public Model setTextMenuUn(Model model){
		if(model.getAttribute("utilisateur") != null && model.getAttribute("utilisateur") != "") {
			Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
			model.addAttribute("MenuFirstItem", utilisateur.getPseudo() + " profil");
			model.addAttribute("MenuSecondItem", "Deconnexion");
			model.addAttribute("MenuFirstLien", "profil");
			model.addAttribute("MenuSecondLien", "deconnexion");
		}
		else {
			model.addAttribute("MenuFirstItem", "Inscription");
			model.addAttribute("MenuSecondItem", "Connexion");
			model.addAttribute("MenuFirstLien","inscription");
			model.addAttribute("MenuSecondLien", "connection");
		}
		return model;
	}
}