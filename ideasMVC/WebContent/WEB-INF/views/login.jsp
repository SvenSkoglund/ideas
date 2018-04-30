<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

	<!-- Leading navigation bar, need to figure out logic for user -->
	<%@ include file="loggedOutNavBar.jsp"%>
	
	<h1>${message }</h1>
	<!-- Login form -->
	<form action="login.do" method="POST">
		<label for="username">Username</label>
		<input type="text" name="username" /><br>
		
		<label for="password">Password</label>
		<input type="text" name="password" /><br>
		
		<input type="submit" value="Log In" />
	</form>
	
	<!-- Create Account form -->
	<form action="" method="GET">
		<input type="submit" value="Create Account" />
	</form>

</body>
</html>