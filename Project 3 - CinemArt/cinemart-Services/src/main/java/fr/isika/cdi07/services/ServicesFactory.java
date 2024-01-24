package fr.isika.cdi07.services;

public class ServicesFactory {
	
	//Java stocke les oject differemment garde la reference
	private static final ServicesFactory INSTANCE = new  ServicesFactory();

	private ServicesFactory() { // Interdit de creer un servicesFactory a l'exterieur
		// patterne singleton
	}
	
	public static ServicesFactory getInstance() {
		return INSTANCE;
	}
	
	public ProjetServiceInterface getProjetService() {
		return new ProjetService();
	}
}