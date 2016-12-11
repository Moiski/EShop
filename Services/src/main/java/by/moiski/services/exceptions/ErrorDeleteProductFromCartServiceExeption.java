package by.moiski.services.exceptions;

public class ErrorDeleteProductFromCartServiceExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorDeleteProductFromCartServiceExeption() {
		super();
	}

	public ErrorDeleteProductFromCartServiceExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ErrorDeleteProductFromCartServiceExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorDeleteProductFromCartServiceExeption(String message) {
		super(message);
	}

	public ErrorDeleteProductFromCartServiceExeption(Throwable cause) {
		super(cause);
	}
	
}
