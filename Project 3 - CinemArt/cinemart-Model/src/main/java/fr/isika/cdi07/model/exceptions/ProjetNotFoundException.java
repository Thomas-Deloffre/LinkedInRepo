package fr.isika.cdi07.model.exceptions;

public class ProjetNotFoundException extends Exception {
	private static final long serialVersionUID = 3231182162807405766L;
	public ProjetNotFoundException() {
		super();
	}
	public ProjetNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	public ProjetNotFoundException(String message) {
		super(message);
	}
	public ProjetNotFoundException(Throwable cause) {
		super(cause);
	}
}
