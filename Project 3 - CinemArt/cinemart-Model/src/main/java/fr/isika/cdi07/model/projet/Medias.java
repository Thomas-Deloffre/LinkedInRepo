package fr.isika.cdi07.model.projet;

import java.sql.Blob;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Medias {
	@Id
	@NotNull
	private long idMedia;
	@NotNull
	@Enumerated(EnumType.STRING)
	private TypeMedia typeMedia;
	
	@NotNull
	private Blob media;
	
	@ManyToOne
	private Projet projet;
}