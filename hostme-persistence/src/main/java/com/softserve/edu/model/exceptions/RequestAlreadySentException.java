package com.softserve.edu.model.exceptions;

public class RequestAlreadySentException extends Exception {
	private static final long serialVersionUID = 1L;

	public RequestAlreadySentException() {
		super();
	}

	public RequestAlreadySentException(String message) {

		super("You have already sent this request!");
	}

}
