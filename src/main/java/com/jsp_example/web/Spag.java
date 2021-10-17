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
		
		// 변수 선언
		String result = "";
		String[] numbers = {"One", "Two"};
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id", 1);
		notice.put("title", "This is the title");
		if(num % 2 == 0)
			result = "짝수";
		else
			result = "홀수";
		String scope = "여기는 request 범위";
		
		// request에 값 담기 (key, value)
		request.setAttribute("Result", result);
		request.setAttribute("Numbers", numbers);
		request.setAttribute("Notice", notice);
		request.setAttribute("scope", scope);
		
		// forwarding (현재 request를 전달: 공유하는 영역이 생기는 것)
		// webapp root에 있을 것이라 가정하기 때문에 경로는 그대로
		RequestDispatcher dispatcher =
				request.getRequestDispatcher("spag.jsp");
		dispatcher.forward(request, response);
	}
}
