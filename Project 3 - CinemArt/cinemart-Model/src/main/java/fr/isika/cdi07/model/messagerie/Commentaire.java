package fr.isika.cdi07.model.messagerie;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Entity
public class Commentaire {
	
	@Id
	@NotNull
	private String uuid;
	private String contenu;
	@ManyToOne
	private Projet projet;
	
	@ManyToOne
	private Utilisateur utilisateur;
	
	public Commentaire() {
	}
	
	public Commentaire(String idCommentaire, String contenu, Projet projet, Utilisateur utilisateur) {
		this.uuid = idCommentaire;
		this.contenu = contenu;
		this.projet = projet;
		this.utilisateur = utilisateur;
	}
	
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public String getContenu() {
		return contenu;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String idCommentaire) {
		this.uuid = idCommentaire;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}
