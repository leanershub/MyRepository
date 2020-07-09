/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
 
package com.home.assignment.scheduler.controller;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.home.assignment.scheduler.exception.InvalidDateException;
import com.home.assignment.scheduler.helper.MessageSchedulerHelper;
import com.home.assignment.scheduler.service.MessageSchedulerService;
import com.home.assignment.scheduler.valueobject.ResponseObject;
import com.sun.media.sound.InvalidDataException;

/**
 * Controller to schedule messages
 * @author admin
 *
 */
@RestController
public class MessageScheduleController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageScheduleController.class);

	@Autowired
	MessageSchedulerService messageSchedulerService;

	/**
	 * End point to schedule message. 
	 * This method receives a message 
	 * and specified time at which the message should be scheduled. Will add the message to scheduler.
	 * Scheduler will send the message at the specified time
	 * @param scheduledDate
	 * @param message
	 * @return
	 * @throws SchedulerException
	 * @throws InvalidDataException
	 */
	@PostMapping(path="/schedule/message")
	public ResponseEntity<Object> scheduleMessage(@RequestParam long scheduledDate,@RequestParam String message) throws SchedulerException, InvalidDataException{
		logger.info("Inside scheduleMessage Date = {},Message = {}",scheduledDate,message);
		// validate the scheduled time
		if(!MessageSchedulerHelper.validateDate(scheduledDate)){
			throw new InvalidDateException("Invalid Date");
		}
		ResponseObject responseObject = messageSchedulerService.scheduleJob(scheduledDate, message);
		return new ResponseEntity(responseObject,HttpStatus.ACCEPTED);
	}
}
