package fr.isika.cdi07.dao;

import org.springframework.data.repository.CrudRepository;
import fr.isika.cdi07.model.utilisateur.Profil;

public interface ProfilRepository extends CrudRepository<Profil, Long> {

}
