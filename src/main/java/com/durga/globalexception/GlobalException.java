package com.durga.globalexception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;
import com.durga.login.exception.LoginUserNotFoundException;

@ControllerAdvice
public class GlobalException {
	@ExceptionHandler(exception= {DataAlreadyExisted.class,DataNotExisted.class})
	public ResponseEntity<Object> handleException(Exception e){
		Map<String, String> error=new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	@ExceptionHandler(exception = {LoginUserNotFoundException.class})
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public ResponseEntity<Object> loginException(Exception e){
		
		Map<String, String> error=new HashMap<>();
		error.put("message", e.getMessage());
		return ResponseEntity.badRequest().body(error);
		
	}
}
