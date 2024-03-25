package fr.isika.cdi07.services;

import java.util.Optional;
import java.util.UUID;

import fr.isika.cdi07.dao.CommentaireRepository;
import fr.isika.cdi07.model.messagerie.Commentaire;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

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
}
