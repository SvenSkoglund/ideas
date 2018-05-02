<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
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
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">

				<div align="center" class="errorMessage">
					<h3 class="textformat">${passwordMessage }</h3>
					<h3 class="textformat">${createUserMessage }</h3>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-2">

				<form:form action="createUser.do" method="POST"
					modelAttribute="userDTO">
					<form:label path="username">Username:</form:label>
					<br>
					<form:input path="username" />
					<form:errors path="username" />
			</div>
			<div class="col-sm-5"></div>
		</div>
		<div class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-2">
				<form:label path="email">Email:</form:label>
				<br>
				<form:input path="email" />
				<form:errors path="email" />
			</div>
			<div class="col-sm-5"></div>
		</div>
		<div class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-2">
				<form:label path="password">Password:</form:label>
				<br>
				<form:input type="password" path="password" />
				<form:errors path="password" />
			</div>
			<div class="col-sm-5"></div>
		</div>
		<div class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-2">
				<form:label path="confirmPassword"> Confirm Password:</form:label>
				<br>
				<form:input type="password" path="confirmPassword" />
				<form:errors path="confirmPassword" />
			</div>
			<div class="col-sm-5"></div>
		</div>
		<div class="row">
			<div class="col-sm-5"></div>
			<div class="col-sm-2">
				<br> <input type="submit" value="Submit" />
				</form:form>
				<br />
			</div>
			<div class="col-sm-5"></div>
		</div>
		<div class="footer">Powered by Unholy Desolation</div>
	</div>
</body>
</html>