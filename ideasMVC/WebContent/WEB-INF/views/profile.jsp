<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${profile.user.username } Profile</title>
</head>
<body>

	<!-- Leading navigation bar, need to figure out logic for user -->
	<%@ include file="loggedOutNavBar.jsp"%>
	
	<h1>${message }</h1>
	<!-- Big headline telling you whose profile you're looking at -->
	<h1>${profile.user.username }</h1><br>
	
	<img alt="Profile picture of ${profile.user.username }" src="${profile.profilePic }"><br>

	<h5>Member since: ${profile.createdDate }</h5><br>
	<p>${profile.bio }</p>
	
	<hr>
	<!-- Deactivate Profile form -->
	<form action="deactivateProfile.do" method="GET">
		<input type="submit" value="Deactive Profile" />
		<input type="hidden" name="pid" value="${profile.id }">
		<!-- This should be replaced by a session profile -->
		<input type="hidden" name="profileLoggedInId" value="${profile.id}">
		
	</form>
	<hr>
	
	<!-- Reputation probably special object in Controller -->
<%-- 	<h5>Reputation: ${profile.reputation }</h5><br>
 --%>	
	<!-- ideaCount probably special object in Controller -->
	<h5>Total Ideas: ${size }</h5><br>
	
	
	<!-- Listing for user's ideas -->
	<c:forEach var="i" items="${ideas }">
		<h3>
			<a href="toIdea.do?iid=${i.id }" id="ideaLink">${i.name}</a>
		</h3><br>
			<form action="destoryIdea.do" method="POST">
				<input type="hidden" value="${i }" name="idea">
			</form>
		<br>
	</c:forEach>
	 
	
	<!-- Account Settings form -->
	<form action="settings.do" method="POST">
		<input type="submit" value="Account Settings" />
		<input type="hidden" name="pid" value="${profile.id }">
	</form>
	
</body>
</html>