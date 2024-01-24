package fr.isika.cdi07.model.exceptions;

public class InvalidProjectPublicationException extends Exception {
	private static final long serialVersionUID = 3231189278807405766L;
	public InvalidProjectPublicationException() {
		super();
	}
	public InvalidProjectPublicationException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidProjectPublicationException(String message) {
		super(message);
	}
	public InvalidProjectPublicationException(Throwable cause) {
		super(cause);
	}
}
