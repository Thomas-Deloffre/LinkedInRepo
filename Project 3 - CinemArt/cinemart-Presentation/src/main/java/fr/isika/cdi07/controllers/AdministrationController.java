package fr.isika.cdi07.controllers;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import fr.isika.cdi07.model.utilisateur.Utilisateur;
import fr.isika.cdi07.services.ProjetService;
import fr.isika.cdi07.services.UtilisateurService;

@Controller
@SessionAttributes({"utilisateur"})
public class AdministrationController {
	
	@Autowired
	private UtilisateurService userService;
	
	@Autowired
	private ProjetService projetService;
	
	@ModelAttribute("utilisateur")
    public Utilisateur getUtilisateur (HttpServletRequest request) {
        return new Utilisateur();
    }
	
	@GetMapping("/administration")
	public String administration(Model model) {
		model = setTextMenuUn(model);
		return "admin";
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
			model.addAttribute("MenuFirstLien","");
			model.addAttribute("MenuSecondLien", "connection");
		}
		return model;
	}
}