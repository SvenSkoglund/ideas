<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
</head>
<body>

	<!-- Leading navigation bar to return home -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a class="navbar-brand" href="">Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	</nav>
	
	<!-- Create Account form -->
	<form action="create.do" method="POST">
		<label for="username">Username</label><br>
		<input type="text" name="username" /><br>
		
		<label for="password">Password</label><br>
		<input type="text" name="password" /><br>
		
		<label for="email">Email</label><br>
		<input type="text" name="email" /><br>
		
		<label for="confirmPassword">Confirm Password</label><br>
		<input type="text" name="confirmPassword" /><br>
		
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