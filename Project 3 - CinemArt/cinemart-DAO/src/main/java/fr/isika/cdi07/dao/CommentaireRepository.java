package fr.isika.cdi07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.isika.cdi07.model.messagerie.Commentaire;

@Repository
public interface CommentaireRepository extends CrudRepository<Commentaire, String> {
	List<Commentaire> findByProjetIdProjet(Long idProjet);
	List<Commentaire> findByUtilisateurIdUtilisateur(Long idUtilisateur);
}