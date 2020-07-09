/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
/**
 * Class to handle Jobs
 * @author admin
 *
 */
public class MessageSchedulerJob extends QuartzJobBean{
	
	private static final Logger logger = LoggerFactory.getLogger(MessageSchedulerJob.class);
	/**
	 * Method to execute scheduler
	 */
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		String message =  (String) context.getJobDetail().getJobDataMap().get("message");
		// print the message when the scheduler runs
		logger.info(message);
	}

}
