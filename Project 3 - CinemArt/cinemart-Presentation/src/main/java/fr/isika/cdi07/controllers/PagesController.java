package fr.isika.cdi07.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.isika.cdi07.model.don.Don;
import fr.isika.cdi07.model.messagerie.Commentaire;
import fr.isika.cdi07.model.messagerie.MyConstants;
import fr.isika.cdi07.model.messagerie.SupportDiscussion;
import fr.isika.cdi07.model.projet.Documents;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetCategorie;
import fr.isika.cdi07.model.utilisateur.Profil;
import fr.isika.cdi07.model.utilisateur.Roles;
import fr.isika.cdi07.model.utilisateur.Utilisateur;
import fr.isika.cdi07.services.CommentaireService;
import fr.isika.cdi07.services.DocumentService;
import fr.isika.cdi07.services.DonService;
import fr.isika.cdi07.services.ProjetService;
import fr.isika.cdi07.services.SupportDiscussionService;
import fr.isika.cdi07.services.UtilisateurService;

@Controller
@Scope("session")
@SessionAttributes({"utilisateur", "listeProjets", "projet", "listeProjetsActive", 
	"listeProjetsValider", "listeProjetsAutres", "listeDons", "listeProjetsPaiement",
	"listeProjetsPending", "listeMessagesOpen", "listeCommentaires", "listeCommentaire"})
public class PagesController {
	@Autowired
	private UtilisateurService userService;
	@Autowired
	private ProjetService projetService;
	@Autowired 
	DocumentService documentService;
	@Autowired
	private SupportDiscussionService supportDiscussionService;
	@Autowired
	private DonService donService;
	@Autowired
	public JavaMailSender emailSender;
	
	@Autowired
	private CommentaireService commentaireService;
	
	private Model model;
	
	//Initialisation variables de sessions
	@ModelAttribute("utilisateur")
    public Utilisateur getUtilisateur (HttpServletRequest request) {
        return null;
    }
	
	@GetMapping("/")
	public String accueil(Model model) {
		this.model = model;
		model = setTextMenuUn(model);
		List<Projet> allProjet = projetService.getAllProjects();
		List<Projet> listProjetOld = projetService.getSixOldProjects();
		List<Projet> listProjetNew = projetService.getSixNewProjects();
		List<Projet> listProjetHelp = projetService.getSixLessMoney();
		List<Projet> listProjetRandom = projetService.getSixRandom();
		model.addAttribute("listProjet", allProjet);
		model.addAttribute("listOld", listProjetOld);
		model.addAttribute("listNew", listProjetNew);
		model.addAttribute("listHelp", listProjetHelp);
		model.addAttribute("listRandom", listProjetRandom);
		return "accueil";
	}
	
	@ModelAttribute("listeProjets")
    public List<Projet> getListProjetUtilisateur (HttpServletRequest request) {
		List<Projet> listeProjets = new ArrayList<Projet>();
		return listeProjets;
    }
	// end init
	
	@GetMapping("/inscription")
	public String home(Model model) {
		model = setTextMenuUn(model);
		List<Utilisateur> allUtilisateur = userService.getAllUtilisateur();
		model.addAttribute("listUtilisateur", allUtilisateur);
		return "inscription";
	}
	
	@GetMapping("/connection")
	public String connection(Model model) {
		this.model = model;
		this.model = setTextMenuUn(model);
		return "connexion";
	}
	
	@PostMapping("/ajouterUtilisateur")
	public String ajouterUtilisateur(Model model, String pseudo, String mdp, String nom,
			String prenom, String email) {
		//set les attributs en dur
		model = setTextMenuUn(model);
		Utilisateur utilisateur = userService.createUtilisateur(pseudo, mdp, nom, prenom, email);
		if(utilisateur != null) {
			model.addAttribute("utilisateur", utilisateur);
			model = setTextMenuUn(model);
			return "profil";
		}
		else {
			return "/inscription";
		}	
	}
	
