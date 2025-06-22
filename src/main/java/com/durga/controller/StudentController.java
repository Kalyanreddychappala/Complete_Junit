package com.durga.controller;

import java.util.List;

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

import com.durga.dto.StudentDto;
import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;
import com.durga.service.StudentService;

@RestController
public class StudentController {
	@Autowired	
	private StudentService service;
	@PostMapping("/students")
	public ResponseEntity<Object> create(@RequestBody StudentDto dto) throws DataAlreadyExisted{
		StudentDto add=service.create(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(add);
	}
	@PutMapping("/update/{sid}")
	public ResponseEntity<Object> update(@RequestBody StudentDto dto,@PathVariable int sid) throws DataNotExisted{
		StudentDto update=service.update(dto, sid);
		return ResponseEntity.status(HttpStatus.CREATED).body(update);
	}
	@GetMapping("/get/{sid}")
	public ResponseEntity<Object> get(@PathVariable int sid) throws DataNotExisted{
		StudentDto get=service.get(sid);
		return ResponseEntity.status(HttpStatus.OK).body(get);
	}
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll() throws DataNotExisted{
		List<StudentDto> getAll=service.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(getAll);
	}
	@DeleteMapping("/delete/{sid}")
	public ResponseEntity<Object> delete(@PathVariable int sid) throws DataNotExisted{
		service.delete(sid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student "+sid+"deleted successfully");
	}
}
