package fr.isika.cdi07.model.utilisateur;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import fr.isika.cdi07.model.exceptions.ProjetNotFoundException;
import fr.isika.cdi07.model.projet.Projet;

@Entity
public class PortFolio {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idPortFolio;
	
	@OneToOne
	private Utilisateur utilisateur;
	
	@OneToMany
	private List<Projet> listProj = new ArrayList(); // ????????
	public PortFolio() {
	}
	public PortFolio(Long idPortFolio) {
		this.idPortFolio = idPortFolio;
	}
	public PortFolio(Utilisateur utilisateur, List<Projet> listProj) {
		super();
		this.utilisateur = utilisateur;
		this.listProj = listProj;
	}
	public void add(Projet projet) {
		this.listProj.add(projet);
	}
	
	public Projet getProjetById(String idProjet) throws ProjetNotFoundException {
		for (Projet p: listProj) {
			if(p.getIdProjet().equals(idProjet)) {
				return p;
			}
		}
		throw new ProjetNotFoundException("Aucun projet trouvé");
	}
	
	public Projet getProjetById(Long idProjet) throws ProjetNotFoundException {
		for (Projet p: listProj) {
			if(p.getIdProjet().equals(idProjet)) {
				return p;
			}
		}
		throw new ProjetNotFoundException("Aucun projet trouvé");
		
	}
	public Long getIdPorteFollio() {
		return idPortFolio;
	}
	public void setIdPorteFollio(Long idPorteFollio) {
		this.idPortFolio = idPorteFollio;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public List<Projet> getListProj() {
		return listProj;
	}
	public void setListProj(List<Projet> listProj) {
		this.listProj = listProj;
	}
}