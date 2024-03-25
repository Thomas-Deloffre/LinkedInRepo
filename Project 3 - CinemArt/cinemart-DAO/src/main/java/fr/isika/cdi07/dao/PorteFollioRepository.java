package fr.isika.cdi07.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import fr.isika.cdi07.model.utilisateur.PortFolio;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Repository
public interface PorteFollioRepository extends CrudRepository<PortFolio, Long> {
	List<PortFolio> findByIdPortFolio(Long idProjet);
	List<PortFolio> findByUtilisateur(Utilisateur utilisateur);
}
