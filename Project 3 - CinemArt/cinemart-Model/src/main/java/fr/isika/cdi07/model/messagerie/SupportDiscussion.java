package fr.isika.cdi07.model.messagerie;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import fr.isika.cdi07.model.utilisateur.Utilisateur;

@Entity
public class SupportDiscussion {

	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idSupportDiscussion;
	
	@OneToOne
	private Utilisateur utilisateur;
	
	private StatutDiscussion statutDiscussion;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<MessageSupport> messagesSupport;
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public SupportDiscussion() {
		this.statutDiscussion = StatutDiscussion.OPEN;
	}
	
	public SupportDiscussion(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
		this.messagesSupport = new ArrayList<MessageSupport>();
		this.statutDiscussion = StatutDiscussion.OPEN;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	public List<MessageSupport> getMessagesSupport() {
		return messagesSupport;
	}
	public void setMessagesSupport(List<MessageSupport> messagesSupport) {
		this.messagesSupport = messagesSupport;
	}

	public Long getIdSupportDiscussion() {
		return idSupportDiscussion;
	}

	public void setIdSupportDiscussion(Long idSupportDiscussion) {
		this.idSupportDiscussion = idSupportDiscussion;
	}

	public StatutDiscussion getStatutDiscussion() {
		return statutDiscussion;
	}

	public void setStatutDiscussion(StatutDiscussion statutDiscussion) {
		this.statutDiscussion = statutDiscussion;
	}
}
