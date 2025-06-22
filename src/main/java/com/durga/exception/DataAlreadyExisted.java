package com.durga.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class DataAlreadyExisted extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
}
