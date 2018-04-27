<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Account Settings</title>
</head>
<body>

	<!-- Leading navigation bar to return home -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a class="navbar-brand" href="">Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	</nav>
	
	<!-- Update Account Settings form -->
	<form action="update.do" method="POST">
		<!-- Fields to update/change -->
		<label for="username">Username</label><br>
		<input type="text" value="${profile.user.username } name="username" /><br>
		
		<label for="password">Password</label><br>
		<input type="text" value="${profile.user.password } name="password" /><br>
		
		<label for="email">Email</label><br>
		<input type="text" value="${profile.user.email } name="email" /><br>
		
		<label for="profilePic">Profile Picture URL</label><br>
		<input type="text" value="${profile.profilePic } name="profilePic" /><br>
		
		<label for="bio">Bio</label><br>
		<input type="text" value="${profile.bio } name="bio" /><br>
		
		<!-- Hidden Values that User should not Change -->
		<input type="hidden" name="pid" value="${profile.id }">
		
		<input type="hidden" name="pid" value="${profile.ideas }">
		
		<input type="hidden" name="pid" value="${profile.comments }">
		
		<input type="hidden" name="pid" value="${profile.createdDate }">
		
		<input type="hidden" name="pid" value="${profile.reputation }">
		
		<input type="hidden" name="pid" value="${profile.postCount }">
		
		<!-- Submit -->
		<input type="submit" value="Update Account" />
	</form>
	
	<!-- Delete Account form -->
	<form action="delete.do" method="POST">
		<input type="submit" value="Delete Account" />
	</form>

</body>
</html>