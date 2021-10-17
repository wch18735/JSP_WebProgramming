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

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		// Session
		HttpSession session = request.getSession();
		
		// Cookie
		Cookie[] cookies = request.getCookies();
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		if(!v_.equals("")) v = Integer.parseInt(v_);
		
		if(op.equals("="))
		{
			int x = 0, y = v, result = 0;
			String operator = "";
			if(cookies != null)
				for(Cookie _tmp: cookies)
					if(_tmp.getName().equals("value"))
						x = Integer.parseInt(_tmp.getValue());
			
			if(cookies != null)
				for(Cookie _tmp: cookies)
					if(_tmp.getName().equals("op"))
						operator = _tmp.getValue();
			
			if(operator.equals("+"))
				result = x + y;
			else
				result = x - y;
			
			response.getWriter().printf("result is %d\n", result);
		}
		else 
		{
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			valueCookie.setPath("/calc2");
			opCookie.setPath("/calc2");
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
			response.sendRedirect("calc2.html");
		}
	}
}