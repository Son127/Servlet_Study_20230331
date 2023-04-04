package com.study.servlet.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginView extends HttpServlet {
	private static final long serialVersionUID = 1L;
  


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("welcome", "안녕하세요");
		request.setAttribute("numbers", new int[] {10,20,30,40,50,60});
		
		
		RequestDispatcher dispatcher =  request.getRequestDispatcher("/WEB-INF/login.jsp"); 
		dispatcher.forward(request, response);
	}

}
