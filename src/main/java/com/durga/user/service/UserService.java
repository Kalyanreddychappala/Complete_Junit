package com.durga.user.service;

import com.durga.exception.DataAlreadyExisted;
import com.durga.exception.DataNotExisted;
import com.durga.user.dto.UserDto;

public interface UserService {
	
	public UserDto insert(UserDto dto)throws DataAlreadyExisted;
	public UserDto update(String userEmail,UserDto dto)throws DataNotExisted;
	public UserDto findByMailId(String userEmail)throws DataNotExisted;
	public UserDto findByUserEmail(String userEmail)throws DataNotExisted;
	public void delete(String userEmail)throws DataNotExisted;

}
