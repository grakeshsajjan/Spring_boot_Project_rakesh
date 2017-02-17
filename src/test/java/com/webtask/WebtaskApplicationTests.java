package com.webtask;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebtaskApplication.class)
@WebAppConfiguration
public class WebtaskApplicationTests {
	
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	
	@Autowired
	private TaskDAO taskDAO;
	
	private RestTemplate resttemplate = new TestRestTemplate();

	@Test
	public void testCreateTask() throws JsonProcessingException{
		
		Map<String, Object> requestbody = new HashMap<String, Object>();
		
		requestbody.put("taskid", 4);
		requestbody.put("startdate", "17/02/2017");
		requestbody.put("duedate", "18/02/2017");
		requestbody.put("priority", 1);
		requestbody.put("remainder", "Pending");
		
		HttpHeaders requestHeaders = new HttpHeaders();
		
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> httpEntity = new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestbody),requestHeaders);
		
		Map<String, Object> apiResponse = resttemplate.postForObject("http://localhost:8870/task", httpEntity, Map.class, Collections.EMPTY_MAP);
		
		Assert.assertNotNull(apiResponse);
		
		String message = apiResponse.get("message").toString();
		
		Assert.assertEquals("Task created succcessfully",message);
		
		int taskId = Integer.parseInt(((Map<String, Object>)apiResponse.get("Task")).get("taskid").toString());
		
		Assert.assertNotNull(taskId);
		
		Task taskfromDB = taskDAO.getTask(taskId);
		
		Assert.assertTrue(4 == taskfromDB.getTaskid());
		Assert.assertEquals("17/02/2017", taskfromDB.getStartdate());
		Assert.assertEquals("18/02/2017", taskfromDB.getDuedate());
		Assert.assertTrue(1 == taskfromDB.getPriority());
		Assert.assertEquals("Pending", taskfromDB.getRemainder());
		
	}

}
