/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler.valueobject;
/**
 * Response Object
 * @author admin
 *
 */
public class ResponseObject {

	private String message;
	
	private int responseCode;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
	
	
}
