package com.cognixia.jump.exception;

public class LoginMenuException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LoginMenuException() {
	System.out.println("Record not found, Please check your username / password");
	}

	

}
