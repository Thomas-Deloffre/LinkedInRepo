package fr.isika.cdi07.model.projet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Documents {
	@Id
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idDocuments;
	@NotNull
	private String titre;
	@ManyToOne
	private Projet projet;
	@Lob
	@NotNull
	private byte[] data;
	
	public Documents() {
		
	}
	
	public Documents(String titre, Projet projet,
			byte[] data) {
		super();
		
		this.titre = titre;
		this.projet = projet;	
		this.data = data;
	}
	
	public void setIdDocument(Long idDocument) {
		this.idDocuments = idDocument;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Projet getProjet() {
		return projet;
	}
	public void setProjet(Projet projet) {
		this.projet = projet;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public Long getIdDocuments() {
		return idDocuments;
	}
}