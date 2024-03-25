package fr.isika.cdi07.model.projet;

import java.awt.Image;
import java.util.Date;

public class Article {
	private String idArticle;
	private String titre;
	private Image image;
	private String courteDescription;
	private String texteArticle;
	private Date date;
	private Long idProjet;
	private Long idPorteur;
	
	public Article() {
	}
	
	public Article(String idArticle, String titre, Image image, String courteDescription, String texteArticle, Date date) {
		this.idArticle = idArticle;
		this.titre = titre;
		this.image = image;
		this.courteDescription = courteDescription;
		this.texteArticle = texteArticle;
		this.date = date;
	}
	
	public String getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(String idArticle) {
		this.idArticle = idArticle;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public String getCourteDescription() {
		return courteDescription;
	}
	public void setCourteDescription(String courteDescription) {
		this.courteDescription = courteDescription;
	}
	public String getTexteArticle() {
		return texteArticle;
	}
	public void setTexteArticle(String texteArticle) {
		this.texteArticle = texteArticle;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getIdPorteur() {
		return idPorteur;
	}
	public void setIdPorteur(Long idPorteur) {
		this.idPorteur = idPorteur;
	}
	public Long getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(Long idProjet) {
		this.idProjet = idProjet;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArticle == null) ? 0 : idArticle.hashCode());
		result = prime * result + ((idPorteur == null) ? 0 : idPorteur.hashCode());
		result = prime * result + ((idProjet == null) ? 0 : idProjet.hashCode());
		result = prime * result + ((titre == null) ? 0 : titre.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (idArticle == null) {
			if (other.idArticle != null)
				return false;
		} else if (!idArticle.equals(other.idArticle))
			return false;
		if (idPorteur == null) {
			if (other.idPorteur != null)
				return false;
		} else if (!idPorteur.equals(other.idPorteur))
			return false;
		if (idProjet == null) {
			if (other.idProjet != null)
				return false;
		} else if (!idProjet.equals(other.idProjet))
			return false;
		if (titre == null) {
			if (other.titre != null)
				return false;
		} else if (!titre.equals(other.titre))
			return false;
		return true;
	}
}
