<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
				<!-- Update Account Settings form -->
				<form action="update.do" method="POST">
					<!-- Fields to update/change -->
					<label for="username">Username</label><br>
					<input type="text" value="${profile.user.username }" name="username" min="6" max="35" message="Must be at least 6 characters"/><br>
					
					<label for="password">Password</label><br>
					<input type="text" value="${profile.user.password }" name="password" min="6" max="35"/><br>
					
					<label for="email">Email</label><br>
					<input type="email" value="${profile.user.email }" name="email" /><br>
					
					<label for="profilePic">Profile Picture URL</label><br>
					<input type="url" value="${profile.profilePic }" name="profilePic" /><br>
					
					<label for="bio">Bio</label><br>
					<input type="text" value="${profile.bio }" name="bio" /><br>
					<input type="hidden" name="pid" value="${profile.id }">
					
					<!-- Hidden Values that User should not Change -->
			
					
					<!-- Submit -->
					<input type="submit" value="Update Account" />
				</form>
				
				<!-- Delete Account form -->
				<form action="delete.do" method="POST">
					<input type="submit" value="Delete Account" />
				</form>
			</div>
			<div class="col-sm-2"></div>
		</div>
		<div class="footer">powered by unholy desolation</div>
	</div>
</body>
</html>