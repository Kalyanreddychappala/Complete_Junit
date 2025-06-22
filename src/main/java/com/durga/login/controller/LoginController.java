package com.durga.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.durga.login.dto.LoginDto;
import com.durga.login.exception.LoginUserNotFoundException;
import com.durga.login.service.LoginService;
import com.durga.user.dto.UserDto;

@RestController
public class LoginController {
	@Autowired	
	private LoginService service;
	@PostMapping("/v1/login")	
	public ResponseEntity<Object> login(@RequestBody LoginDto dto) throws LoginUserNotFoundException{
		if(dto==null ||dto.getUserEmail()==null || dto.getUserEmail().length()<4||dto.getPassword()==null
				||dto.getPassword().length()<=0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid details");
		}
		UserDto user=service.loadUserByEmail(dto.getUserEmail());
		if(user!=null && user.getPassword().equals(dto.getPassword())) {
			return ResponseEntity.status(HttpStatus.OK).body("Login success");
		}
		
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login Fail");
		
		
	}
	
}
