package fr.isika.cdi07.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import fr.isika.cdi07.model.projet.Projet;
import fr.isika.cdi07.model.projet.ProjetCategorie;
import fr.isika.cdi07.model.projet.ProjetGenres;
import fr.isika.cdi07.model.projet.ProjetStatut;

public class ProjetRepositoryInMemory implements ProjetRepository {
private List<Projet> projets;
	
	public ProjetRepositoryInMemory() {
		
		this.projets = new ArrayList<Projet>();
	}
	@Override
	public <S extends Projet> S save(S entity) {
		this.projets.add(entity);
		return null;
	}
	@Override
	public Optional<Projet> findById(Long id) {
		
		return projets.stream().findFirst();
	}
	@Override
	public Iterable<Projet> findAll() {
		
		return this.projets;
	}
	@Override
	public long count() {
		
		return this.projets.size();
	}
	@Override
	public <S extends Projet> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterable<Projet> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stu
	}
	@Override
	public void delete(Projet entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll(Iterable<? extends Projet> entities) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Projet> findByTitreLike(String titre) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Projet> findByUtilisateurIdUtilisateur(Long utilisateurIdUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Projet> findByIdProjet(Long idProjet) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Projet> findByProjetStatut(ProjetStatut projetStatut) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Projet> findByUtilisateurIdUtilisateurAndProjetStatut(Long utilisateurIdUtilisateur,
			ProjetStatut projetStatut) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Projet> findByUtilisateurIdUtilisateurAndProjetStatutIn(Long idUtilisateur,
			List<ProjetStatut> listePStatut) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Projet> findByCategorie(ProjetCategorie projetCategorie) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Projet> findByGenreMain(ProjetGenres projetGenre) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Projet> findByCategorieAndGenreMain(ProjetCategorie projetCategorie, ProjetGenres projetGenre) {
		// TODO Auto-generated method stub
		return null;
	}
}
