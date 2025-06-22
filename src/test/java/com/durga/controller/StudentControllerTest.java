package com.durga.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.durga.dto.StudentDto;
import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;
import com.durga.service.StudentService;
@WebMvcTest(value=StudentController.class)
class StudentControllerTest {
		@Autowired
		private StudentController controller;
		@MockBean
		private StudentService service;
	@Test
	public void testCreate() throws DataAlreadyExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		when(service.create(dto)).thenReturn(dto);
		ResponseEntity<Object> actual=controller.create(dto);
		assertEquals(actual.getStatusCode(), HttpStatus.CREATED);
	}
	@Test
	public void testCreateFail() throws DataAlreadyExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		when(service.create(dto)).thenThrow(DataAlreadyExisted.class);
		assertThrows(DataAlreadyExisted.class, ()->controller.create(dto));
	}
	@Test
	public void testUpdate() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		when(service.update(dto, dto.getSid())).thenReturn(dto);
		ResponseEntity<Object> actual=controller.update(dto, dto.getSid());
		assertEquals(actual.getStatusCode(), HttpStatus.CREATED);
	}
	@Test
	public void testUpdateFail() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		when(service.update(dto, dto.getSid())).thenThrow(DataNotExisted.class);
		assertThrows(DataNotExisted.class, ()->controller.update(dto, dto.getSid()));
	}
	@Test
	public void testGet() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		when(service.get(dto.getSid())).thenReturn(dto);
		ResponseEntity<Object> actual=controller.get(dto.getSid());
		assertEquals(actual.getStatusCode(), HttpStatus.OK);
	}
	@Test
	public void testGetFail() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		when(service.get(dto.getSid())).thenThrow(DataNotExisted.class);
		assertThrows(DataNotExisted.class, ()->controller.get(dto.getSid()));
	}
	@Test
	public void testGetAll() throws DataNotExisted {
		List<StudentDto> list=Arrays.asList(new StudentDto(1,"pavan","MCA"));
		when(service.getAll()).thenReturn(list);
		ResponseEntity<Object> actual=controller.getAll();
		assertEquals(actual.getStatusCode(), HttpStatus.OK);
	}
	@Test
	public void testGetAllFail() throws DataNotExisted {
		when(service.getAll()).thenThrow(DataNotExisted.class);
		assertThrows(DataNotExisted.class, ()->controller.getAll());
	}
	@Test
	public void testDelete() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		doNothing().when(service).delete(dto.getSid());
		ResponseEntity<Object> actual=controller.delete(dto.getSid());
		assertEquals(actual.getStatusCode(),HttpStatus.NO_CONTENT);
	}
	@Test
	public void testDeleteFail() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		doThrow(DataNotExisted.class).when(service).delete(dto.getSid());
		assertThrows(DataNotExisted.class, ()->controller.delete(dto.getSid()));
	}

}
