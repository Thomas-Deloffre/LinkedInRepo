package fr.isika.cdi07.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import fr.isika.cdi07.dao.ProjetCriteriaRepository;
import fr.isika.cdi07.dao.ProjetRepository;
import fr.isika.cdi07.model.exceptions.IllegalAttributeSizeException;
import fr.isika.cdi07.model.exceptions.InvalidProjectPublicationException;
import fr.isika.cdi07.model.exceptions.InvalidStatusChangeException;
import fr.isika.cdi07.model.exceptions.InvalidUpdateException;
import fr.isika.cdi07.model.exceptions.ProjetNotFoundException;
import fr.isika.cdi07.model.messagerie.Commentaire;
import fr.isika.cdi07.model.projet.Catalogue;
import fr.isika.cdi07.model.projet.Documents;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetCategorie;
import fr.isika.cdi07.model.projet.ProjetGenres;
import fr.isika.cdi07.model.projet.ProjetPage;
import fr.isika.cdi07.model.projet.ProjetSearchCriteria;
import fr.isika.cdi07.model.projet.ProjetStatut;
import fr.isika.cdi07.model.utilisateur.PortFolio;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

//@Service est équivalent à @Component pour les services transactionnels
@EntityScan(basePackages="fr.isika.cdi07.model.projet")
@EnableJpaRepositories("fr.isika.cdi07.dao")
@Service
public class ProjetService implements ProjetServiceInterface {
	@Autowired
	private ProjetRepository projetRepository;

	private ProjetCriteriaRepository projetCriteriaRepository;

	public ProjetService(ProjetRepository produitRepository) {
		this.projetRepository = produitRepository;
	}

	ProjetService() {
	}
	@Override
	public Projet getProjet(Long idProjet) {
		List<Projet> op = projetRepository.findByIdProjet(idProjet);
		return op.get(0);
	}

	public List<Projet> getProjetUtilisateur(Long idUtilisateur){
		List<Projet> listeProjets = projetRepository.findByUtilisateurIdUtilisateur(idUtilisateur);
		return listeProjets;
	}

	public List<Projet> getProjetActiveUtilisateur(Long idUtilisateur) {
		List<Projet> listeProjets = projetRepository.findByUtilisateurIdUtilisateurAndProjetStatut(idUtilisateur, ProjetStatut.ACTIVE);
		return listeProjets;
	}

	public List<Projet> getProjetValiderUtilisateur(Long idUtilisateur) {
		List<ProjetStatut> listePStatut = new ArrayList<ProjetStatut>();
		List<Projet> listeProjets = projetRepository.findByUtilisateurIdUtilisateurAndProjetStatut(idUtilisateur, ProjetStatut.VALIDATED);
		return listeProjets;
	}

	public List<Projet> getProjetAutresUtilisateur(Long idUtilisateur) {
		List<ProjetStatut> listePStatut = new ArrayList<ProjetStatut>();
		listePStatut.add(ProjetStatut.CLOSED);
		listePStatut.add(ProjetStatut.PENDINGFORVALIDATION);
		listePStatut.add(ProjetStatut.PENDINGPAYMENT);
		listePStatut.add(ProjetStatut.REJECTED);
		listePStatut.add(ProjetStatut.PUBLISHED);
		List<Projet> listeProjets = projetRepository.findByUtilisateurIdUtilisateurAndProjetStatutIn(idUtilisateur, listePStatut);
		return listeProjets;
	}

	public List<Projet> getAllProjetPending(){
		List<Projet> listeProjets = projetRepository.findByProjetStatut(ProjetStatut.PENDINGFORVALIDATION);
		return listeProjets;
	}

	public List<Projet> getAllProjetPaiement() {
		List<Projet> listeProjets = projetRepository.findByProjetStatut(ProjetStatut.PENDINGPAYMENT);
		return listeProjets;
	}	

	public void validateActiveProject(Long idProjet){
		Projet projet = getProjet(idProjet);
		projet.setProjetStatut(ProjetStatut.VALIDATED);
		projetRepository.save(projet);
	}
	public void rejectedActiveProject(Long idProjet){
		Projet projet = getProjet(idProjet);
		projet.setProjetStatut(ProjetStatut.REJECTED);
		projetRepository.save(projet);
	}

