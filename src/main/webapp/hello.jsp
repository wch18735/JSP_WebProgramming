<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
String _age = request.getParameter("age");
String age = "0";

if(_age != null && !_age.equals(""))
	age = _age;

out.print("I'm " + age + " years old\n");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
</body>
</html>