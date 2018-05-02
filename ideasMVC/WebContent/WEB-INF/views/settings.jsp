<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account Settings</title>
</head>
<body>

	<!-- Leading navigation bar -->
	<c:choose>
		<c:when test="${empty loggedInUser}">
			<%@ include file="loggedOutNavBar.jsp"%>
		</c:when>
		<c:otherwise>
			<%@ include file="loggedInNavBar.jsp"%>

		</c:otherwise>
	</c:choose>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">

				<div align="center" class="errorMessage">
					<h3 class="textformat">${invalidSettingsMessage }</h3>
				</div>
			</div>
			<div class="col-sm-2"></div>


			<div class="row">
				<div class="col-sm-4"></div>
				<div class="col-sm-4">
					<!-- Update Account Settings form -->
					<form action="update.do" method="POST" align="center">
						<!-- Fields to update/change -->
						<label for="username">Username</label><br> <input
							class="inner" id="title" type="text"
							value="${profile.user.username }" name="username" min="6"
							max="35" message="Must be at least 6 characters" required /><br>
						<br> <label for="password">Password</label><br> <input
							class="inner" id="title" type="text"
							value="${profile.user.password }" name="password" min="6"
							max="35" message="Must be at least 6 characters" required /><br>
						<br> <label for="email">Email</label><br> <input
							class="inner" id="title" type="email"
							value="${profile.user.email }" name="email" /><br> <br>
						<label for="profilePic">Profile Picture URL</label><br> <input
							class="inner" id="title" type="url"
							value="${profile.profilePic }" name="profilePic" /><br> <br>
						<label for="bio">Bio</label><br>
						<textarea class="inner" type="Text" name="bio"></textarea>
						<br> <br> <input type="hidden" name="pid"
							value="${profile.id }">

						<!-- Hidden Values that User should not Change -->

						<br>

						<!-- Submit -->
						<input type="submit" value="Update Account" />
					</form>
				</div>
				<div class="col-sm-4"></div>
			</div>
			<div class="footer">Powered by Unholy Desolation</div>
		</div>
	</div>
</body>
</html>