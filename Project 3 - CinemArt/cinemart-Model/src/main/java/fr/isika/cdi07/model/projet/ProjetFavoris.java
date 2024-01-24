package fr.isika.cdi07.model.projet;

public class ProjetFavoris {

	private String idProjet;
	private String idUtilisateur;
	private String titre;
	public ProjetFavoris(String idProjet, String idUtilisateur, String titre) {
		this.idProjet = idProjet;
		this.idUtilisateur = idUtilisateur;
		this.titre = titre;
	}
	public String getIdProjet() {
		return idProjet;
	}
	public void setIdProjet(String idProjet) {
		this.idProjet = idProjet;
	}
	public String getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
}
