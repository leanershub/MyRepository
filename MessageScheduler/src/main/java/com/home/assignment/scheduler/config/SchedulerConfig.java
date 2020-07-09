/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler.config;

import java.util.Date;
import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
/**
 * Class to configure Quartz Scheduler Properties
 * @author admin
 *
 */
@Configuration
public class SchedulerConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(SchedulerConfig.class);

	@Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private QuartzProperties quartzProperties;
    /**
     * For creating SchedulerFactoryBean
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {

        SchedulerJobFactory jobFactory = new SchedulerJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(jobFactory);
        return factory;
    }
    /**
     * To create JobDetail
     * @param jobClass
     * @return
     */
    public static JobDetail createJob(Class<? extends QuartzJobBean> jobClass) {
    	JobDetail jobDetail  = JobBuilder.newJob(jobClass).build();
    	return jobDetail;
    }
    /**
     * To create a Trigger
     * @param triggerName
     * @param startTime
     * @return
     */
    public static Trigger createSimpleTrigger(String triggerName, Date startTime) {
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName).startAt(startTime).build();
        return trigger;
    }
}
