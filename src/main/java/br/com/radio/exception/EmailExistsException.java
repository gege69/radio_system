package br.com.radio.exception;

public class EmailExistsException extends RuntimeException {

	private static final long serialVersionUID = -5529388324591384506L;

	public EmailExistsException(String message) {
        super(message);
    }
	
}
