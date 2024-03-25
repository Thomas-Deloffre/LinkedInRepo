package fr.isika.cdi07.dao;

import org.springframework.data.repository.CrudRepository;
import fr.isika.cdi07.model.projet.Documents;

public interface DocumentRepository extends CrudRepository<Documents, Long>{
	Documents findByIdDocuments(Long idDocument);
}