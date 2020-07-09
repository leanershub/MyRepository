/*******************************************************************************
 * Confidential
 * Organization
 * Copyright
 *******************************************************************************/
package com.home.assignment.scheduler;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.assignment.scheduler.controller.MessageScheduleController;
import com.home.assignment.scheduler.service.MessageSchedulerService;
import com.home.assignment.scheduler.valueobject.ResponseObject;
/**
 * Test class for the controller
 * @author admin
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = MessageScheduleController.class,secure = false)
public class MessageSchedulerControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MessageSchedulerService messageSchedulerService;
	
	/**
	 * To test the end point
	 * @throws Exception
	 */
	@Test
	public void testScheduleMessage() throws Exception{
		String URI = "/schedule/message?scheduledDate=1625841660000&message=Awsome work2 43";
		ResponseObject responseObject = new ResponseObject();
		responseObject.setMessage("Success");
		responseObject.setResponseCode(202);
		String expectedJson = this.mapToJson(responseObject);
		Mockito.when(messageSchedulerService.scheduleJob(Mockito.anyLong(), Mockito.anyString())).thenReturn(responseObject);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputJson = response.getContentAsString();
		assertThat(outputJson).isEqualTo(expectedJson);
	}
	private String mapToJson(Object object) throws JsonProcessingException{
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}
