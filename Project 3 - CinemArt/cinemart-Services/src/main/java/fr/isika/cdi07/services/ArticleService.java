package fr.isika.cdi07.services;

import fr.isika.cdi07.model.projet.Article;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

public interface ArticleService {
	Article publierActualite(String titre, String description, Utilisateur porteurProjet, Projet projet);
}
