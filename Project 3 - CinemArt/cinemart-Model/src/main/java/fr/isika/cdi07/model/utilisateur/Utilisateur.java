package fr.isika.cdi07.model.utilisateur;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import fr.isika.cdi07.model.don.Don;
import fr.isika.cdi07.model.messagerie.Commentaire;
import fr.isika.cdi07.model.projet.Projet;

@Entity
public class Utilisateur {

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idUtilisateur;
	@NotNull
	private Roles role;
	@NotNull
	private String pseudo;
	@NotNull
	private String motDePasse;
	
	@OneToOne
	private Profil profil;
	
	@OneToOne
	private PortFolio porteFollio;
	
	@OneToMany
	private List<Projet> projets;
	
	@OneToMany
	private Set<Projet> projetsFavoris = new HashSet<>();
	
	@OneToMany
	private List<Commentaire> commentaire;
	
	@OneToMany
	private List<Don> don;

	//to delete
	private static HashMap<Long,Utilisateur> mapUtilisateur = new HashMap<>();

	public Utilisateur() {
	}
	
	public Utilisateur(String login, String mdp) {
		this.pseudo = login;
		this.motDePasse = mdp;
		this.role = Roles.UTILISATEUR;
		this.profil = new Profil();
	}
	public Utilisateur(String login, String mdp, Profil profil) {
		this.pseudo = login;
		this.motDePasse = mdp;
		this.role = Roles.UTILISATEUR;
		this.profil = profil;
	}

	public void addEspaceUtilisateur(EspaceUtilisateur espaceUtilisateur) {
		// TODO Auto-generated method stub

	}

	public int projetsFavorisCount() {
		return this.projetsFavoris.size();
	}

	public void ajouterProjetAuxFavoris(Projet projet) {
		this.projetsFavoris.add(projet);
	}
	
	public boolean estProjetFavoris(String id) {
		if(this.projetsFavoris.isEmpty()) return false;

		return this.projetsFavoris
				.parallelStream()
				.filter(projet -> projet.getIdProjet().equals(id))
				.findFirst()
				.isPresent();
	}
	
	
	// GETTER

	public List<Don> getDon() {
		return don;
	}

	public void setDon(List<Don> don) {
		this.don = don;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", role=" + role + ", pseudo=" + pseudo + ", motDePasse="
				+ motDePasse + ", profil=" + profil + "]";
	}

	public Long getIdUtilisateur() {
		return this.idUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public static HashMap<Long, Utilisateur> getMapUtilisateur() {
		return mapUtilisateur;
	}
	public static Utilisateur getUtilisateurById(Long idUtilisateur) {
		return mapUtilisateur.get(idUtilisateur);
	}
	public String getMotDePasse() {
		return this.motDePasse;
	}
	public PortFolio getPorteFollio() {
		return porteFollio;
	}
	public List<Projet> getProjets() {
		return projets;
	}
	public Roles getRole() {
		return role;
	}
	public Profil getProfil() {
		return profil;
	}

	// SETTER


	public void setPorteFollio(PortFolio porteFollio) {
		this.porteFollio = porteFollio;
	}
	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}
	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	public void setRole(Roles role) {
		this.role = role;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
}