	public Projet createProject (String titre, ProjetStatut projetStatut, ProjetCategorie categorie,
			ProjetGenres genreMain, ProjetGenres genreAlt,  String courteDescription, String longueDescription, String webLink, int cagnotte, List<Documents> idDocuments,
			byte[] imageProjet ,List<Commentaire> commentaire, PortFolio portfolio, Utilisateur utilisateur,
			int montantDemande, LocalDate dateCreation, LocalDate dateFinCampagne, Period dureeCampagne ) throws IllegalAttributeSizeException, IOException {
		dateCreation = LocalDate.now();
		dateFinCampagne = LocalDate.of(1, 1, 1);
		dureeCampagne = Period.between(dateCreation, dateFinCampagne);

		Projet project = new Projet(	
				titre,
				projetStatut,
				categorie,
				genreMain,
				genreAlt,
				courteDescription,
				longueDescription,
				webLink,
				cagnotte,	
				idDocuments,
				imageProjet,
				commentaire,
				portfolio,
				utilisateur,
				montantDemande,
				dateCreation,
				dateFinCampagne,
				dureeCampagne);	

		projetRepository.save(project);
		return project;
	}

	public void changeProjectStatus(Projet projet, ProjetStatut newStatus) throws InvalidStatusChangeException {
		if (projet.getProjetStatut() == ProjetStatut.ACTIVE && newStatus == ProjetStatut.PENDINGFORVALIDATION)
			projet.setProjetStatut(newStatus);
		else if (projet.getProjetStatut() == ProjetStatut.PENDINGFORVALIDATION && newStatus == ProjetStatut.REJECTED)
			projet.setProjetStatut(newStatus);
		else if (projet.getProjetStatut() == ProjetStatut.PENDINGFORVALIDATION && newStatus == ProjetStatut.VALIDATED)
			projet.setProjetStatut(newStatus);
		else if (projet.getProjetStatut() == ProjetStatut.PENDINGFORVALIDATION && newStatus == ProjetStatut.ACTIVE)
			projet.setProjetStatut(newStatus);
		else if (projet.getProjetStatut() == ProjetStatut.VALIDATED && newStatus == ProjetStatut.PUBLISHED)
			projet.setProjetStatut(newStatus);
		else if (projet.getProjetStatut() == ProjetStatut.PUBLISHED && newStatus == ProjetStatut.PENDINGPAYMENT)
			projet.setProjetStatut(newStatus);
		else 	if (newStatus == ProjetStatut.CLOSED)
			projet.setProjetStatut(newStatus);
		else {
			StringBuilder sb = new StringBuilder();
			sb.append("This status change is invalid from ");
			sb.append(projet.getProjetStatut());
			sb.append(" to : ");
			sb.append(newStatus);
			throw new InvalidStatusChangeException(sb.toString());
		}	
	}

	public void updateProject(Long idProjet , ProjetStatut projetStatut, String titre, String courteDescription,
			String longueDescription, String webLink, PortFolio portfolio, int montantDemande) throws ProjetNotFoundException, InvalidUpdateException, IllegalAttributeSizeException {
		//		HashMap<String, Projet> listProj = new HashMap<>();

		//listProj.put(projet.getIdProjet(), projet);
		Projet projectToUpdate = portfolio.getProjetById(idProjet);

		if (projectToUpdate.getProjetStatut() != ProjetStatut.REJECTED && projectToUpdate.getProjetStatut() != ProjetStatut.CLOSED
				&& projectToUpdate.getProjetStatut() != ProjetStatut.PENDINGPAYMENT && projectToUpdate.getProjetStatut() != ProjetStatut.PENDINGFORVALIDATION) {
			projectToUpdate.setTitre(titre);
			projectToUpdate.setCourteDescription(courteDescription);
			projectToUpdate.setLongueDescription(longueDescription);
			projectToUpdate.setWebLink(webLink);
			projectToUpdate.setMontantDemande(montantDemande);

		}
		else {
			throw new InvalidUpdateException("Invalid update in this current state ");	
		}

	}

