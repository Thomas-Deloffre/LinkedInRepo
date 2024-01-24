package fr.isika.cdi07.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.isika.cdi07.dao.MessageSupportRepository;
import fr.isika.cdi07.dao.SupportDiscussionRepository;
import fr.isika.cdi07.model.messagerie.MessageSupport;
import fr.isika.cdi07.model.messagerie.StatutDiscussion;
import fr.isika.cdi07.model.messagerie.SupportDiscussion;
import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Service
public class SupportDiscussionService {
	
	@Autowired
	private SupportDiscussionRepository supportDiscussionRepo;
	
	@Autowired
	private MessageSupportRepository messageSupportRepo;

	public void createDiscutionSupport(Utilisateur utilisateur, String intitule, String message) {
		SupportDiscussion supportDiscussion = new SupportDiscussion(utilisateur);
		supportDiscussionRepo.save(supportDiscussion);
		MessageSupport messageSupport = new MessageSupport(supportDiscussion, message, intitule);
		messageSupportRepo.save(messageSupport);
		List<MessageSupport> messagesSupport = supportDiscussion.getMessagesSupport();
		messagesSupport.add(messageSupport);
		supportDiscussionRepo.save(supportDiscussion);
	}

	public List<SupportDiscussion> getAllDiscutionNotClosed() {
		List<SupportDiscussion> statutDiscution = supportDiscussionRepo.findByStatutDiscussion(StatutDiscussion.OPEN);
		return statutDiscution;
	}

	public void addReponseDiscution(Long idSupportDiscussion, String reponse) {
		Optional<SupportDiscussion> opSupportDiscussion = supportDiscussionRepo.findById(idSupportDiscussion);
		SupportDiscussion supportDiscussion = opSupportDiscussion.get();
		List<MessageSupport> messageSupport = supportDiscussion.getMessagesSupport();
		MessageSupport message= new MessageSupport();
		message.setIntitule("Réponse Admin");
		message.setMessage(reponse);
		messageSupport.add(message);
		supportDiscussionRepo.save(supportDiscussion);
		messageSupportRepo.save(message);
	}
}
