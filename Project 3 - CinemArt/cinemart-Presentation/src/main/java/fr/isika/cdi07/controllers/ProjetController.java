package fr.isika.cdi07.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetCategorie;
import fr.isika.cdi07.model.projet.ProjetGenres;
import fr.isika.cdi07.model.utilisateur.Utilisateur;
import fr.isika.cdi07.services.ProjetService;

@Controller
@Scope("session")
@SessionAttributes({"utilisateur", "listeProjets"})
public class ProjetController {
	
	@Autowired
	private ProjetService projetService;
	
	@GetMapping("/catalogue")
	public String catalogue(Model model) {
		model = setTextMenuUn(model);
		List<Projet> allProjet = projetService.getAllProjects();
		model.addAttribute("listProjet", allProjet);
		setTextMenuUn(model);
		return "catalogue";
	}
	
	@PostMapping("/triCatalogue")
		public String triCatalogue(Model model, ProjetCategorie categorie, ProjetGenres genreMain, String motCle) {
		String motCleTrim = motCle.trim().toLowerCase();
		if(categorie == ProjetCategorie.VIDE && genreMain == ProjetGenres.VIDE && motCleTrim.isEmpty()) {
			List<Projet> listeProjetsTri = projetService.searchByCategorie(categorie);	
			model.addAttribute("listProjet", listeProjetsTri);
		} else if (categorie != ProjetCategorie.VIDE && genreMain == ProjetGenres.VIDE && motCleTrim.isEmpty()) {
			List<Projet> listeProjetsTri = projetService.searchByCategorie(categorie);
			model.addAttribute("listProjet", listeProjetsTri);
		} else if (categorie == ProjetCategorie.VIDE && genreMain!= ProjetGenres.VIDE && motCleTrim.isEmpty()) {
			List<Projet> listeProjetsTri = projetService.searchByGenreMain(genreMain);
			model.addAttribute("listProjet", listeProjetsTri);
		} else if (categorie != ProjetCategorie.VIDE && genreMain!= ProjetGenres.VIDE && motCleTrim.isEmpty()) {
			List<Projet> listeProjetsTri = projetService.searchByCategorieAndGenreMain(categorie, genreMain);
			model.addAttribute("listProjet", listeProjetsTri);
		} else if (categorie != ProjetCategorie.VIDE && genreMain == ProjetGenres.VIDE && !motCleTrim.isEmpty()) {
			List<Projet> listeProjetsTri = projetService.searchByCategorie(categorie);
			List<Projet> listeProjetsMotCle = new ArrayList<Projet>();
			for(Projet p : listeProjetsTri) {
				if(p.getTitre().contains(motCleTrim) || p.getCourteDescription().contains(motCleTrim) || p.getLongueDescription().contains(motCleTrim)) {
					listeProjetsMotCle.add(p);
				}
			model.addAttribute("listProjet", listeProjetsMotCle);
			}
		} else if (categorie == ProjetCategorie.VIDE && genreMain != ProjetGenres.VIDE && !motCleTrim.isEmpty()) {
			List<Projet> listeProjetsTri = projetService.searchByGenreMain(genreMain);
			List<Projet> listeProjetsMotCle = new ArrayList<Projet>();
			for(Projet p : listeProjetsTri) {
				if(p.getTitre().contains(motCleTrim) || p.getCourteDescription().contains(motCleTrim) || p.getLongueDescription().contains(motCleTrim)) {
					listeProjetsMotCle.add(p);
				}
			model.addAttribute("listProjet", listeProjetsMotCle);
			}
		} else if (categorie != ProjetCategorie.VIDE && genreMain!= ProjetGenres.VIDE && !motCleTrim.isEmpty()) {
			List<Projet> listeProjetsTri = projetService.searchByCategorieAndGenreMain(categorie, genreMain);
			List<Projet> listeProjetsMotCle = new ArrayList<Projet>();
			for(Projet p : listeProjetsTri) {
				if(p.getTitre().contains(motCleTrim) || p.getCourteDescription().contains(motCleTrim) || p.getLongueDescription().contains(motCleTrim)) {
					listeProjetsMotCle.add(p);
				}
			model.addAttribute("listProjet", listeProjetsMotCle);
			}
		} else if(categorie == ProjetCategorie.VIDE && genreMain == ProjetGenres.VIDE && !motCleTrim.isEmpty()) {
			List<Projet> allProjets = projetService.getAllProjects();
			List<Projet> listeProjetsMotCle = new ArrayList<Projet>();
			for(Projet p : allProjets) {
				if(p.getTitre().contains(motCleTrim) || p.getCourteDescription().contains(motCleTrim) || p.getLongueDescription().contains(motCleTrim)) {
					listeProjetsMotCle.add(p);
				}
			model.addAttribute("listProjet", listeProjetsMotCle);
			}
		}
		setTextMenuUn(model);
		return "catalogue";
	}
	
	@PostMapping("/triCatalogue2")
	public String triCatalogueMotCle(Model model, String motCle) {
		String motCleTrim = motCle.trim().toLowerCase();
		if(motCleTrim.isEmpty()) {
			List<Projet> allProjets = projetService.getAllProjects();
			model.addAttribute("listProjet", allProjets);
		} else if (!motCleTrim.isEmpty()) {
			List<Projet> allProjets = projetService.getAllProjects();
			List<Projet> listeProjetsMotCle = new ArrayList<Projet>();
			for(Projet p : allProjets) {
				if(p.getTitre().contains(motCleTrim) || p.getCourteDescription().contains(motCleTrim) || p.getLongueDescription().contains(motCleTrim)) {
					listeProjetsMotCle.add(p);
				}
			model.addAttribute("listProjet", listeProjetsMotCle);
			}
		}
		setTextMenuUn(model);
		return "catalogue";
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