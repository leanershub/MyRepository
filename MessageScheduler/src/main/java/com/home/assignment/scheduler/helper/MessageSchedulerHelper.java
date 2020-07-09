/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.media.sound.InvalidDataException;

@Component
public class MessageSchedulerHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSchedulerHelper.class);
	/**
	 * Method to validate given time. The scheduled time should be future date
	 * @param scheduledDate
	 * @return
	 * @throws InvalidDataException
	 */
	public static boolean validateDate(long scheduledDate) throws InvalidDataException{
		if(scheduledDate < 1){
			return false;
		}
		Date givenDate = convertDate(scheduledDate);
		if(givenDate.compareTo(new Date()) < 0){
			return false;
		}
		return true;
		
	}
	/**
	 * Method to convert long to date instance
	 * @param scheduledDateInMillis
	 * @return
	 */
	public static Date convertDate(long scheduledDateInMillis){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date scheduledDate = new Date(scheduledDateInMillis);
		return scheduledDate;
	}
}
