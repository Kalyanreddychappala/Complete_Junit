package com.durga.user.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private String userEmail;
	private String firstName;
	private String lastName;
	private Long phno;
	private String password;
	private Date dob;

}
