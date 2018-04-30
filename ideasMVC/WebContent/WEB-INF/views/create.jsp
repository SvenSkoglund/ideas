<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
</head>
<body>

	<!-- Leading navigation bar, need to figure out logic for user -->
	<%@ include file="loggedOutNavBar.jsp"%>
	
	<h1>${passwordMessage }</h1>
	<h1>${createUserMessage }</h1>
	
	<!-- Create Account form -->
	<form action="createUser.do" method="GET">
		<label for="username">Username</label><br>
		<input type="text" name="username" min="6" max="35" required/><br>
		
		<label for="password">Password</label><br>
		<input type="text" name="password" /><br>
		
		<label for="email">Email</label><br>
		<input type="email" name="email" required/><br>
		
		<label for="confirmPassword">Confirm Password</label><br>
		<input type="text" name="confirmPassword" min="6" max="35" required /><br>
		
		<!-- If we are directing the new user to settings.jsp, we need  -->
		<!-- to make sure we create a Profile object in the controller  -->
		<!-- first and associate it with that User object. Otherwise,   -->
		<!-- you'll get a NullPointerException in settings.jsp. Whoever -->
		<!-- will be in charge of Controller should touch base with me  -->
		<input type="submit" value="Create Account" />
	</form>
	
	<!-- Sign In form -->
	<form action="" method="GET">
		<input type="submit" value="Sign In" />
	</form>

</body>
</html>