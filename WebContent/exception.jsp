<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<body>
		<h1> Java Exception Handling Page</h1>
		<h2>Details:</h2>
		<p> Type:    ${pageContext.exception["class"] }
		<p> Message: Please go back to login page 
		<a href="Login.jsp">Login</a>
	</body>
</html>