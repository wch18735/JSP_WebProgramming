<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<% pageContext.setAttribute("scope", "여기는 pageContext 범위"); %>

<!-- ------------------------------ -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	${scope} <br>
	${pageScope.scope}<br>
	${requestScope.scope}
</body>
</html>
