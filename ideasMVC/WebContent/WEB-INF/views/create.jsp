<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
	  
	  <form:label path="email">Email:</form:label>
	  <form:input path="email" />
	  <form:errors path="email" />
	  
	  <br />
	  <form:label path="password">Password:</form:label>
	  <form:input path="password" />
	  <form:errors path="password" />
  
	  <form:label path="confirmPassword"> Confirm Password:</form:label>
	  <form:input path="confirmPassword" />
	  <form:errors path="confirmPassword" />
   
 		<br />
  	  <input type="submit" value="Create Account" />
	</form:form>
	
	
	
	
	<!-- Sign In form -->
	<form action="login.do" method="GET">
		<input type="submit" value="Sign In" />
	</form>

</body>
</html>