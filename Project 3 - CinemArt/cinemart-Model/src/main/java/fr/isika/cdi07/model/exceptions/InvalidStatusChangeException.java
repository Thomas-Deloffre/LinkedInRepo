package fr.isika.cdi07.model.exceptions;

public class InvalidStatusChangeException extends Exception {	
	private static final long serialVersionUID = 3231189278807405766L;
	public InvalidStatusChangeException() {
		super();
	}
	public InvalidStatusChangeException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidStatusChangeException(String message) {
		super(message);
	}
	public InvalidStatusChangeException(Throwable cause) {
		super(cause);
	}
}