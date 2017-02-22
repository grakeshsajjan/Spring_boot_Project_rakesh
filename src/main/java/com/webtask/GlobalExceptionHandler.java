package com.webtask;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BaseException.class)
	public Map<String, Object> beHandler(BaseException e)
	{
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", e.getMessage());
		return response;
		//return e.getMessage();
	}

}
