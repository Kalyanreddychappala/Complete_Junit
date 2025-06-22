package com.durga.service;

import java.util.List;

import com.durga.dto.StudentDto;
import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;

public interface StudentService {
	
	public StudentDto create(StudentDto dto)throws DataAlreadyExisted;
	public StudentDto update(StudentDto dto,int sid)throws DataNotExisted;
	public StudentDto get(int sid)throws DataNotExisted;
	public List<StudentDto> getAll()throws DataNotExisted;
	public void delete(int sid)throws DataNotExisted;

}
