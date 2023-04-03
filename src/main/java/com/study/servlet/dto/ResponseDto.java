package com.study.servlet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseDto<T>{
	private int statusCode;
	private String message;
	private T data;
	

}
