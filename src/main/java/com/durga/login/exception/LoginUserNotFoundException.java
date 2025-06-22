package com.durga.login.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message;

}
