package com.study.servlet.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;


public class RequestDto<T> {

	public static <T> T converRequestBody(HttpServletRequest request, Class<?> c) throws IOException{
		
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String requestBody = bufferedReader.lines().collect(Collectors.joining());
		
		System.out.println(requestBody);
		Gson gson = new Gson();
		T object = (T) gson.fromJson(requestBody,c);
		
		return object;
	}
}