	@PostMapping("/modifierUtilisateur")
	public String modifierUtilisateur(Model model, String pseudo, String mdp, String nom,
			String prenom, String email) {
		System.out.println("step 1");
		this.model = model;
		Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
		utilisateur.setPseudo(pseudo);
		utilisateur.setMotDePasse(mdp);
		Profil profil = utilisateur.getProfil();
		profil.setNom(nom);
		profil.setPrenom(prenom);
		profil.setEmail(email);
		System.out.println(utilisateur);
		userService.modifierUtilisateur(utilisateur);
		this.model.addAttribute("utilisateur", utilisateur);
		this.model = setTextMenuUn(model);
		if(utilisateur.getRole().equals(Roles.ADMINISTRATEUR)) {
			return "adminProfil";
		}
		else {
			return "profil";
		}
	}
	@GetMapping("/deconnexion")
	public String deconnexion(Model model) {
		model.addAttribute("utilisateur", "");
		model = setTextMenuUn(model);
		return "connexion";
	}
	@GetMapping("/profil")
	public String profil(Model model) {
		model = setTextMenuUn(model);
		return "profil";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		model = setTextMenuUn(model);
		return "admin";
	}
	
	@GetMapping("/adminProfil")
	public String adminProfil(Model model) {
		model = setTextMenuUn(model);
		return "adminProfil";
	}
	
	@GetMapping("/page_projet")
	public String page_projet(Model model, Long idProjet) {
		Projet projet = projetService.getProjet(idProjet);
		model.addAttribute("projet", projet);
		List<ProjetCategorie>listCategories = new ArrayList<>();
		listCategories.add(projet.getCategorie());
		model.addAttribute("listeCategories", listCategories);
		List<Commentaire> listeCommentaires = commentaireService.rechercheCommentaireProjet(projet);
		model.addAttribute("listeCommentaires", listeCommentaires);
		model = setTextMenuUn(model);
		return "page_projet";
	}	
	@PostMapping("/rechercherProjets")
	public String rechercherProjets(Model model, String motsCles){
		//String[] listesMotsCles = motsCles.split(" ");
		List<Projet> listeProjets = projetService.rechercherParMotCle(motsCles);
		model.addAttribute("listeProjets", listeProjets);
		return "page_recherche_projets";
	}	
	@GetMapping("/etudierProjet")
	public String etudierProjet(Model model, Long idProjet, Long idDocuments) {
		Projet projet = projetService.getProjet(idProjet);
		Documents document = documentService.getDocument(idDocuments);
		model.addAttribute("projet", projet);
		model.addAttribute("idProjet", idProjet);
		model.addAttribute("document", document);
		model.addAttribute("idDocument", idDocuments);
		List<ProjetCategorie>listCategories = new ArrayList<>();
		listCategories.add(projet.getCategorie());
		model.addAttribute("listeCategories", listCategories);
		model = setTextMenuUn(model);
		return "etudierProjet";
	}
	@PostMapping("/validerRefuserProjet")
	public String validerRefuserProjet(Model model, Long idProjet, String validation, String refuser) {
		if(validation != null){
			projetService.validateActiveProject(Long.parseLong(validation));
		}
		else {
			projetService.rejectedActiveProject(Long.parseLong(refuser));
		}
		List<Projet> listeProjets = projetService.getAllProjetPending();
		model.addAttribute("listeProjets", listeProjets);
		return "admin";
	}
	@PostMapping("/connectionUtilisateur")
	public String connecterUtilisateur(Model model, String pseudo, String mdp) {
		Utilisateur utilisateur = userService.recupereUtilisateur(pseudo, mdp);
		List<Projet> listeProjets;
		if(utilisateur != null) {
			model.addAttribute("utilisateur", utilisateur);
			model = setTextMenuUn(model);
			if(utilisateur.getRole().equals(Roles.ADMINISTRATEUR)) {
				listeProjets = projetService.getAllProjetPending();
				model.addAttribute("listeProjetsPending", listeProjets);
				listeProjets = projetService.getAllProjetPaiement();
				model.addAttribute("listeProjetsPaiement", listeProjets);
				List<SupportDiscussion> listeDiscussion = supportDiscussionService.getAllDiscutionNotClosed();
				model.addAttribute("listeMessagesOpen", listeDiscussion);
				return "admin";
			}
			listeProjets = projetService.getProjetActiveUtilisateur(utilisateur.getIdUtilisateur());
			model.addAttribute("listeProjetsActive", listeProjets);
			listeProjets = projetService.getProjetValiderUtilisateur(utilisateur.getIdUtilisateur());
			model.addAttribute("listeProjetsValider", listeProjets);
			listeProjets = projetService.getProjetAutresUtilisateur(utilisateur.getIdUtilisateur());
			model.addAttribute("listeProjetsAutres", listeProjets);
			List<Don> listeDons = donService.getDonUtilisateur(utilisateur.getIdUtilisateur());
			model.addAttribute("listeDons", listeDons);
			List<Commentaire> listeCommentaire = commentaireService.getAllCommentaireUtilisateur(utilisateur.getIdUtilisateur());
			model.addAttribute("listeCommentaire", listeCommentaire);
			return "profil";
		}
		else {
			model = setTextMenuUn(model);
			return "/connexion";
		}	
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
	@PostMapping("/createDiscutionSupport")
	public String createDiscutionSupport(Model model, String intitule, String message) {
		this.model = model;
		
		Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
		supportDiscussionService.createDiscutionSupport(utilisateur, intitule, message);
		return "profil";
	}
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}	
	@GetMapping("/projet/image/{idProjet}")
	public void showProductImage(@PathVariable String idProjet, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpg");
		Projet projet = projetService.getProjet(Long.parseLong(idProjet));
		InputStream is = new ByteArrayInputStream(projet.getImageProjet());
		IOUtils.copy(is, response.getOutputStream());
	}	
	@GetMapping("/faq")
	public String faq(Model model) {
		this.model = model;
		this.model = setTextMenuUn(model);
		return "faq";
	}	
	@GetMapping("/contact")
	public String contact(Model model) {
		this.model = model;
		this.model = setTextMenuUn(model);
		return "contact";
	}
	@GetMapping("/cgu")
	public String cgu(Model model) {
		this.model = model;
		this.model = setTextMenuUn(model);
		return "conditionsGenerales";
	}	
	@GetMapping("/team")
	public String team(Model model) {
		this.model = model;
		this.model = setTextMenuUn(model);
		return "teamDisplay";
	}	
	@GetMapping("/faireDon")
	public String faireDon(Model model, String idProjet) {
		Projet projet = projetService.getProjet(Long.parseLong(idProjet));
		model.addAttribute("projet", projet);
		System.out.println("TITRE : " + projet.getTitre());
		this.model = model;
		this.model = setTextMenuUn(model);
		return "faireDon";
	}	
	@PostMapping("/validerDon")
	public String validerDon(Model model, int montantDon){
		Projet projet = (Projet) model.getAttribute("projet");
		Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
		donService.enregistrerDon(montantDon, projet, utilisateur);
		this.model = setTextMenuUn(model);
		return "profil";
	}
	
