package by.moiski.services.exceptions;

public class ErrorCartUserIsEmptyServiceExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorCartUserIsEmptyServiceExeption() {
		super();
	}

	public ErrorCartUserIsEmptyServiceExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ErrorCartUserIsEmptyServiceExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorCartUserIsEmptyServiceExeption(String message) {
		super(message);
	}

	public ErrorCartUserIsEmptyServiceExeption(Throwable cause) {
		super(cause);
	}

}
