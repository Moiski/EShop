package by.moiski.services.exceptions;

public class ErrorDataAccessServiceExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorDataAccessServiceExeption() {
		super();
	}

	public ErrorDataAccessServiceExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ErrorDataAccessServiceExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorDataAccessServiceExeption(String message) {
		super(message);
	}

	public ErrorDataAccessServiceExeption(Throwable cause) {
		super(cause);
	}
	
}
