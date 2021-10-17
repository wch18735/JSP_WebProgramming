package com.jsp_example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc")
public class Calc extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		String x_tmp = request.getParameter("x");
		String y_tmp = request.getParameter("y");
		String operator = request.getParameter("button");
		
		Integer x = 10;
		Integer y = 10;
		Integer result = 0;
		if(x_tmp != null && !x_tmp.equals(""))
		{
			try {
				x = Integer.parseInt(x_tmp);
			} catch (NumberFormatException e) {
				out.println(e);
			}
		}
		
		if(y_tmp != null && !y_tmp.equals(""))
		{
			try {
				y = Integer.parseInt(y_tmp);
			} catch (NumberFormatException e) {
				out.println(e);
			}
		}
		
		if (operator.equals("add"))
		{
			result = x + y;
		}
		else if (operator.equals("sub"))
		{
			result = x - y;
		}
		
		out.println(result);
	}
}
