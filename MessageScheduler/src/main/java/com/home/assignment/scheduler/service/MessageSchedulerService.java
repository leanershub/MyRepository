/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler.service;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.home.assignment.scheduler.config.SchedulerConfig;
import com.home.assignment.scheduler.helper.MessageSchedulerHelper;
import com.home.assignment.scheduler.job.MessageSchedulerJob;
import com.home.assignment.scheduler.valueobject.MessageVO;
import com.home.assignment.scheduler.valueobject.ResponseObject;

@Service
public class MessageSchedulerService {
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

	@Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @Autowired
    private ApplicationContext context;
    /**
     * Service method to schedule jobs
     * @param scheduledDate
     * @param message
     */
	public ResponseObject scheduleJob(long scheduledDate,String message){
		logger.info("Inside MessageSchedulerService scheduleJob");
		ResponseObject responseObject = new ResponseObject();
		String id = String.valueOf(scheduledDate);
		JobDetail jobDetails = SchedulerConfig.createJob(MessageSchedulerJob.class);
		jobDetails.getJobDataMap().put("message", getMessageObject(scheduledDate,message).toString());
		Trigger trigger = SchedulerConfig.createSimpleTrigger(id, MessageSchedulerHelper.convertDate(scheduledDate));
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		try {
			scheduler.scheduleJob(jobDetails,trigger);
			responseObject.setMessage("Success");
			responseObject.setResponseCode(HttpStatus.ACCEPTED.value());
		} catch (SchedulerException e) {
			logger.error(e.getMessage());
			responseObject.setMessage("Failed");
			responseObject.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		}
		return responseObject;
	}
	/**
	 * Method to create MessageVO
	 * @param scheduledDate
	 * @param message
	 * @return
	 */
	private MessageVO getMessageObject(long scheduledDate,String message){
		MessageVO messageObj = new MessageVO();
		messageObj.setScheduledTime(MessageSchedulerHelper.convertDate(scheduledDate));
		messageObj.setMessage(message);
		return messageObj;
	}
}
