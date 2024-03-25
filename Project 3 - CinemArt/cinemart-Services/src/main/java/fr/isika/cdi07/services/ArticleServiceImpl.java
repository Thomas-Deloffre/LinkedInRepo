package fr.isika.cdi07.services;

import java.util.Date;
import java.util.UUID;
import fr.isika.cdi07.dao.ArticleRepository;
import fr.isika.cdi07.model.projet.Article;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

public class ArticleServiceImpl implements ArticleService {
	
	private ArticleRepository articleRepository;
	
	public ArticleServiceImpl(ArticleRepository articleRepository) {
		this.articleRepository = articleRepository;
	}
	
	public Article publierActualite() {
		return null;
	}
	
	@Override
	public Article publierActualite(String titre, String description, Utilisateur porteurProjet, Projet projet) {
		//		Image image= new Image ("a3757627-431d-48f6-8e00-a781f05c2533");
		Article article = new Article(UUID.randomUUID().toString(), titre, null, description, "", new Date());
		article.setIdPorteur(porteurProjet.getIdUtilisateur());
		article.setIdProjet(projet.getIdProjet());
		articleRepository.add(article);
		return article;
	}
}