	public Projet publishValidatedProject1(Projet projet, Catalogue catalogue) throws InvalidProjectPublicationException {
		if (projet.getProjetStatut().equals(ProjetStatut.VALIDATED)) {
			catalogue.getProjetsPub().add(projet);
			return projet;
		}
		else {throw new InvalidProjectPublicationException("This project is currently inelligible to publication ");
		}
	}

	public List<Projet> rechercherParMotCle(String motsCles) {
		List<Projet> listeProjet = projetRepository.findByTitreLike(motsCles);
		return listeProjet;
	}

	public Page<Projet> getProjets(ProjetPage projetPage, ProjetSearchCriteria projetSearchCriteria){
		return projetCriteriaRepository.findAllWithFilters(projetPage, projetSearchCriteria);
	}
	public Projet addProjet(Projet projet) {
		return projetRepository.save(projet);
	}

	@Override
	public List<Projet> getAllProjects() {
		return (List<Projet>) projetRepository.findAll();
	}
	public List<Projet> getSixOldProjects() {
		List<Projet> listeProjets = (List<Projet>) projetRepository.findAll();
		Collections.sort(listeProjets, new Comparator<Projet>() {
			@Override
			public int compare(Projet o1, Projet o2) {
				return o1.getDateFinCampagne().compareTo(o2.getDateFinCampagne());
			}
		});
		return listeProjets.subList(0,6); //mika
	}
	public List<Projet> getSixNewProjects() {
		List<Projet> listeProjets = (List<Projet>) projetRepository.findAll();
		Collections.sort(listeProjets, new Comparator<Projet>() {
			@Override
			public int compare(Projet o1, Projet o2) {
				return o1.getDatePublication().compareTo(o2.getDatePublication());
			}
		});
		return listeProjets.subList(0,6); //mika
	}

	public List<Projet> getSixRandom() {
		List<Projet> listeProjets = (List<Projet>) projetRepository.findAll();
		Random rand = new Random();
		List<Projet> listeProjetsRandom = new ArrayList<Projet>();
		for(int i=0; i<6; i++) {
			listeProjetsRandom.add(listeProjets.get(rand.nextInt(listeProjets.size())));
		}
		return listeProjetsRandom;
	}

	public List<Projet> getSixLessMoney() {
		List<Projet> listeProjets = (List<Projet>) projetRepository.findAll();
		Collections.sort(listeProjets, new Comparator<Projet>() {
			@Override
			public int compare(Projet o1, Projet o2) {
				return Integer.compare(o1.getCagnotte(), o2.getCagnotte());
			}
		});
		return listeProjets.subList(0,6);
	}

	public List<Projet> getSixPopular() {
		Projet p = new Projet();
		List<Projet> listeProjets = (List<Projet>) projetRepository.findAll();
		// Tri nombre don
		Collections.sort(listeProjets, new Comparator<Projet>() {

			@Override
			public int compare(Projet o1, Projet o2) {
				return Integer.compare(o1.getNbreDon(), o2.getNbreDon()); // a voir avec classe don
			}
		});
		return listeProjets.subList(0,6); //mika
	}

	public void DeleteProject (Long idProjet) {
		projetRepository.deleteById(idProjet);
	}

	public void saveModificationProjet(Projet projet) {
		projetRepository.save(projet);
	}

	public void pendingActiveProject(Long idProjet) {
		Projet projet = getProjet(idProjet);
		projet.setProjetStatut(ProjetStatut.PENDINGFORVALIDATION);
		projetRepository.save(projet);
	}

	public void paiementProjet(Projet projet) {
		projet.setProjetStatut(ProjetStatut.CLOSED);
		projetRepository.save(projet);
	}

	public List<Projet> searchByCategorie(ProjetCategorie categorie) {
		List<Projet> p = projetRepository.findByCategorie(categorie);
		return p;
	}
	public List<Projet> searchByGenreMain(ProjetGenres genreMain) {
		List<Projet> p = projetRepository.findByGenreMain(genreMain);
		return p;
	}
	public List<Projet> searchByCategorieAndGenreMain(ProjetCategorie categorie, ProjetGenres genreMain) {
		List<Projet> p = projetRepository.findByCategorieAndGenreMain(categorie, genreMain);
		return p;
	}
}