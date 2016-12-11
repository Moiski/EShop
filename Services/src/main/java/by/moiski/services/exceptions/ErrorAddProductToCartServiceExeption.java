package by.moiski.services.exceptions;

public class ErrorAddProductToCartServiceExeption extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorAddProductToCartServiceExeption() {
		super();
	}

	public ErrorAddProductToCartServiceExeption(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ErrorAddProductToCartServiceExeption(String message, Throwable cause) {
		super(message, cause);
	}

	public ErrorAddProductToCartServiceExeption(String message) {
		super(message);
	}

	public ErrorAddProductToCartServiceExeption(Throwable cause) {
		super(cause);
	}
	
}
