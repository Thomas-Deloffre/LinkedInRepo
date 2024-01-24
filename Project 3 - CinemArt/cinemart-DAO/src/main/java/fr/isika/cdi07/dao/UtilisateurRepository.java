package fr.isika.cdi07.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, Long>{
	List<Utilisateur>findByPseudo(String pseudo);
}