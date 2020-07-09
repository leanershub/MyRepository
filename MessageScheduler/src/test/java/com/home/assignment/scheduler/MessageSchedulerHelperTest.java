/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler;

import static org.junit.Assert.assertEquals;

import java.time.Instant;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.home.assignment.scheduler.helper.MessageSchedulerHelper;
import com.sun.media.sound.InvalidDataException;

/**
 * Test class for MessageSchedulerHelper
 * @author admin
 *
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageSchedulerHelperTest {

	@Autowired
	MessageSchedulerHelper messageSchedulerHelper;
	
	/**
	 * Test to validate date
	 * @throws InvalidDataException
	 */
	@Test
	public void testValidateDate() throws InvalidDataException{
		long scheduledDate = 1609482600000L;
		boolean actual = messageSchedulerHelper.validateDate(scheduledDate);
		assertEquals(true, actual);
	}
	
	/**
	 * Test to validate Date conversion
	 */
	@Test
	public void testConvertDate(){
		Date expected = Date.from(Instant.ofEpochMilli(1577860200000L));
		Date actual = messageSchedulerHelper.convertDate(1577860200000L);
		assertEquals(expected, actual);
	}
}
