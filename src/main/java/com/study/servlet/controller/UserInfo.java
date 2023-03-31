package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/user")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		
		System.out.println("get요청");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		Map<String, String> userMap = new HashMap<>();
		userMap.put("name", name);
		userMap.put("phone", phone);
		
		String userJson = gson.toJson(userMap);
		System.out.println(userJson);
		
		/** get 요청의 특징>
		 * 1. 주소창, src, href, replace 등으로 요청할 수 있음.
		 * 2. 데이터를 전달하는 방법(Qurey String) 
		 * 		ex) http(s)://서버주소:포트/요청메시지?key=value&key=value
		 * 3.
		 */
		
		System.out.println(response.getCharacterEncoding());
		
//		response.addHeader("Content-Type", "text/html;charset=EUC-KR");
//		response.addHeader("test", "text data");
//		response.setContentType("text/html;charset=UTF-8");				 //HTML 응답
		
		response.setContentType("application/json;charset=UTF-8"); // JSON 응답
		
		PrintWriter out = response.getWriter();
//		out.println("이름: " + name);
//		out.println("연락처: " + phone);
		out.println(userJson);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("post요청");
	    request.setCharacterEncoding("UTF-8");
	    ServletInputStream inputStream = request.getInputStream();
	    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	    
	    String json = "";
	    String line = null;
	    while ((line = bufferedReader.readLine()) != null) {
	        json += line;
	    }
	    json = json.replaceAll(" ", "");
	    System.out.println(json);
		
//		System.out.println(request.getParameter("address"));
//		AtomicReference<String> jsonAtomic = new AtomicReference<>("");
//		bufferedReader.lines().forEach(line -> {
//			jsonAtomic.getAndUpdate(t -> t + line);
//		});
//		String json = jsonAtomic.get().replaceAll(" ", " ");
//		System.out.println(json);
	    
		Gson gson = new Gson();
		
		Map<String, String> jsonMap = gson .fromJson(json, Map.class);
		
		System.out.println(jsonMap);
	
		
		/**
		 * 1.
		 * <from action="요청메세지 (url)" method="Post">
		 * 		<input name="key" value="value">
		 * 		<button type="submit">요청</button>
		 * </from>
		 */
		
		
		
	}
	

}

