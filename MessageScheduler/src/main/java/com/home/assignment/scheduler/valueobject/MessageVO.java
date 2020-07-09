/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler.valueobject;

import java.util.Date;
/**
 * Message VO class
 * @author admin
 *
 */
public class MessageVO {

	Date scheduledTime;
	
	String message;

	public Date getScheduledTime() {
		return scheduledTime;
	}

	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}

	@Override
	public String toString() {
		return "MessageVO [scheduledTime=" + scheduledTime + ", message=" + message + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
