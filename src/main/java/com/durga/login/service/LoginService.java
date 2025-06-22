package com.durga.login.service;

import com.durga.login.exception.LoginUserNotFoundException;
import com.durga.user.dto.UserDto;

public interface LoginService {
	
	public UserDto loadUserByEmail(String userEmail) throws LoginUserNotFoundException;

}
