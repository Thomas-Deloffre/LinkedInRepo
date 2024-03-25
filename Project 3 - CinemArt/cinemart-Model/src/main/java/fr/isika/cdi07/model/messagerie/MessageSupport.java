package fr.isika.cdi07.model.messagerie;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class MessageSupport {
	
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idMessageSupport;
	
	@ManyToOne
	private SupportDiscussion supportDiscussion;
	
	private String message;
	
	private String intitule;

	public MessageSupport() {
	}
	public MessageSupport(SupportDiscussion supportDiscussion, String message, String intitule) {
		super();
		this.supportDiscussion = supportDiscussion;
		this.message = message;
		this.intitule = intitule;
	}
	public Long getIdMessageSupport() {
		return idMessageSupport;
	}
	public void setIdMessageSupport(Long idMessageSupport) {
		this.idMessageSupport = idMessageSupport;
	}
	public SupportDiscussion getSupportDiscussion() {
		return supportDiscussion;
	}
	public void setSupportDiscussion(SupportDiscussion supportDiscussion) {
		this.supportDiscussion = supportDiscussion;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
}
