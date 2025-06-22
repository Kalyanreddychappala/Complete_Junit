package com.durga.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;
import com.durga.user.dto.UserDto;
import com.durga.user.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	@PostMapping("/registration")
	public ResponseEntity<Object> insert(@RequestBody UserDto dto) throws DataAlreadyExisted{
		UserDto add=service.insert(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(add);
	}
	@PutMapping("/user/update/{userEmail}")
	public ResponseEntity<Object> update(@PathVariable String userEmail,@RequestBody UserDto dto) throws DataNotExisted{
		UserDto update=service.update(userEmail, dto);
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(update);	
	}
	@GetMapping("/user/get/{userEmail}")
	public ResponseEntity<Object> get(@PathVariable String userEmail) throws DataNotExisted{
		UserDto get=service.findByUserEmail(userEmail);
		return ResponseEntity.status(HttpStatus.OK).body(get);
	}
	@DeleteMapping("/user/delete/{userEmail}")
	
	public ResponseEntity<Object> delete(@PathVariable String userEmail) throws DataNotExisted{
		service.delete(userEmail);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}
