package by.moiski.services.exceptions;

public class ErrorSavingUserServiceExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorSavingUserServiceExeption() {
		super();
	}

	public ErrorSavingUserServiceExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ErrorSavingUserServiceExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorSavingUserServiceExeption(String message) {
		super(message);
	}

	public ErrorSavingUserServiceExeption(Throwable cause) {
		super(cause);
	}
	
}
