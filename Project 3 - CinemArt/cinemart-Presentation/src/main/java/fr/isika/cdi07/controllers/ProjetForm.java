package fr.isika.cdi07.controllers;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.web.multipart.MultipartFile;
import fr.isika.cdi07.model.projet.ProjetCategorie;
import fr.isika.cdi07.model.projet.ProjetGenres;
import fr.isika.cdi07.model.projet.ProjetStatut;
import fr.isika.cdi07.model.utilisateur.PortFolio;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

public class ProjetForm {
	private String idProjet;
	private ProjetStatut projetStatut;
	private String titre;
	private String courteDescription;
	private String longueDescription;
	private String webLink;
	private int cagnotte;
	private PortFolio portfolio;
	private Utilisateur utilisateur;
	private int montantDemande;
	private MultipartFile document1;
	private MultipartFile document2;
	private MultipartFile document3;
	private MultipartFile imageProjet;
	private ProjetCategorie categorie;
	private ProjetGenres genreMain;
	private ProjetGenres genreAlt;
	private String selectedCat;
	private String selectedGenre;
	private LocalDate dateCreation;
	private LocalDate dateFinCampagne;
	private Period dureeCampagne;



	public String getSelectedCat() {
		return selectedCat;
	}
	public void setSelectedCat(String selectedCat) {
		this.selectedCat = selectedCat;
	}
	public String getSelectedGenre() {
		return selectedGenre;
	}
	public void setSelectedGenre(String selectedGenre) {
		this.selectedGenre = selectedGenre;
	}
	public String getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(String idProjet) {
		this.idProjet = idProjet;
	}
	public ProjetStatut getProjetStatut() {
		return projetStatut;
	}
	public void setProjetStatut(ProjetStatut projetStatut) {
		this.projetStatut = projetStatut;
	}
	public ProjetStatut setStatusNewProject(ProjetStatut projetstatut) {
		this.projetStatut = projetstatut;
		return projetStatut;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getCourteDescription() {
		return courteDescription;
	}
	public void setCourteDescription(String courteDescription) {
		this.courteDescription = courteDescription;
	}
	public String getLongueDescription() {
		return longueDescription;
	}
	public void setLongueDescription(String longueDescription) {
		this.longueDescription = longueDescription;
	}
	public String getWebLink() {
		return webLink;
	}
	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}
	public int getCagnotte() {
		return cagnotte;
	}
	public void setCagnotte(int cagnotte) {
		this.cagnotte = cagnotte;
	}
	public PortFolio getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(PortFolio portfolio) {
		this.portfolio = portfolio;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getMontantDemande() {
		return montantDemande;
	}
	public void setMontantDemande(int montantDemande) {
		this.montantDemande = montantDemande;
	}

	public Long getIdDocument (Long idDocument) {
		return idDocument;	
	}
	public MultipartFile getImageProjet() {
		return imageProjet;
	}
	public void setImageProjet(MultipartFile imageProjet) {
		//	System.err.println("TEST ---------------");
		this.imageProjet = imageProjet;
	}
	public ProjetCategorie getCatTest() {
		return categorie;
	}
	public void setCatTest(ProjetCategorie categorie) {
		this.categorie = categorie;
	}
	public ProjetGenres getGenreMain() {
		return genreMain;
	}
	public void setGenreMain(ProjetGenres genreMain) {
		this.genreMain = genreMain;
	}
	public ProjetGenres getGenreAlt() {
		return genreAlt;
	}
	public void setGenreAlt(ProjetGenres genreAlt) {
		this.genreAlt = genreAlt;
	}
	public LocalDate getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDate dateCreation) {
		this.dateCreation = dateCreation;
	}
	public LocalDate getDateFinCampagne() {
		return dateFinCampagne;
	}
	public void setDateFinCampagne(LocalDate dateFinCampagne) {
		this.dateFinCampagne = dateFinCampagne;
	}
	public Period getDureeCampagne() {
		return dureeCampagne;
	}
	public void setDureeCampagne(Period dureeCampagne) {
		this.dureeCampagne = dureeCampagne;
	}
	public MultipartFile getDocument1() {
		return document1;
	}
	public void setDocuments1(MultipartFile document) {
		System.err.println("TEST DOCUMENT---------------");
		this.document1 = document;
	}
	public MultipartFile getDocument2() {
		return document2;
	}
	public void setDocument2(MultipartFile document2) {
		this.document2 = document2;
	}
	public MultipartFile getDocument3() {
		return document3;
	}
	public void setDocument3(MultipartFile document3) {
		this.document3 = document3;
	}
}