package br.com.adopet.apiadopet.exception;

public class DomainAdoPetException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DomainAdoPetException(String msg) {
		super(msg);
	}
}
