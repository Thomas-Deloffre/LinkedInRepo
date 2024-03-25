package fr.isika.cdi07.model.projet;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import fr.isika.cdi07.model.don.Don;
import fr.isika.cdi07.model.exceptions.IllegalAttributeSizeException;
import fr.isika.cdi07.model.messagerie.Commentaire;
import fr.isika.cdi07.model.utilisateur.PortFolio;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Entity
public class Projet {	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idProjet;
	@NotNull
	@Enumerated(EnumType.STRING)
	private ProjetStatut projetStatut;
	@NotNull
	@Enumerated(EnumType.STRING)
	private ProjetCategorie categorie;
	@Enumerated(EnumType.STRING)
	private ProjetGenres genreMain;
	@Enumerated(EnumType.STRING)
	private ProjetGenres genreAlt;
	@NotNull
	private String titre;
	@NotNull
	private String courteDescription;
	@NotNull
	private String longueDescription;
	private String webLink;
	private int cagnotte;
	private LocalDate dateCreation;
	private LocalDate datePublication;
	private LocalDate dateFinCampagne;
	private Period dureeCampagne;
	@Lob
	private byte[] imageProjet;
	@OneToMany
	private List<Documents> document;
	@OneToMany
	private List<Commentaire> commentaire;
	@ManyToOne
	private PortFolio portfolio;
	@ManyToOne
	private Utilisateur utilisateur;
	private int montantDemande;
	@OneToMany
	private List<Don> don;
	
	public Projet() {
	}
	
	public Projet(ProjetStatut projetStatut, String titre, String courteDescription,
			String longueDescription, String webLink) {
		super();
		this.projetStatut = projetStatut;
		this.titre = titre;
		this.courteDescription = courteDescription;
		this.longueDescription = longueDescription;
		this.webLink = webLink;
	}
	
	public Projet(Long idProjet, ProjetStatut projetStatut, String titre, String courteDescription,
			String longueDescription, String webLink, Utilisateur utilisateur, PortFolio portfolio) {
		super();
		this.idProjet = idProjet;
		this.projetStatut = projetStatut;
		this.titre = titre;
		this.courteDescription = courteDescription;
		this.longueDescription = longueDescription;
		this.webLink = webLink;
		this.utilisateur = utilisateur;
		this.portfolio = portfolio;
	}
	
	public Projet(Long idProjet, ProjetStatut projetStatut, String titre, String courteDescription,
			String longueDescription, String webLink, PortFolio portfolio, int montantDemande) {
		super();
		this.idProjet = idProjet;
		this.projetStatut = projetStatut;
		this.titre = titre;
		this.courteDescription = courteDescription;
		this.longueDescription = longueDescription;
		this.webLink = webLink;
		this.portfolio = portfolio;
		this.montantDemande = montantDemande;
	}
	
	public Projet(Long idProjet,String titre, ProjetStatut projetStatut, ProjetCategorie categorie, ProjetGenres genreMain, ProjetGenres genreAlt,
			 String courteDescription, String longueDescription, String webLink, int cagnotte, List<Documents> document,
           List<Commentaire> commentaire, PortFolio portfolio, Utilisateur utilisateur, int montantDemande) {
       super();
       this.idProjet = idProjet;
       this.titre = titre;
       this.projetStatut = projetStatut;
       this.categorie = categorie;
       this.genreMain = genreMain;
       this.genreAlt = genreAlt;
       this.courteDescription = courteDescription;
       this.longueDescription = longueDescription;
       this.webLink = webLink;
       this.cagnotte = cagnotte;
       this.document = document;
       this.commentaire = commentaire;
       this.portfolio = portfolio;
       this.utilisateur = utilisateur;
       this.montantDemande = montantDemande;
    }
	
	public Projet( String titre, ProjetStatut projetStatut,  ProjetCategorie categorie,
			ProjetGenres genreMain, ProjetGenres genreAlt,  String courteDescription,
			String longueDescription, String webLink, int cagnotte,
			List<Documents> document, byte[] imageProjet, List<Commentaire> commentaire, PortFolio portfolio, Utilisateur utilisateur,
			int montantDemande, LocalDate dateCreation, LocalDate dateFinCampagne, Period dureeCampagne) {
		super();		
		this.titre = titre;
		this.projetStatut = projetStatut;
		this.categorie = categorie;
		this.genreMain = genreMain;
		this.genreAlt = genreAlt;		
		this.courteDescription = courteDescription;
		this.longueDescription = longueDescription;
		this.webLink = webLink;
		this.imageProjet = imageProjet;
		this.cagnotte = cagnotte;
		this.document = document;
		this.commentaire = commentaire;
		this.portfolio = portfolio;
		this.utilisateur = utilisateur;
		this.montantDemande = montantDemande;
		this.dateCreation = dateCreation;
		this.dateFinCampagne = dateFinCampagne;
		this.dureeCampagne = dureeCampagne;
	}
	
