package application.model;

public class InvalidDateException extends Exception {

	private static final long serialVersionUID = -82247106617776746L;

	public InvalidDateException(String message) {
		super(message);
	}
}
