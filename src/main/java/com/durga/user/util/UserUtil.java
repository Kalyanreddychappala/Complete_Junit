package com.durga.user.util;

import org.springframework.beans.BeanUtils;

import com.durga.user.beans.User;
import com.durga.user.dto.UserDto;

public class UserUtil {
	
	public static User convertToEntity(UserDto dto) {
		User user=new User();
		BeanUtils.copyProperties(dto, user);
		return user;
	}
	
	public static UserDto convertToDto(User user) {
		UserDto dto=new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}

}
