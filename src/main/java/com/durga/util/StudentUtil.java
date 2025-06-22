package com.durga.util;

import org.springframework.beans.BeanUtils;

import com.durga.beans.Student;
import com.durga.dto.StudentDto;

public class StudentUtil {

	public static Student converToEntity(StudentDto dto) {

		Student std = new Student();
		BeanUtils.copyProperties(dto, std);
		return std;
	}

	public static StudentDto convertToDto(Student std) {
		StudentDto dto = new StudentDto();
		BeanUtils.copyProperties(std, dto);
		return dto;
	}

}
