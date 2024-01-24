package fr.isika.cdi07.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.isika.cdi07.dao.CommentaireRepository;
import fr.isika.cdi07.dao.CommentaireRepositoryInMemory;
import fr.isika.cdi07.model.messagerie.Commentaire;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetStatut;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

public class EcrireUnCommentaire {
	private CommentaireRepository repositoryCommentaire;
	private CommentaireService service;
	private Utilisateur utilisateur= new Utilisateur("toto", "tata");
	private Projet projet = new Projet(ProjetStatut.ACTIVE, "Titre super projet", 
			"courteDesc", "longueDesc", "weblink");
	
	@BeforeEach
	public void setUp() {
		repositoryCommentaire = new CommentaireRepositoryInMemory() ;
		service = new CommentaireService(repositoryCommentaire);
	}
	
	@Test
	public void shoulAddOneComment() {
		// je suis un utilisateur (visiteur) qui souhaite pouvoir faire un commentaire sur un projet
		//ACT
		Commentaire actual = service.writeCommentary("contenu du comment", projet, utilisateur);
		//ASSERT
		assertNotNull(actual.getUuid());
		assertEquals("contenu du comment", actual.getContenu());
		assertEquals(projet, actual.getProjet());
		assertEquals(utilisateur, actual.getUtilisateur());
		assertTrue(service.findById(actual.getUuid()).isPresent());
	}
}