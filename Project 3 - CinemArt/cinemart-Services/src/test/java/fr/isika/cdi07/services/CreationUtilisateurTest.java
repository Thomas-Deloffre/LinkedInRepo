package fr.isika.cdi07.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import fr.isika.cdi07.dao.ArticleRepository;
import fr.isika.cdi07.dao.InMemoryArticleRepository;
import fr.isika.cdi07.dao.UtilisateurRepository;
import fr.isika.cdi07.dao.UtilisateurRepositoryInMemory;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

public class CreationUtilisateurTest {
	

	private UtilisateurRepository utilisateurRepository = new UtilisateurRepositoryInMemory();
	private  UtilisateurService utilisateurService = new UtilisateurService(utilisateurRepository);
	
	@Test
	public void creationUtilisateurAvecLoginMdp() {		
		//Arrange
	}
	
	@Test
	public void exeptionAvecLoginVide() {	
		//Test que la liste reste de meme longueur
		
		//Arrange
		String pseudo = "";
		String motDePasse = "motDePasse";
		String nom = "jeanjean";
		String prenom = "Dupont";
		String email= "jeanjean@gmail.com";
		
		//Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class, 
				() -> { //Act
					utilisateurService.createUtilisateur(pseudo, motDePasse, nom, prenom, email); }
				);
		assertEquals("Impossible d'ajouter un utilisateur sans login!", exception.getMessage());
	}
	
	@Test
	public void exeptionAvecMdpVide() {	
		//Test que la liste reste de meme longueur
		
		//Arrange
		String pseudo = "feu";
		String motDePasse = "";
		String nom = "jeanjean";
		String prenom = "Dupont";
		String email= "jeanjean@gmail.com";
		
		//Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class, 
				() -> { //Act
					utilisateurService.createUtilisateur(pseudo, motDePasse, nom, prenom, email); }
				);
		assertEquals("Impossible d'ajouter un utilisateur sans mdp!", exception.getMessage());
	}
	
	@Test
	public void exeptionAvecMdpSuperieurAVingtQuatreCaractere() {	
		//Test que la liste reste de meme longueur
		
		//Arrange
		String login = "email";
		String motDePasse = "wqertettryyyyryuytiiuouot";

		//Act
		// ID utilisateur  : valeur utilisateur // redondant 2 fois l'id	
//		Iterator<String> iterator= mapUtilisateur.keySet().iterator();
//		while (iterator.hasNext()){
//			iterator.next();
//		}
		
		//Assert
		/*IllegalSizeArgumentException exception = assertThrows(
				IllegalSizeArgumentException.class, 
				() -> { //Act
					utilisateur.add(); }
				);
		assertEquals("Un mot de passe fait 24 caracteres maximum!", exception.getMessage());
		assertEquals(tailleListe, Utilisateur.getMapUtilisateur().size());*/
	}
}