	public void augmenteCagnotte(int montant) {
		this.cagnotte = this.cagnotte + montant;	
	}
	
	public Projet createProjetWithId(Long idProjet) {
		this.idProjet = idProjet;
		return this;
	}

	/***** GETTER ****/
	
	public String getCourteDescription() {
		return courteDescription;
	}
	public String getLongueDescription() {
		return longueDescription;
	}
	public ProjetStatut getProjetStatut() {
		return projetStatut;
	}
	public String getTitre() {
		return this.titre;
	}
	public int getCagnotte() {
		return this.cagnotte;
	}
	public Long getIdProjet() {
		return this.idProjet;
	}
	public String getWebLink() {
		return webLink;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public int getMontantDemande() {
		return montantDemande;
	}
	public PortFolio getPortfolio() {
		return portfolio;
	}
	public ProjetCategorie getCategorie() {
		return categorie;
	}
	public ProjetGenres getGenreMain() {
		return genreMain;
	}
	public ProjetGenres getGenreAlt() {
		return genreAlt;
	}
	public List<Documents> getDocument() {
		return document;
	}
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public Period getDureeCampagne() {
		return dureeCampagne;
	}
	public List<Commentaire> getCommentaire() {
		return commentaire;
	}
	public LocalDate getDatePublication() {
		return datePublication;
	}
	public void setDatePublication(LocalDate datePublication) {
		this.datePublication = datePublication;
	}
	public LocalDate getDateFinCampagne() {
		return dateFinCampagne;
	}
	public void setDateFinCampagne(LocalDate dateFinCampagne) {
		this.dateFinCampagne = dateFinCampagne;
	}
	public byte[] getImageProjet() {
		return imageProjet;
	}
	public void setImageProjet(byte[] imageProjet) {
		this.imageProjet = imageProjet;
	}
	
	/**** AND SETTER ****/
	public void setTitre(String titre) throws IllegalAttributeSizeException{
		if (titre == null) throw  new IllegalAttributeSizeException("This element is null");
		if (titre.length() < 3) throw new IllegalAttributeSizeException("This element is too short (less than 3 chars)");
		if (titre.length() > 50) throw new IllegalAttributeSizeException("This element is too long (greater than 30 chars)");
		this.titre = titre;
	}
	
	public void setCourteDescription(String courteDescription) throws IllegalAttributeSizeException {
		if (courteDescription == null) throw new IllegalAttributeSizeException("this element is null");
		if (courteDescription.length() < 10)	throw new IllegalAttributeSizeException("This element is too short (less than 10 chars)");
		if (courteDescription.length() > 100) throw new IllegalAttributeSizeException("This element is too long (greater than 30 chars)");
		this.courteDescription = courteDescription;
	}
	public void setLongueDescription(String longueDescription) throws IllegalAttributeSizeException {
		if (longueDescription == null) throw new IllegalAttributeSizeException("this element is null");
		if (longueDescription.length() < 10) throw new IllegalAttributeSizeException("This element is too short (less than 50 chars)"); //Valeur min à modifier
		if(longueDescription.length() > 10000) throw new IllegalAttributeSizeException("This element is too long (greater than 10000 chars)");			
		this.longueDescription = longueDescription;
	}
	public void setMontantDemande(int montantDemande) throws IllegalAttributeSizeException {
		if (montantDemande > 100 && montantDemande < 1000000) { // test unitaire superieur, inferieur, egaux, dans les chevrons 5 => 0 100 1 000 000 et 1 000 050 et 1000
			this.montantDemande = montantDemande;
		}
		else if(montantDemande < 100) {
			throw new IllegalAttributeSizeException("This element is too short (lesser than 100 euros)");
		}
		else {
			throw new IllegalAttributeSizeException("This element is too important (greater than 1000000 euros)");
		}
	}
	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}
	public void setProjetStatut(ProjetStatut newStatus) {
		this.projetStatut = newStatus;
	}
	public void setCategorie(ProjetCategorie categorie) {
		this.categorie = categorie;
	}
	public void setGenreMain(ProjetGenres genreMain) {
		this.genreMain = genreMain;
	}
	public void setGenreAlt(ProjetGenres genreAlt) {
		this.genreAlt = genreAlt;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public int getNbreDon() {
		return this.don.size();
	}
}