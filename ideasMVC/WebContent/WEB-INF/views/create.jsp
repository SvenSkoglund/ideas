<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Create Account</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--charset is the set of characters the keyboard is using-->

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

<!-- CUSTOM CSS INCLUDE-->

<!-- <link rel="stylesheet" type="text/css" href="CSS/ideas.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css"> -->

<!--Check for CDN if false load local dist-->

<!--fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Crimson+Text|Raleway"
	rel="stylesheet">
</head>
<body>

	<!-- Leading navigation bar, need to figure out logic for user -->
	<%@ include file="loggedOutNavBar.jsp"%>
	<div class="container-fluid">
	<h1 class="textformat">${passwordMessage }</h1>
	<h1 class="textformat">${createUserMessage }</h1>
	
	<!-- Create Account form -->
<!--  	<form action="createUser.do" method="GET">  -->
	<%-- 	<label for="username">Username</label><br>
		<input type="text" name="username" min="6" max="35" required message="Enter name betweeen 6-35 characters"/><br>
		
		<label for="email">Email</label><br>
		<input type="email" name="email" required/><br>
		
		<label for="password">Password</label><br>
		<input type="password" name="password" /><br>
		
		<label for="confirmPassword">Confirm Password</label><br>
		<input type="password" name="confirmPassword" min="6" max="35" required /><br>
		
		<!-- If we are directing the new user to settings.jsp, we need  -->
		<!-- to make sure we create a Profile object in the controller  -->
		<!-- first and associate it with that User object. Otherwise,   -->
		<!-- you'll get a NullPointerException in settings.jsp. Whoever -->
		<!-- will be in charge of Controller should touch base with me  -->
		<input type="submit" value="Create Account" />
	</form> --%>
	
	<form:form action="createUser.do" method="POST" modelAttribute="userDTO">
	  <form:label path="username">Username:</form:label>
	  <form:input path="username" />
	  <form:errors path="username" />
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <form:label path="email">Email:</form:label>
	  <form:input path="email" />
	  <form:errors path="email" />
	  
	  <br />
	  <form:label path="password">Password:</form:label>
	  <form:input type="password" path="password" />
	  <form:errors path="password" />
   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  <form:label path="confirmPassword"> Confirm Password:</form:label>
	  <form:input type="password" path="confirmPassword" />
	  <form:errors path="confirmPassword" />
   
 		<br /><br />
  	  <input type="submit" value="Create Account" />
	</form:form>
	<br />
	
	
	
	<!-- Sign In form -->
	<form action="toToLogin.do" method="GET">
		<input type="submit" value="Return to Log In" />
	</form>
	</div>
</body>
</html>