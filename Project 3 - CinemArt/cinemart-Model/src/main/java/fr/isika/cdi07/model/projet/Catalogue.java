package fr.isika.cdi07.model.projet;

import java.util.ArrayList;
import java.util.List;

import fr.isika.cdi07.model.exceptions.InvalidProjectPublicationException;

public class Catalogue {
	private List<Projet> projetsPub = new ArrayList();
	private ProjetStatut projetStatut;
	
	public Catalogue() {
	}
	
	public Catalogue(List<Projet> projetsPub) {
		super();
		this.projetsPub = projetsPub;
	}
	public ProjetStatut getProjetStatut() {
		return projetStatut;
	}
	public void setProjetStatut(ProjetStatut projetStatut) {
		this.projetStatut = projetStatut;
	}

	
	public void publishValidatedProject(Projet projet) throws InvalidProjectPublicationException {
		if (projet.getProjetStatut().equals(ProjetStatut.VALIDATED)) {
			this.projetsPub.add(projet);
		}
		else {throw new InvalidProjectPublicationException("This project is currently inelligible to publication ");
		}
	}

	public List<Projet> getProjetsPub() {
		return projetsPub;
	}
}
