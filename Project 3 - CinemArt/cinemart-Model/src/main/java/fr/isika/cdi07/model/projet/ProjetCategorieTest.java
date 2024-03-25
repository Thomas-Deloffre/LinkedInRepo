package fr.isika.cdi07.model.projet;

public enum ProjetCategorieTest {

	
	COURTMETRAGE (" COURT METRAGE"),
    LONGMETRAGE (" LONG METRAGE"),
    SERIE (" SERIE");
	
	
	private final String displayValue;

	private ProjetCategorieTest(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	
	
	
}
