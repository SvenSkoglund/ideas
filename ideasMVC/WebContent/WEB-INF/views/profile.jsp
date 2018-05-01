<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${profile.user.username } Profile</title>
	
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
		<h1 class="textformat">${message }</h1>
		
		<!-- Big headline telling you whose profile you're looking at -->
		<h1 class="textformat">${profile.user.username }</h1><br>
		
		<img alt="Profile picture of ${profile.user.username }" src="${profile.profilePic }" class="imgsize3"><br>
	
		<h5 class="textformat">Member since: ${profile.createdDate }</h5><br>
		<p class="textformat">${profile.bio }</p>
		
		<hr>
		
		<!-- Deactivate Profile form -->
		<c:if test="${loggedInUser == profile.user || loggedInUser.profile.user.admin}">
			<form action="deactivateProfile.do" method="GET">
				<input type="submit" value="Deactivate Profile" />
				<input type="hidden" name="pid" value="${profile.id }">
				<!-- This should be replaced by a session profile -->
				<input type="hidden" name="profileLoggedInId" value="${profile.id}">
			</form>
		</c:if>
		
		<hr>
		
						<!-- Reputation probably special object in Controller -->
						<%-- 	<h5>Reputation: ${profile.reputation }</h5><br>
						 --%>	
						<!-- ideaCount probably special object in Controller -->
							
		<h5 class="textformat">Total Ideas: ${size }</h5><br>
		
		
		<!-- Listing for user's ideas -->
		<c:forEach var="i" items="${ideas }">
			<h3 class="textformat">
				<a href="toIdea.do?iid=${i.id }" id="ideaLink">${i.name}</a>
			</h3><br>
				<form action="destoryIdea.do" method="POST">
					<input type="hidden" value="${i }" name="idea">
				</form>
			<br>
		</c:forEach>
		 
		
		<!-- Account Settings form -->
		<c:if test="${loggedInUser == profile.user || loggedInUser.profile.user.admin}">
			<form action="toSettings.do" method="GET">
				<input type="submit" value="Account Settings" />
			</form>
		</c:if>
	
	</div>
</body>
</html>