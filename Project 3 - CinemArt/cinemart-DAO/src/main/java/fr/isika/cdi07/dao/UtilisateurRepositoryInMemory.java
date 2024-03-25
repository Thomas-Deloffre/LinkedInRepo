package fr.isika.cdi07.dao;

import java.util.List;
import java.util.Optional;

import fr.isika.cdi07.model.utilisateur.Utilisateur;

public class UtilisateurRepositoryInMemory implements UtilisateurRepository {

	@Override
	public <S extends Utilisateur> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Utilisateur> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Utilisateur> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Utilisateur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Utilisateur> findAllById(Iterable<Long> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Utilisateur entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Utilisateur> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Utilisateur> findByPseudo(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}
}
