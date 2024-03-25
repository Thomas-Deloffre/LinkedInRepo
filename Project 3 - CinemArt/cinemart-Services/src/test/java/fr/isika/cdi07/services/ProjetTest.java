package fr.isika.cdi07.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import fr.isika.cdi07.dao.ProjetRepository;
import fr.isika.cdi07.dao.ProjetRepositoryInMemory;
import fr.isika.cdi07.model.exceptions.IllegalAttributeSizeException;
import fr.isika.cdi07.model.exceptions.InvalidProjectPublicationException;
import fr.isika.cdi07.model.exceptions.InvalidStatusChangeException;
import fr.isika.cdi07.model.exceptions.InvalidUpdateException;
import fr.isika.cdi07.model.exceptions.ProjetNotFoundException;
import fr.isika.cdi07.model.projet.Catalogue;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetStatut;
import fr.isika.cdi07.model.utilisateur.PortFolio;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

public class ProjetTest {/*
	private ProjetRepository projetRepository = new ProjetRepositoryInMemory();
	private ProjetService projetservice = new ProjetService();
	private Utilisateur gestionnaireProjet = new Utilisateur("Luc", "motdepasseRobuste");
	private PortFolio portfolio = new PortFolio("59deb198-5596-4d5d-aa34-f7bb3f018b93");
	
	@Test
	public void shouldCreateProjectThatNotExists() throws ProjetNotFoundException {
		// ARRANGE / toutes les données besoin
		
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88", ProjetStatut.ACTIVE, "titre",
				"courteDescription", "longueDescription", "https://monprojetTest.fr",gestionnaireProjet, portfolio);
		// ACT: que teste t'on?
		portfolio.add(projet);
		// ASSERT: ce qu'on test
		Projet actual = portfolio.getProjetById("ee759f93-9fe4-410f-bcae-cd4ee28d6f88");
		assertEquals(projet, actual);
	}
	@Test
	public void shouldntCreateProjectThatExists() throws ProjetNotFoundException {
		// ARRANGE / toutes les données besoin
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.ACTIVE,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		// on ajoute une 1ère fois
		portfolio.add(projet);
		// ACT: ajouter le même projet 2 fois => exception !!
		portfolio.add(projet);

		// ASSERT: ce qu'on test
		try {
			portfolio.getProjetById("ee759f93-9fe4-410f-bcae-cd4ee28d6f88");
		} catch (ProjetNotFoundException ex) {
			assertEquals("Aucun projet trouvé", ex.getMessage());
		}
	}	

	@Test
	public void canChangeActiveProjectToPendingForValidationProject() throws InvalidStatusChangeException  {
		// ARRANGE / toutes les données besoin
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.ACTIVE,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet2 = new Projet("ee755g93-9fe4-487ff-bcae-cd4eesf28",  ProjetStatut.PENDINGFORVALIDATION,
				"titre2", "courteDescription2", "longueDescription2", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		// on modifie le statut du projet après création et soumission
		projetservice.changeProjectStatus(projet, ProjetStatut.PENDINGFORVALIDATION);		

		// ASSERT: ce qu'on test
		assertEquals(projet.getProjetStatut(), projet2.getProjetStatut());

	}	

	@Test
	public void canChangeToRejectedStatusWhenProjectIsPendingForValidation() throws InvalidStatusChangeException  {
		// ARRANGE / toutes les données besoin
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.PENDINGFORVALIDATION,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet2 = new Projet("ee755g93-9fe4-487ff-bcae-cd4eesf28",  ProjetStatut.REJECTED,
				"titre2", "courteDescription2", "longueDescription2", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		// on modifie le statut du projet après création et soumission
		projetservice.changeProjectStatus(projet, ProjetStatut.REJECTED);

		// ASSERT: ce qu'on test
		assertEquals(projet.getProjetStatut(), projet2.getProjetStatut());

	}	

	@Test
	public void canChangeToValidatedStatusWhenProjectIsPendingForValidation() throws InvalidStatusChangeException  {
		// ARRANGE / toutes les données besoin
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.PENDINGFORVALIDATION,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet2 = new Projet("ee755g93-9fe4-487ff-bcae-cd4eesf28",  ProjetStatut.VALIDATED,
				"titre2", "courteDescription2", "longueDescription2", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		// on modifie le statut du projet après création et soumission
		projetservice.changeProjectStatus(projet, ProjetStatut.VALIDATED);

		// ASSERT: ce qu'on test
		assertEquals(projet.getProjetStatut(), projet2.getProjetStatut());

	}	

	@Test
	public void canRevertToActiveStatusWhenProjectIsPendingForValidation() throws InvalidStatusChangeException  {
		// ARRANGE / toutes les données besoin
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.PENDINGFORVALIDATION,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet2 = new Projet("ee755g93-9fe4-487ff-bcae-cd4eesf28",  ProjetStatut.ACTIVE,
				"titre2", "courteDescription2", "longueDescription2", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		// on modifie le statut du projet après création et soumission
		projetservice.changeProjectStatus(projet, ProjetStatut.ACTIVE);

		// ASSERT: ce qu'on test
		assertEquals(projet.getProjetStatut(), projet2.getProjetStatut());

	}

	@Test
	public void canChangeToPublishedStatusWhenProjectIsValidated() throws InvalidStatusChangeException  {
		// ARRANGE / toutes les données besoin
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.VALIDATED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet2 = new Projet("ee755g93-9fe4-487ff-bcae-cd4eesf28",  ProjetStatut.PUBLISHED,
				"titre2", "courteDescription2", "longueDescription2", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		// on modifie le statut du projet après création et soumission
		projetservice.changeProjectStatus(projet, ProjetStatut.PUBLISHED);

		// ASSERT: ce qu'on test
		assertEquals(projet.getProjetStatut(), projet2.getProjetStatut());

	}	

	@Test
	public void canChangeToPendingPaymentStatusWhenProjectIsPublished() throws InvalidStatusChangeException  {
		// ARRANGE / toutes les données besoin
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.PUBLISHED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet2 = new Projet("ee755g93-9fe4-487ff-bcae-cd4eesf28",  ProjetStatut.PENDINGPAYMENT,
				"titre2", "courteDescription2", "longueDescription2", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		// on modifie le statut du projet après création et soumission
		projetservice.changeProjectStatus(projet, ProjetStatut.PENDINGPAYMENT);

		// ASSERT: ce qu'on test
		assertEquals(projet.getProjetStatut(), projet2.getProjetStatut());

	}	

	@Test
	public void canChangeToClosedStatusWhenProjectIsPendingpayment() throws InvalidStatusChangeException  {
		// ARRANGE / toutes les données besoin
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.PENDINGPAYMENT,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr",gestionnaireProjet, portfolio);
		Projet projet2 = new Projet("ee755g93-9fe4-487ff-bcae-cd4eesf28",  ProjetStatut.CLOSED,
				"titre2", "courteDescription2", "longueDescription2", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		// on modifie le statut du projet après création et soumission
		projetservice.changeProjectStatus(projet, ProjetStatut.CLOSED);

		// ASSERT: ce qu'on test
		assertEquals(projet.getProjetStatut(), projet2.getProjetStatut());

	}	


	@Test
	public void exceptionChangingToForbiddenStatusFromCurrentStatus() {
		//Arrange
		Projet projet = new Projet("ee759f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.REJECTED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		StringBuilder sb = new StringBuilder();
		sb.append("This status change is invalid from ");
		sb.append(projet.getProjetStatut());
		sb.append(" to : ");
		sb.append(ProjetStatut.ACTIVE);
		//Assert
		InvalidStatusChangeException exception = assertThrows(
				InvalidStatusChangeException.class,
				() -> { //Act
					projetservice.changeProjectStatus(projet, ProjetStatut.ACTIVE); }
				);
		assertEquals(sb.toString(), exception.getMessage());

	}

	@Test
	public void shouldPublishProjectsInCatalog () throws InvalidProjectPublicationException {
		//Arrange : On crée un catalogue et des projets à publier
		List<Projet> projetsPub = new ArrayList<Projet>();
		Catalogue catalog = new Catalogue(projetsPub);	
		Projet projet1 = new Projet("ee7719853-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.VALIDATED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet2 = new Projet("ee3558f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.VALIDATED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet3 = new Projet("ee9545f93-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.VALIDATED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet4 = new Projet("ee7542793-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.VALIDATED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);
		Projet projet5 = new Projet("ee7745893-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.VALIDATED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);

		//On enregistre les projets dans le dummy Catalog
		projetservice.publishValidatedProject1(projet1, catalog); //en utilisant la methode provenant de ProjetService
		projetservice.publishValidatedProject1(projet2, catalog);
		projetservice.publishValidatedProject1(projet3, catalog); 
		projetservice.publishValidatedProject1(projet4, catalog);
		projetservice.publishValidatedProject1(projet5, catalog);

		//Assert		
		int expected = 5;
		assertEquals(projetsPub.stream().count() , expected);
	}
	
	
	@Test
	public void exceptionPublishingNonValidatedProjects() {
		//Arrange : On crée un catalogue et des projets à publier avec differents status
		List<Projet> projetsPub = new ArrayList<Projet>();
		Catalogue catalog = new Catalogue(projetsPub);	
		Projet projet1 = new Projet("ee7719853-9fe4-410f-bcae-cd4ee28d6f88",  ProjetStatut.REJECTED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", gestionnaireProjet, portfolio);

		//Assert	//On enregistre les projets dans le dummy Catalog	
		InvalidProjectPublicationException exception = assertThrows(
				InvalidProjectPublicationException.class,
				() -> { //Act
					catalog.publishValidatedProject(projet1); }
				);
		assertEquals("This project is currently inelligible to publication ", exception.getMessage());
	}
	
	@Test
	public void shouldUpdateProject () throws ProjetNotFoundException, InvalidUpdateException, IllegalAttributeSizeException {
		//Arrange : On crée un catalogue et des projets à publier	
		Projet projet1 = new Projet("4567",  ProjetStatut.VALIDATED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr",  portfolio, 1000);
		Projet projet2 = new Projet("14788",  ProjetStatut.VALIDATED,
				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", portfolio, 1000);
	

		//On enregistre les projets dans le dummy Catalog
		portfolio.add(projet1);
		portfolio.add(projet2);
		projetservice.updateProject("4567", ProjetStatut.VALIDATED, "niceTitle", "courteDescription1", "longueDescription1", "url", portfolio, 2000);
		
		//Assert		
		String expected = portfolio.getProjetById("14788").getTitre();
		assertNotEquals(portfolio.getProjetById("4567").getTitre() , expected);
	}
	
	
	@Test
	public void shouldntUpdateProjectWhenNotAllowed () throws ProjetNotFoundException, InvalidUpdateException {
		//Arrange : On crée un catalogue et des projets à publier
		Projet projet1 = new Projet("4567",  ProjetStatut.REJECTED,
				"titre1", "courteDescription", "longueDescription", "https://monprojetTest.fr",  portfolio, 1000);

		//On enregistre les projets dans le dummy Catalog
		portfolio.add(projet1);
	
		//Assert		
		InvalidUpdateException exception = assertThrows(
				InvalidUpdateException.class,
				() -> { //Act
					projetservice.updateProject("4567", ProjetStatut.REJECTED, "niceTitle", "courteDescription1", "longueDescription1", "url", portfolio, 2000); }
				);
		assertEquals("Invalid update in this current state ", exception.getMessage());
	}
	
	
	
	@Test
	public void shouldntUpdateProjectWithNullFields () throws ProjetNotFoundException, InvalidUpdateException, IllegalAttributeSizeException {
		//Arrange : On crée un catalogue et des projets à publier
		Projet projet1 = new Projet("4567",  ProjetStatut.ACTIVE,
				"titre1", "courteDescription", "longueDescription", "https://monprojetTest.fr",  portfolio, 1000);

		//On enregistre les projets dans le dummy Catalog
		portfolio.add(projet1);
	
		//Assert		
		IllegalAttributeSizeException exception = assertThrows(
				IllegalAttributeSizeException.class,
				() -> { //Act
					projetservice.updateProject("4567", ProjetStatut.ACTIVE, null, "courteDescription1", "longueDescription1", "url", portfolio, 2000); }
				);
		assertEquals("This element is null", exception.getMessage());
	}

	
	@Test
	public void shouldntUpdateProjectWithTooShortFields () throws ProjetNotFoundException, InvalidUpdateException, IllegalAttributeSizeException {
		//Arrange : On crée un catalogue et des projets à publier
		Projet projet1 = new Projet("4567",  ProjetStatut.ACTIVE,
				"titre1", "courteDescription", "longueDescription", "https://monprojetTest.fr",  portfolio, 1000);

		//On enregistre les projets dans le dummy Catalog
		portfolio.add(projet1);
	
		//Assert		
		IllegalAttributeSizeException exception = assertThrows(
				IllegalAttributeSizeException.class,
				() -> { //Act
					projetservice.updateProject("4567", ProjetStatut.ACTIVE, "ti", "courteDescription1", "longueDescription1", "url", portfolio, 2000); }
				);
		assertEquals("This element is too short (less than 3 chars)", exception.getMessage());
	}
	
	
	@Test
	public void shouldntUpdateProjectWithTooLongFields () throws ProjetNotFoundException, InvalidUpdateException, IllegalAttributeSizeException {
		//Arrange : On crée un catalogue et des projets à publier
		Projet projet1 = new Projet("4567",  ProjetStatut.ACTIVE,
				"titre1", "courteDescription", "longueDescription", "https://monprojetTest.fr",  portfolio, 1000);

		//On enregistre les projets dans le dummy Catalog
		portfolio.add(projet1);
	
		//Assert		
		IllegalAttributeSizeException exception = assertThrows(
				IllegalAttributeSizeException.class,
				() -> { //Act
					projetservice.updateProject("4567", ProjetStatut.ACTIVE, "titrebeaucouptroplongpouretreaccepeteentantquetel", "courteDescription1", "longueDescription1", "url", portfolio, 2000); }
				);
		assertEquals("This element is too long (greater than 30 chars)", exception.getMessage());
	}	
	
	//	@Test
	//	public void shouldPublishValidatedProjectsOnlyInCatalog () throws InvalidProjectPublicationException {
	//		//Arrange : On crée un catalogue et des projets à publier avec differents status
	//		List<Projet> projetsPub = new ArrayList<Projet>();
	//		Catalogue catalog = new Catalogue(projetsPub);	
	//		Utilisateur utilisateur = new Utilisateur("a9835e0e-6ec1-4e32-a7f9-36588b547df7", "Luc",
	//				"Arne", "LucArne@gmail.com", "motdepasseRobuste");
	//		PortFolio portfolio = new PortFolio("59deb198-5596-4d5d-aa34-f7bb3f018b93");
	//		Projet projet1 = new Projet("1234",  ProjetStatut.REJECTED,
	//				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", utilisateur, portfolio);
	//		Projet projet2 = new Projet("4567",  ProjetStatut.REJECTED,
	//				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", utilisateur, portfolio);
	//		Projet projet3 = new Projet("7891",  ProjetStatut.REJECTED,
	//				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", utilisateur, portfolio);
	//		Projet projet4 = new Projet("45677",  ProjetStatut.VALIDATED,
	//				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", utilisateur, portfolio);
	//		Projet projet5 = new Projet("14788",  ProjetStatut.VALIDATED,
	//				"titre", "courteDescription", "longueDescription", "https://monprojetTest.fr", utilisateur, portfolio);
	//		
	//		//On enregistre les projets dans le dummy Catalog
	//		catalog.publishValidatedProjects(projet1, projet2, projet3, projet4, projet5);
	//		
	//		Optional<Projet> numberProj = catalog.getProjectsById("14788");
	//		assertTrue(numberProj.isPresent());
	//
	//		Optional<Projet> numberProj2 = catalog.getProjectsById("45677");
	//		assertTrue(numberProj2.isPresent());
	//	}*/
}
