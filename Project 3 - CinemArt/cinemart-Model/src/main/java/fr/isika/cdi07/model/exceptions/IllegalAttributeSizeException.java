package fr.isika.cdi07.model.exceptions;

public class IllegalAttributeSizeException extends Exception {
	private static final long serialVersionUID = 3231182412807405766L;
	public IllegalAttributeSizeException() {
		super();
	}
	public IllegalAttributeSizeException(String message, Throwable cause) {
		super(message, cause);
	}
	public IllegalAttributeSizeException(String message) {
		super(message);
	}
	public IllegalAttributeSizeException(Throwable cause) {
		super(cause);
	}
}