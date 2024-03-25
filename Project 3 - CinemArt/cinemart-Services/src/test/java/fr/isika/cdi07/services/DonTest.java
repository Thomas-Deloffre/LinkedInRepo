package fr.isika.cdi07.services;

public class DonTest {
/*
	//private DonRepository repository = new InMemoryDonRepo();
	private UtilisateurRepository repositoryUsers = new UtilisateurRepositoryInMemory();
	private DonService service;
	private Utilisateur donateur;
	
	@BeforeEach
	public void setUp() {
		donateur = new Utilisateur("Luc", "Arne", "LucArne@gmail.com", "motdepasseRobuste");
		repositoryUsers.save(donateur);	
		service = new DonService(repositoryUsers);
	}
	
	@Test
	void shouldHaveAUser() {
		assertEquals(1, repositoryUsers.count());
		Utilisateur donateur2 = new Utilisateur("Luc", "Arne", "LucArne@gmail.com", "motdepasseRobuste");
		repositoryUsers.save(donateur2);
		repositoryUsers.save(donateur2);
		assertEquals(2, repositoryUsers.count());
	}
	
	@Test
	public void faireUnDonUnEuro() {
		
		//Arrange
		Projet projet = new Projet(ProjetStatut.ACTIVE, "Super projet test", "Un projet pour les test", 
				"Une tres longue description", "https://monprojetTest.fr");
		EspaceUtilisateur espaceUtilisateur = new EspaceUtilisateur(donateur, projet);
		int montant = 1;
		Don don = new Don(montant, espaceUtilisateur);

		//Act
		service.donner(don, projet);

		//Assert
		// 1 le don envoyer et recu sont les memes
		int actual = don.getMontant();
		assertEquals(montant, actual);

		// 2 Incremente du montant Don la cagnotte
		assertEquals(1, projet.getCagnotte());
	}

	@Test
	public void faireUnDonDePlusDeUnEuro() {
		//Arrange
		Projet projet = new Projet(ProjetStatut.ACTIVE, "Super projet test", "Un projet pour les test", 
				"Une tres longue description", "https://monprojetTest.fr");
		EspaceUtilisateur espaceUtilisateur = new EspaceUtilisateur(donateur, projet);
		
		int montant = 5;
		Don don = new Don(montant, espaceUtilisateur);

		//Act
		Don actual = service.donner(don, projet);

		//Assert
		// 1 le don envoyer et recu sont les memes
		assertEquals(montant, actual.getMontant());

		// 2 Incremente du montant Don la cagnotte
		assertEquals(5, projet.getCagnotte());
	}

	@Test
	public void faireUnDonEnregistreEspaceUtilisateur() {
		
		// arrange
		Projet projet = new Projet(ProjetStatut.ACTIVE, "Super projet test", "Un projet pour les test", 
				"Une tres longue description", "https://monprojetTest.fr");
		EspaceUtilisateur espaceUtilisateur = new EspaceUtilisateur(donateur, projet);
		
		//Act
		Don actual = service.enregistrerDon(5, 1, projet);

		//Assert
		assertNotNull(actual);
		assertEquals(5, actual.getMontant());
		assertEquals(espaceUtilisateur, actual.getEspaceUtilisateur());	
	}

	@Test
	void exceptionDonInferieurAUnEuro() {
		//Arrange
		Projet projet = new Projet(ProjetStatut.ACTIVE, "Super projet test", "Un projet pour les test", 
				"Une tres longue description", "https://monprojetTest.fr");
		EspaceUtilisateur espaceUtilisateur = new EspaceUtilisateur(donateur, projet);
		Don don = new Don(-1, espaceUtilisateur);

		//Assert
		IllegalArgumentException exception = assertThrows(
				IllegalArgumentException.class, 
				() -> { //Act
					service.donner(don, projet); }
				);
		assertEquals("Impossible de faire un don inferieur a un euro!", exception.getMessage());
		assertEquals(0, projet.getCagnotte());
	}

	@Test
	void faireUnDonSuperieurOuEgalMilleEuroSetPending() {
		//Arrange
		Projet projet = new Projet(ProjetStatut.ACTIVE, "Super projet test", "Un projet pour les test", 
				"Une tres longue description", "https://monprojetTest.fr");
		EspaceUtilisateur espaceUtilisateur = new EspaceUtilisateur(donateur, projet);
		int montant = 1000;
		Don don = new Don(montant, espaceUtilisateur);

		//Act
		service.donner(don, projet);

		//Assert
		assertEquals(DonStatut.PENDING, don.getDonStatut());
	}

	@Test
	void faireUnDonInferieurMilleEuroMetDone() {
		//Arrange
		Projet projet = new Projet(ProjetStatut.ACTIVE, "Super projet test", "Un projet pour les test", 
				"Une tres longue description", "https://monprojetTest.fr");
		EspaceUtilisateur espaceUtilisateur = new EspaceUtilisateur(donateur, projet);
		Don don = new Don(5, espaceUtilisateur);

		//Act
		service.donner(don, projet);

		//Assert
		assertEquals(DonStatut.DONE, don.getDonStatut());
	} */
}
