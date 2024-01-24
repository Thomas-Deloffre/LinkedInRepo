package fr.isika.cdi07.model.don;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Entity
public class Don {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idDon;
	private int montant;
	@ManyToOne
	private Utilisateur utilisateur;
	private DonStatut donStatut;
	@ManyToOne
	private Projet projet;

	public Don() {
	}
	
	public Don(int montant, Projet projet, Utilisateur utilisateur) {
		this.montant = montant;
		this.utilisateur = utilisateur;
		this.projet = projet;
	}

	public int getMontant() {
		return montant;
	}

	public Utilisateur getUtilisateur() {
		return this.utilisateur;
	}

	public DonStatut getDonStatut() {
		return this.donStatut;
	}

	public void setDonStatut(DonStatut donStatut) {
		this.donStatut = donStatut;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Long getIdDon() {
		return idDon;
	}
	public void setIdDon(Long idDon) {
		this.idDon = idDon;
	}
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
}