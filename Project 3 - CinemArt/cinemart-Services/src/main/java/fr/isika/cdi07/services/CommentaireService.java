package fr.isika.cdi07.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;
import fr.isika.cdi07.dao.CommentaireRepository;
import fr.isika.cdi07.model.messagerie.Commentaire;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Service
public class CommentaireService {

	private CommentaireRepository repository;
	
	public CommentaireService(CommentaireRepository repository) {
		this.setRepository(repository);
	}
	public CommentaireRepository getRepository() {
		return repository;
	}
	public void setRepository(CommentaireRepository repository) {
		this.repository = repository;
	}
	
	public Commentaire writeCommentary(String contenu, Projet projet, Utilisateur utilisateur) {
		Commentaire commentaire = new Commentaire(UUID.randomUUID().toString(), contenu, projet, utilisateur);
		repository.save(commentaire);
		return commentaire;
	}
	
	public Optional<Commentaire> findById(String uuid) {
		return repository.findById(uuid);
	}
	public List<Commentaire> rechercheCommentaireProjet(Projet projet) {
		List<Commentaire> listeCommentaire = repository.findByProjetIdProjet(projet.getIdProjet());
		return listeCommentaire;
	}
	public List<Commentaire> getAllCommentaireUtilisateur(Long idUtilisateur) {
		List<Commentaire> listeCommentaire = repository.findByUtilisateurIdUtilisateur(idUtilisateur);
		return listeCommentaire;
	}
}