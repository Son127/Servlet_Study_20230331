package com.study.servlet.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*") // (?) 여기에 들어있는 것으로 요청 들어올시 필터 걸림,  * = 모든 요청 포함 
public class AuthFilter extends HttpFilter implements Filter {
       
    public AuthFilter() {

    }

	public void destroy() {
		//객체가 소멸 될 때 한번만 실행 
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		//전처리
		System.out.println("전처리");
		/*
		 * 1. url: role요청이 들어왔을 때 로그인이 되어 있는지 확인
		 * 2. 로그인이 되어 있으면 doFilter를 통해 서블릿에 접근 허용
		 * 3. 로그인이 되어 있지 않으면 response를 통해 로그인 페이지로 이동
		 */
		HttpServletRequest  httpRequest = (HttpServletRequest) request; //ServletRequest 를 HttpServletRequest로 다운 캐스팅
		HttpServletResponse httpResponse = (HttpServletResponse) response; //ServletResponse 를 HttpServletResponse로 다운 캐스팅
		
		String uri = httpRequest.getRequestURI();
		
		httpRequest.getSession().setMaxInactiveInterval(20); //setMaxInactiveInterval(초 * ) 기본적으로 30분
		
		List<String> antMatchers = new ArrayList<>();
		antMatchers.add("/user");
		antMatchers.add("/mypage");
		
		for(String antMatcher : antMatchers) {
			if(uri.startsWith(antMatcher)){ //antMatcher에 등록된 URI로 시작하면.인증을 거친다.
				HttpSession session = httpRequest.getSession();
				if(session.getAttribute("AuthenticationPrincipal") == null) {
					httpResponse.sendRedirect("/login.html");
					return;
				}
			}
		};
		
		
		chain.doFilter(request, response);//다음으로 실행될 필터나 서블릿
		//후처리
		System.out.println("후처리");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		//객체가 생성 될 때 한번만 실행 
	}

}
