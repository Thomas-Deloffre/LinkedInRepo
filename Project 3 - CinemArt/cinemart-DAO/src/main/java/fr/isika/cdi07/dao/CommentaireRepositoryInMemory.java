package fr.isika.cdi07.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import fr.isika.cdi07.model.messagerie.Commentaire;

public class CommentaireRepositoryInMemory implements CommentaireRepository {
	
	private List<Commentaire> comments = new ArrayList<Commentaire>();
	@Override
	public <S extends Commentaire> S save(S entity) {
		comments.add(entity);
		return entity;
	}
	@Override
	public <S extends Commentaire> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<Commentaire> findById(String id) {
		return comments.stream().filter(comment -> comment.getUuid().equals(id)).findFirst();
	}
	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Iterable<Commentaire> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Iterable<Commentaire> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
	}
	@Override
	public void delete(Commentaire entity) {
		// TODO Auto-generated method stub
	}
	@Override
	public void deleteAll(Iterable<? extends Commentaire> entities) {
		// TODO Auto-generated method stub
	}
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}
	@Override
	public List<Commentaire> findByProjetIdProjet(Long idProjet) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Commentaire> findByUtilisateurIdUtilisateur(Long idUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}
}