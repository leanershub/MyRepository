/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler.exception;
/**
 * Custom Exception Class
 * @author admin
 *
 */
public class InvalidDateException extends RuntimeException{

	public InvalidDateException(String message){
		super(message);
	}
}
