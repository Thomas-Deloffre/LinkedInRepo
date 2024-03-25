package fr.isika.cdi07.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import fr.isika.cdi07.model.messagerie.StatutDiscussion;
import fr.isika.cdi07.model.messagerie.SupportDiscussion;

public interface SupportDiscussionRepository extends CrudRepository<SupportDiscussion, Long>  {
	List<SupportDiscussion> findByStatutDiscussion(StatutDiscussion open);
}
