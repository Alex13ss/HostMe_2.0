package com.softserve.edu.entity.exceptions;

public class DuplicateLoginException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicateLoginException() {
		super();
	}

	public DuplicateLoginException(String message) {

		super("User with such login already exists!");
	}
}
