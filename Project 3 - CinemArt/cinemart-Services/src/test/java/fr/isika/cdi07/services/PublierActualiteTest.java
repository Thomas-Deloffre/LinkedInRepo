package fr.isika.cdi07.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.isika.cdi07.dao.ArticleRepository;
import fr.isika.cdi07.dao.InMemoryArticleRepository;
import fr.isika.cdi07.model.projet.Article;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetStatut;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

public class PublierActualiteTest {
	
	private ArticleRepository articleRepository = new InMemoryArticleRepository();
	private ArticleService articleService = new ArticleServiceImpl(articleRepository);
	
	@BeforeEach
	public void setInit() {
	}
	
	@Test
	public void shouldAddOneArticle(){
		//  Le porteur de projet va pouvoir créer une actualité sur son projet et la publier. L'actualité contiendra le titre, optionnellement une image,
		// une courte descritpion, le texte, la date.
		//Arrange
		Utilisateur porteurProjet = new Utilisateur("toto", "motdepasse");
		Projet projet = new Projet( ProjetStatut.ACTIVE, "Projet1", "courteDesc", "longueDesc", "lien");
		
		//Act
		//1 où? 2) comment verbe 3) quoi (sera en parametre)?
		Article article = articleService.publierActualite("Titre", "Description", porteurProjet, projet);
		//Assert
		assertEquals("Titre", article.getTitre());
		assertEquals("Description", article.getCourteDescription());
		
		assertEquals("e8922efa-7cd5-4d17-a722-856dd77883bb", article.getIdPorteur());
		assertEquals("8b3dae48-20d6-43b6-9815-eab22f233171", article.getIdProjet());
		
		assertNotNull(article.getDate());
		assertNotNull(article.getIdArticle());
		
		assertEquals(1, articleRepository.all().size());
		assertEquals(article, articleRepository.all().get(0));
	}

}