	@PostMapping("/paiementProjet")
	public String validerDon(Model model, Long idProjet){
		Projet projet = projetService.getProjet(idProjet);
		projetService.paiementProjet(projet);
		List<Projet> listeProjets = projetService.getAllProjetPaiement();
		model.addAttribute("listeProjetsPaiement", listeProjets);
		this.model = setTextMenuUn(model);
		return "admin";
	}
	
	@GetMapping("/adminMessage")
	public String adminMessage(Model model) {
		this.model = setTextMenuUn(model);
		return "adminMessage";
	}
	
	@GetMapping("/dev")
	public String enDev(Model model) {
		this.model = model;
		this.model = setTextMenuUn(model);
		return "developpement";
	}
	
	@GetMapping("/financementParticipatif")
	public String financement(Model model) {
		this.model = model;
		this.model = setTextMenuUn(model);
		return "financement";
	}
	
	@PostMapping("/envoieCommentaire")
	public String envoieCommentaire(Model model, String commentaire) {
		Projet projet = (Projet) model.getAttribute("projet");
		Long idProjet = projet.getIdProjet();
		Utilisateur utilisateur = (Utilisateur) model.getAttribute("utilisateur");
		commentaireService.writeCommentary(commentaire, projet, utilisateur);
		this.model.addAttribute("idProjet", idProjet);
		this.model = model;
		this.model = setTextMenuUn(model);
		List<Commentaire> listeCommentaires = commentaireService.rechercheCommentaireProjet(projet);
		model.addAttribute("listeCommentaires", listeCommentaires);
		return "page_projet";
	}
	
	@PostMapping("/repondreAdmin")
	public String repondreAdmin(Model model, Long idDiscussion, String reponse) {
		supportDiscussionService.addReponseDiscution(idDiscussion, reponse);
		List<SupportDiscussion> listeDiscussion = supportDiscussionService.getAllDiscutionNotClosed();
		model.addAttribute("listeMessagesOpen", listeDiscussion);
		this.model = setTextMenuUn(model);
		return "adminMessage";
	}
	
	
	@ResponseBody
	@PostMapping("/sendSimpleEmail")
	public String sendSimpleEmail() {

		// Create a Simple MailMessage.
		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(MyConstants.FRIEND_EMAIL);
		message.setSubject("Test Simple Email");
		message.setText("Hello, Im testing Simple Email");

		// Send Message!
		this.emailSender.send(message);

		return "Email Sent!";
	}

}