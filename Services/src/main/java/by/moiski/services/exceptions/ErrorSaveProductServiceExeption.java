package by.moiski.services.exceptions;

public class ErrorSaveProductServiceExeption extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ErrorSaveProductServiceExeption() {
		super();
	}

	public ErrorSaveProductServiceExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ErrorSaveProductServiceExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorSaveProductServiceExeption(String message) {
		super(message);
	}

	public ErrorSaveProductServiceExeption(Throwable cause) {
		super(cause);
	}
	
}
