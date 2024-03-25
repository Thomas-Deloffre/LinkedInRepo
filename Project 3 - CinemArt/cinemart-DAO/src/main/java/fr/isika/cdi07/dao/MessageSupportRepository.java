package fr.isika.cdi07.dao;

import org.springframework.data.repository.CrudRepository;
import fr.isika.cdi07.model.messagerie.MessageSupport;

public interface MessageSupportRepository extends CrudRepository<MessageSupport, Long>{

}
