package by.moiski.services.exceptions;

public class LoginAlreadyBusyServiceExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public LoginAlreadyBusyServiceExeption() {
		super();
	}

	public LoginAlreadyBusyServiceExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LoginAlreadyBusyServiceExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginAlreadyBusyServiceExeption(String message) {
		super(message);
	}

	public LoginAlreadyBusyServiceExeption(Throwable cause) {
		super(cause);
	}
	
	

}
