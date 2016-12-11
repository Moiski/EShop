package by.moiski.services.exceptions;

public class ServiceExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public ServiceExeption() {
		super();
	}

	public ServiceExeption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceExeption(String message) {
		super(message);
	}

	public ServiceExeption(Throwable cause) {
		super(cause);
	}
	
}
