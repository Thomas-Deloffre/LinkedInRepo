package fr.isika.cdi07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetCategorie;
import fr.isika.cdi07.model.projet.ProjetGenres;
import fr.isika.cdi07.model.projet.ProjetStatut;

@Repository
public interface ProjetRepository extends CrudRepository<Projet, Long>{
	List<Projet> findByIdProjet(Long idProjet);
	List<Projet> findByTitreLike(String titre);
	List<Projet> findByUtilisateurIdUtilisateur(Long utilisateurIdUtilisateur);
	List<Projet> findByUtilisateurIdUtilisateurAndProjetStatut(Long utilisateurIdUtilisateur, ProjetStatut projetStatut);
	List<Projet> findByProjetStatut(ProjetStatut projetStatut);
	List<Projet> findByUtilisateurIdUtilisateurAndProjetStatutIn(Long idUtilisateur, List<ProjetStatut> listePStatut);
	List<Projet> findByCategorie(ProjetCategorie projetCategorie);
	List<Projet> findByGenreMain(ProjetGenres projetGenre);
	List<Projet> findByCategorieAndGenreMain(ProjetCategorie projetCategorie, ProjetGenres projetGenre);
}