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
		<label for="firstName">First Name</label>
		<input type="text" name="firstName" /><br>
		
		<label for="lastName">Last Name</label>
		<input type="text" name="lastName" /><br>
		
		<label for="email">Email</label>
		<input type="text" name="email" /><br>
		
		<label for="username">Username</label>
		<input type="text" name="username" /><br>
		
		<label for="password">Password</label>
		<input type="text" name="password" /><br>
		
		<label for="confirmPassword">Confirm Password</label>
		<input type="text" name="confirmPassword" /><br>
		
		<input type="submit" value="Create Account" />
	</form>
	
	<!-- Sign In form -->
	<form action="" method="GET">
		<input type="submit" value="Sign In" />
	</form>

</body>
</html>