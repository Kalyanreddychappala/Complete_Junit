package com.durga.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.durga.beans.Student;
import com.durga.dto.StudentDto;
import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;
import com.durga.repository.StudentRepository;
import com.durga.util.StudentUtil;
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
	@InjectMocks
	private StudentServiceImpl service;
	@Mock	
	private StudentRepository repo;
	@Test
	public void testCreate() throws DataAlreadyExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		Student entity=StudentUtil.converToEntity(dto);
		Optional<Student> expected=Optional.ofNullable(null);
		when(repo.findById(dto.getSid())).thenReturn(expected);
		when(repo.save(entity)).thenReturn(entity);
		StudentDto actual=service.create(dto);
		assertEquals(dto, actual);
	}
	@Test
	public void testCreateFail() {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		Student entity=StudentUtil.converToEntity(dto);
		Optional<Student> expected=Optional.ofNullable(entity);
		when(repo.findById(dto.getSid())).thenReturn(expected);
		assertThrows(DataAlreadyExisted.class, ()->service.create(dto));	
	}
	@Test
	public void testUpdate() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		Student entity=StudentUtil.converToEntity(dto);
		Optional<Student> expected=Optional.ofNullable(entity);
		when(repo.findById(dto.getSid())).thenReturn(expected);
		when(repo.save(entity)).thenReturn(entity);
		StudentDto actual=service.update(dto, dto.getSid());
		assertEquals(dto, actual);
	}
	@Test
	public void testUpdateFail() {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		Optional<Student> expected=Optional.ofNullable(null);
		when(repo.findById(dto.getSid())).thenReturn(expected);
		assertThrows(DataNotExisted.class, ()->service.update(dto, dto.getSid()));
		
	}
	@Test
	public void testGet() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		Student entity=StudentUtil.converToEntity(dto);
		Optional<Student> expected=Optional.ofNullable(entity);
		when(repo.findById(entity.getSid())).thenReturn(expected);
		StudentDto actual=service.get(dto.getSid());
		assertEquals(dto, actual);
	}
	@Test
	public void testGetFail() {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		Student entity=StudentUtil.converToEntity(dto);
		Optional<Student> expected=Optional.ofNullable(null);
		when(repo.findById(dto.getSid())).thenReturn(expected);
		assertThrows(DataNotExisted.class, ()->service.get(dto.getSid()));
	}
	@Test
	public void testGetAll() throws DataNotExisted {
		List<Student> mockito=List.of(new Student(1,"pavan","MCA"));
		when(repo.findAll()).thenReturn(mockito);
		List<StudentDto> expected=mockito.stream()
				.map(StudentUtil::convertToDto)
				.collect(Collectors.toList());
		List<StudentDto> actual=service.getAll();
		assertEquals(expected, actual);
	}
	@Test
	public void testGetAllFail() {
		when(repo.findAll()).thenReturn(List.of());
		assertThrows(DataNotExisted.class, ()->service.getAll());
	}
	@Test
	public void testDelete() throws DataNotExisted {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		Student entity=StudentUtil.converToEntity(dto);
		Optional<Student> expected=Optional.ofNullable(entity);
		when(repo.findById(entity.getSid())).thenReturn(expected);
		service.delete(dto.getSid());
		verify(repo).deleteById(dto.getSid());
	}
	@Test
	public void testDeleteFail() {
		StudentDto dto=new StudentDto(1,"pavan","MCA");
		Optional<Student> expected=Optional.ofNullable(null);
		when(repo.findById(dto.getSid())).thenReturn(expected);
		assertThrows(DataNotExisted.class, ()->service.delete(dto.getSid()));
	}
	
}
