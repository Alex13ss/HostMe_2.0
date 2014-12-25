package com.softserve.edu.entity.exceptions;

public class RequestCannotSendException extends Exception {
	private static final long serialVersionUID = 1L;

	public RequestCannotSendException() {
		super();
	}

	public RequestCannotSendException(String message) {

		super("Request cannot be sent!");
	}

}
