package com.jsp_example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calcpage")
public class CalcPage extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
				+ " <form action=\"calc3\" method=\"post\">\r\n"
				+ "		<table>\r\n"
				+ "			<tr>\r\n");
		out.printf("				<td colspan=\"4\" class=\"output\">%s</td>\r\n", expression);
		out.write("			</tr>\r\n"
				+ "			<tr>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"CE\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"C\"/></td>\r\n"
				+ "				<td><input type=\"submit\" name=\"operator\" value=\"ก็\"/></td>\r\n"
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
}