package com.jsp_example.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spag")
public class Spag extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = 0;
		String num_ = request.getParameter("num");
		
		if(num_ != null && !num_.equals(""))
			num = Integer.parseInt(num_);
		
		// ���� ����
		String result = "";
		String[] numbers = {"One", "Two"};
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "This is the title");
		if(num % 2 == 0)
			result = "¦��";
		else
			result = "Ȧ��";
		String scope = "����� request ����";
		
		// request�� �� ��� (key, value)
		request.setAttribute("Result", result);
		request.setAttribute("Numbers", numbers);
		request.setAttribute("Notice", notice);
		request.setAttribute("scope", scope);
		
		// forwarding (���� request�� ����: �����ϴ� ������ ����� ��)
		// webapp root�� ���� ���̶� �����ϱ� ������ ��δ� �״��
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}
