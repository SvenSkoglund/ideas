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
		        Currently logged in as ${loggedInUser.username }
			</c:otherwise>
		</c:choose>
	
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
		
		<!-- Hidden Values that User should not Change -->
		<%-- <input type="hidden" name="pid" value="${profile.id }">
		
		<input type="hidden" name="pid" value="${profile.ideas }">
		
		<input type="hidden" name="pid" value="${profile.comments }">
		
		<input type="hidden" name="pid" value="${profile.createdDate }">
		
		<input type="hidden" name="pid" value="${profile.reputation }">
		
		<input type="hidden" name="pid" value="${profile.postCount }"> --%>
		
		<!-- Submit -->
		<input type="submit" value="Update Account" />
	</form>
	
	<!-- Delete Account form -->
	<form action="delete.do" method="POST">
		<input type="submit" value="Delete Account" />
	</form>

</body>
</html>