package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@WebServlet("/car")
public class CarInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		
		Map<String, String> carMap = new HashMap<>();
		
		carMap.put("1","쏘나타");
		carMap.put("2","K5");
		carMap.put("3","SM6");
		
		String id = request.getParameter("id");
		String model = carMap.get(id);

		JsonObject respData = new JsonObject();

		if(model == null) {
			respData.addProperty("statusCode", 400);
			respData.addProperty("errorMessage", "Not Found!");
			
		}else {
			respData.addProperty("id",id);
			respData.addProperty("model",model);

		}
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(respData.toString());
		
	    
}
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		String requestJson = bufferedReader.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		List<Map<String, String>> requestData = gson.fromJson(requestJson, List.class);
		
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		requestData.forEach(obj ->{
			System.out.println("id(" + obj.get("id") + "): "  + obj.get("model"));
			out.print("id(" + obj.get("id") + "): "  + obj.get("model"));
		});
		
		
		
		
		
	}
}
