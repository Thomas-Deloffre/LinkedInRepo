package fr.isika.cdi07.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetStatut;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

public class MettreEnFavorisTest {
	
private static Utilisateur utilisateur = new Utilisateur("nom", "mdp");
	
	@BeforeEach
	public void beforeEachTest() {
		// Re-crée le user avant chaque test pour éviter de garder des choses en mémoire
		// donc la liste des projets favoris sera remise à zéro avant chaque test
		utilisateur = new Utilisateur("nom", "mdp");
	}
	
	@Test
	public void shouldNotTellThatProjectIsFavouriteIsUserHasNoProjects() {
		//Assert
		assertEquals(0, utilisateur.projetsFavorisCount());
		assertFalse(utilisateur.estProjetFavoris("1"));
	}
	
	@Test
	public void shouldAddFavouriteProjectOnceForAUser() {
		//Arrange
		Projet projet = new Projet(ProjetStatut.ACTIVE, "Test", "courteDesc", "longueDesc", "weblink").createProjetWithId(1L);
		//Act
		utilisateur.ajouterProjetAuxFavoris(projet);
		//Assert
		assertEquals(1, utilisateur.projetsFavorisCount());
		assertTrue(utilisateur.estProjetFavoris("1"));
	}
	
	@Test
	public void shouldAddSameFavouriteProjectMultipleTimesForAUser() {
		//Arrange
		Projet projet = new Projet(ProjetStatut.ACTIVE, "Test", "courteDesc", "longueDesc", "weblink").createProjetWithId(1L);
		
		//Act : ajouter 2 fois le mpeme projet
		utilisateur.ajouterProjetAuxFavoris(projet);
		utilisateur.ajouterProjetAuxFavoris(projet);
		
		//Assert : vérifier que le projet a été ajouté une seule fois car c'est le même
		assertTrue(utilisateur.estProjetFavoris("1"));
		assertEquals(1, utilisateur.projetsFavorisCount());
	}
	@Test
	public void shouldAddDifferentFavouriteProjectForAUser() {
		//Arrange
		Projet first = new Projet(ProjetStatut.ACTIVE, "Test", "courteDesc", "longueDesc", "weblink").createProjetWithId(1L);
		Projet second = new Projet(ProjetStatut.ACTIVE, "Test", "courteDesc", "longueDesc", "weblink").createProjetWithId(2L);
		
		//Act : ajouter 2 fois le meme projet
		utilisateur.ajouterProjetAuxFavoris(first);
		utilisateur.ajouterProjetAuxFavoris(second);
		
		//Assert : vérifier que le projet a été ajouté une seule fois car c'est le même
		assertTrue(utilisateur.estProjetFavoris("1"));
		assertTrue(utilisateur.estProjetFavoris("2"));
		assertEquals(2, utilisateur.projetsFavorisCount());
	}
}
