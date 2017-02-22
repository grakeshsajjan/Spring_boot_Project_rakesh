package com.webtask;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TaskController {
	
	@Autowired
	private TaskDAO tasks;
	
	@Autowired
	private Task taskobj;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@RequestMapping(value = "/exp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> testException() throws BaseException
	{
		throw new BaseException("Base exception thrown");
	}
	
	
	
	@RequestMapping(value = "/task", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Task> getAll()
	{
		
		logger.debug("this is debug message");
		logger.info("this is info message");
		logger.warn("this is warn message");
		logger.error("this is error message");
		
		
		return tasks.getAll();
	}
	
	@RequestMapping(value = "/task", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> createTask(@RequestBody Map<String, Object> payload)
	{
		
		//ObjectMapper mapper = new ObjectMapper();
		
		int count = 0;
		
			
			Task taskobj1 = new Task(Integer.parseInt(payload.get("taskid").toString()), payload.get("startdate").toString(),payload.get("duedate").toString(), Integer.parseInt(payload.get("priority").toString()), payload.get("remainder").toString());
			
			
			System.out.println("start date:" + taskobj1.getStartdate());
			

		
		count = tasks.createTask(taskobj1);
		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		
		if(count>0)
		{
			response.put("message", "Task created succcessfully");
			response.put("Task", taskobj1);
		}
		else
		{
			response.put("message", "Task creation failed");
		}
		
		return response;
		
	}
	
	@RequestMapping(value = "/task/{taskId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> deleteTask(@PathVariable("taskId") int taskId)
	{
		int count = 0;
		
		count = tasks.deleteTask(taskId);
		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		
		if(count>0)
			
			response.put("message", "Task deletion completed");
		else
			
			response.put("message", "Task deletion failed");
		
		return response;
		
	}
	
	@RequestMapping(value = "/task", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, Object> updateTask(@RequestBody Map<String, Object> payload)
	{
		
		int count = 0;
		
		
		Task taskobj1 = new Task(Integer.parseInt(payload.get("taskid").toString()), payload.get("startdate").toString(),payload.get("duedate").toString(), Integer.parseInt(payload.get("priority").toString()), payload.get("remainder").toString());
		
		
		System.out.println("start date:" + taskobj1.getStartdate());
		

	
	count = tasks.updateTask(taskobj1);
	
	Map<String, Object> response = new LinkedHashMap<String, Object>();
	
	if(count>0)
	{
		response.put("message", "The shared task updated succcessfully");
		response.put("Task", taskobj1);
	}
	else
	{
		response.put("message", "Task updation failed");
	}
	
	return response;
		
	}
	
	//@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Base exception occured", value = HttpStatus.BAD_REQUEST)
	/*@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BaseException.class)
	public Map<String, Object> beHandler(BaseException e)
	{
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", e.getMessage());
		return response;
		//return e.getMessage();
	}*/

}
