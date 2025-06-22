package com.durga.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durga.beans.Student;
import com.durga.dto.StudentDto;
import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;
import com.durga.repository.StudentRepository;
import com.durga.util.StudentUtil;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository repo;
	public StudentDto create(StudentDto dto)throws DataAlreadyExisted{
		
		if(repo.findById(dto.getSid()).isPresent()) {
			throw new DataAlreadyExisted("Student Already Existed: "+dto.getSid());
		}
		
		return StudentUtil.convertToDto(repo.save(StudentUtil.converToEntity(dto)));
		
	}

	@Override
	public StudentDto update(StudentDto dto, int sid) throws DataNotExisted {
		if(repo.findById(sid).isEmpty()) {
			throw new DataNotExisted("Student not existed in the given Id: "+sid);
		}
		
		return StudentUtil.convertToDto(repo.save(StudentUtil.converToEntity(dto)));
	}

	@Override
	public StudentDto get(int sid) throws DataNotExisted {
		if(repo.findById(sid).isEmpty()) {
			throw new DataNotExisted("Student not existed in the given Id: "+sid);
		}
		return StudentUtil.convertToDto(repo.findById(sid).get());
	}

	@Override
	public List<StudentDto> getAll() throws DataNotExisted {
		if(repo.findAll().isEmpty()) {
			throw new DataNotExisted("No Students are Existed");
		}
		List<Student> list=repo.findAll();
		return list.stream().map(StudentUtil::convertToDto)
				.collect(Collectors.toList());
	}

	@Override
	public void delete(int sid) throws DataNotExisted {
		if(repo.findById(sid).isEmpty()) {
			throw new DataNotExisted("Student not existed in the given Id: "+sid);
		}
		repo.deleteById(sid);
	}
	
	
	
}
