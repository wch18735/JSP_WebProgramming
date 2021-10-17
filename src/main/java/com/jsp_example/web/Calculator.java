package com.jsp_example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		String expression = "0";
		if(cookies != null) {
			for(Cookie c : cookies) {
				if(c.getName().equals("expression")) {
					expression = c.getValue();
					break;
				}
			}
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8;");
		PrintWriter out = response.getWriter();
		
		out.write("<style>\r\n"
				+ "	input{\r\n"
				+ "		width: 50px;\r\n"
				+ "		height:50px;\r\n"
				+ "	}\r\n"
				+ "	\r\n"
				+ "	.output {\r\n"
				+ "		height: 50px;\r\n"
				+ "		background: #e9e9e9;\r\n"
				+ "		font-size: 24px;\r\n"
				+ "		font-weight: bold;\r\n"
				+ "		text-align: right;\r\n"
				+ "		padding: 0px 5px;\r\n"
				+ "	}\r\n"
				+ "</style>\r\n"
				+ "\r\n"
				+ "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ " <form method=\"post\">\r\n"
				+ "		<table>\r\n"
				+ "			<tr>\r\n");
		out.printf("				<td colspan=\"4\" class=\"output\">%s</td>\r\n", expression);
		out.write("			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"CE\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"C\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"←\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"/\"/></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"7\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"8\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"9\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"*\"/></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"4\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"5\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"6\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"-\"/></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"1\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"2\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"3\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"+\"/></td>\r\n"
				+ "			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"value\" value=\"0\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"dot\" value=\".\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"=\"/></td>\r\n"
				+ "			</tr>\r\n"
				+ "		</table>\r\n"
				+ "		\r\n"
				+ "		<div>\r\n"
				+ "			result: 0\r\n"
				+ "		</div>\r\n"
				+ "	</form>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		expCookie.setPath("/calculator");
		response.addCookie(expCookie);

		// servlet 재실행
		response.sendRedirect("calculator");
	}
}
