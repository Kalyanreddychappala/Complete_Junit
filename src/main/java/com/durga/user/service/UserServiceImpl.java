package com.durga.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;
import com.durga.user.dto.UserDto;
import com.durga.user.repository.UserRepository;
import com.durga.user.util.UserUtil;

import jakarta.transaction.Transactional;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repo;

	@Override
	public UserDto insert(UserDto dto) throws DataAlreadyExisted {
		// TODO Auto-generated method stub
		if(repo.findByUserEmail(dto.getUserEmail()).isPresent()) {
			throw new DataAlreadyExisted("User Data Already Existed: "+dto.getUserEmail());
		}
		return UserUtil.convertToDto(repo.save(UserUtil.convertToEntity(dto)));
	}

	@Override
	public UserDto update(String userEmail, UserDto dto) throws DataNotExisted {
		// TODO Auto-generated method stub
		if(repo.findByUserEmail(userEmail).isEmpty()) {
			throw new DataNotExisted("No Such Email id is Existed: "+userEmail);
		}
		
		return UserUtil.convertToDto(repo.save(UserUtil.convertToEntity(dto)));
	}

	@Override
	public UserDto findByMailId(String userEmail) throws DataNotExisted {
		return findByUserEmail(userEmail);
	}

	@Override
	public UserDto findByUserEmail(String userEmail) throws DataNotExisted {
		// TODO Auto-generated method stub
		if(repo.findByUserEmail(userEmail).isEmpty()) {
			throw new DataNotExisted("No Such Email id is Existed: "+userEmail);
		}
		return UserUtil.convertToDto(repo.findByUserEmail(userEmail).get());
	}

	@Override
	@Transactional
	public void delete(String userEmail) throws DataNotExisted {
		// TODO Auto-generated method stub
		if(repo.findByUserEmail(userEmail).isEmpty()) {
			throw new DataNotExisted("No Such Email id is Existed: "+userEmail);
		}
		repo.deleteByUserEmail(userEmail);

	}

}
