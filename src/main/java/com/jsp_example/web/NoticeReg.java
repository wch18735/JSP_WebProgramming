package com.jsp_example.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// default settings (Server encoding, Client encoding)
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		// out instance
		PrintWriter out = response.getWriter();
		
		// out target
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		
		// print
		out.println(title);
		out.println(content);
	}
}
