package fr.isika.cdi07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.isika.cdi07.model.don.Don;

@Repository
public interface DonRepository extends CrudRepository<Don, Long>{
	List<Don> findByUtilisateurIdUtilisateur(Long idUtilisateur);
}
