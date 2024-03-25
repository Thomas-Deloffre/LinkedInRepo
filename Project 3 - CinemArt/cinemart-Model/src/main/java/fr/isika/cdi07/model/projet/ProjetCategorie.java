package fr.isika.cdi07.model.projet;

public enum ProjetCategorie {
	VIDE ("SANS CATEGORIE"),
	COURTMETRAGE (" COURT METRAGE"),
    LONGMETRAGE (" LONG METRAGE"),
    SERIE (" SERIE");
	
	private final String displayValue;
	private ProjetCategorie(String displayValue) {
		this.displayValue = displayValue;
	}
	public String getDisplayValue() {
		return displayValue;
	}
}