<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--class full means-->

<head>
<title>ideas.</title>

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
		        Currently logged in as ${loggedInUser.username }
			</c:otherwise>
		</c:choose>

	<div class="container-fluid">

		
		<h1>${logoutMessage }</h1>
		<h1>${message }</h1>
			<div class="row">
				<div class="col-sm-9">
				<!-- Sort by, don't know if this was stretch goal or not -->
					<form action="sorting.do" method="GET">
						<select name="sortChoice">
							<option value="newest">Newest First</option>
							<option value="oldest">Oldest First</option>
							<option value="like">Most Likes</option>
							<option value="dislike">Most Dislikes</option>
							<option value="controversy">Sort by Controversy</option>
							<option value="username">Sort by Username</option>
						</select> 
						<br>
						<input type="hidden" name="ideaList" value="${ideaList }">
						<input type="submit" value="Submit" />
					</form>
				</div>
				
				<%-- MOVE THIS CREATE IDEA TO NAVBAR WHEN LOGGED IN --%>
				<%-- TEMPORARY INSTALL TO CHECK toPostIdea.do LOGIC --%>
				<%-- <div class = "col-sm-5">
					<!-- Create your own idea form -->
					<form action="toPostIdea.do" method="GET">
						<input type="hidden" value="${profile }" name="profile" />
						<input type="Submit" value="Create your own idea!" />
					</form>
				</div> --%>
				
				
				<div class="col-sm-3">
				<!-- Search bar for ideas -->
				<form action="search.do" method="GET">
					<input type="text" name="ideaKeyword" /> 
					 <br><input type="submit" value="Search for Idea" />
				</form>
				</div>
				
			</div>
	
		<!-- Listing for ideas -->
		<c:forEach var="i" items="${ideaList}"  >
			<div id="page-wrap">
          	<div id="one-true" class="group">
          	<div class="row">
				<div class="col-xs-2 col-md-1 ideaicons col"><img alt="Picture of ${i.profile.user.username}" src="${i.profile.profilePic}" class="imgsize2"></div>
				<div class="col-xs-2 col-md-1 ideaicons col">	<br><a href="likeIdea.do?iid=${i.id }"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<a href="dislikeIdea.do?iid=${i.id }"><i class="fa fa-thumbs-o-down" aria-hidden="true"></i></a><br>${i.likes } - ${i.dislikes }</div>
				<div class="col-xs-8 col-md-10 idearow col"><a href="toIdea.do?iid=${i.id }">${i.name}</a></div>
			</div>
			</div>
			</div>
		</c:forEach>

	</div>

</body>
</html>