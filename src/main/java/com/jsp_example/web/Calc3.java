package com.jsp_example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		// 사용자가 클릭한 내용 얻어옴
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
		
		// 쿠키 정보 얻어오기
		String expression = "";
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("expression")) {
					expression = c.getValue();
					break;
				}
			}
		}
		
		if(operator != null && operator.equals("=")) {
			// operation 이 = 이면 계산
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
			try {
				expression = String.valueOf(engine.eval(expression));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(operator != null && operator.equals("C")) {
			// Cookie 지우기
			expression = "";
		}
		else {
			// operation 이 = 이 아닌 경우 string 누적
			expression += (value == null) ? "" : value;
			expression += (operator == null) ? "" : operator;
			expression += (dot == null) ? "" : dot;
		}
		
		// Cookie 생성
		Cookie expCookie = new Cookie("expression", expression);
		if(operator != null && operator.equals("C")) expCookie.setMaxAge(0);
		
		// expression 만들어서 redirection
		response.addCookie(expCookie);

		// servlet 재실행
		response.sendRedirect("calcpage");
	}
}