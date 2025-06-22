package com.durga.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durga.login.exception.LoginUserNotFoundException;
import com.durga.user.dto.UserDto;
import com.durga.user.repository.UserRepository;
import com.durga.user.util.UserUtil;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepository repo;

	@Override
	public UserDto loadUserByEmail(String userEmail) throws LoginUserNotFoundException {
		if(repo.findByUserEmail(userEmail).isEmpty()) {
			throw new LoginUserNotFoundException("User id not Existed in the given ID: "+userEmail);
		}
		return UserUtil.convertToDto(repo.findByUserEmail(userEmail).get());
	}

}
