package fr.isika.cdi07.services;

import java.util.List;

import fr.isika.cdi07.model.projet.Projet;

public interface ProjetServiceInterface {

	List<Projet> getAllProjects();
	Projet getProjet(Long idProjet);
}
