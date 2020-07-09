/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler.exception;

import java.util.Date;
/**
 * Application Error response structure
 * @author admin
 *
 */
public class ExceptionResponse {

	private Date timestamp;
	private String message;
	private String details;
	
	public ExceptionResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	
}
