package fr.isika.cdi07.services;

import java.util.List;
import fr.isika.cdi07.model.projet.Documents;
public interface DocumentServiceInterface {
	List<Documents> getAllDocuments();
	Documents getDocument(Long idDocument);
	
}