package fr.isika.cdi07.model.exceptions;

public class InvalidUpdateException extends Exception {
	private static final long serialVersionUID = 3231182162759305766L;
	public InvalidUpdateException() {
		super();
	}
	public InvalidUpdateException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidUpdateException(String message) {
		super(message);
	}
	public InvalidUpdateException(Throwable cause) {
		super(cause);
	}
}
