package fr.isika.cdi07.model.projet;

public enum ProjetGenres {
	VIDE ("SANS GENRE") ,
	ACTION ("ACTION"),
	ANIME ("ANIME"),
	COMEDIE ("COMEDIE"),
	DOCUMENTAIRE ("DOCUMENTAIRE"),
	DRAME ("DRAME"),
	FANTASTIQUE ("FANTASTIQUE"),
	HORREUR ("HORREUR"),
	JEUNESSE ("JEUNESSE"),
	COMEDIEMUSICALE ("COMEDIE MUSICALE"),
	POLICIER ("POLICIER"),
	ROMANCE ("ROMANCE"),
	SCIENCEFICTION ("SCIENCE FICTION"),
	THRILLER ("THRILLER"),
	CUISINE ("CUISINE"),
	VOYAGE ("VOYAGE"),
	SCIENCEETNATURE ("SCIENCE ET NATURE"),
	TELEREALITE ("TELE REALITE");
	
	private final String displayVal;
	private ProjetGenres(String displayVal) {
		this.displayVal = displayVal;
	}
	public String getDisplayVal() {
		return displayVal;
	}
}