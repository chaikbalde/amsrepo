package com.hub4u.ams.frwk;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
	}

	public UserNotFoundException(String message) {
		super("Unnable to find User : " + message);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super("Unnable to find User" + message, cause);
	}

}
