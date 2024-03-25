package fr.isika.cdi07.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import fr.isika.cdi07.dao.DocumentRepository;
import fr.isika.cdi07.model.projet.Documents;
import fr.isika.cdi07.model.projet.Projet;

@EntityScan(basePackages="fr.isika.cdi07.model.document")
@EnableJpaRepositories("fr.isika.cdi07.dao")
@Service
public class DocumentService implements DocumentServiceInterface{
	@Autowired
	public DocumentRepository documentRepository;
	public DocumentService(DocumentRepository documentRepository) {
		super();
		this.setDocumentRepository(documentRepository);
	}
	@Override
	public List<Documents> getAllDocuments() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Documents getDocument(Long idDocument) {
		// TODO Auto-generated method stub
		return null;
	}
	public Documents createDocument (String titre, Projet projet, byte[] data) {
		Documents document = new Documents(	
				titre,
				projet,
				data);	
		getDocumentRepository().save(document);
		return document;
	}
	public void deleteDocument (Long idDocument) {

		getDocumentRepository().deleteById(idDocument);
	}
	public DocumentRepository getDocumentRepository() {
		return documentRepository;
	}
	public void setDocumentRepository(DocumentRepository documentRepository) {
		this.documentRepository = documentRepository;
	}
}