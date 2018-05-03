<!DOCTYPE html>
<html lang="en">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--class full means-->

<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
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

		</c:otherwise>
	</c:choose>

	<div class="container-fluid">

		<div class="row">
			<div class="col-sm-2"></div>
			<div class="col-sm-8">

				<div align="center" class="errorMessage">
					<c:if test="${empty ideaList }">
						<h3 class="textformat">No Ideas Found</h3></c:if>
					<h3 class="textformat">${logoutMessage }</h3>
					<h3 class="textformat">${mustBeLoggedInMessage }</h3>
					<h3 class="textformat">${accountNotFoundMessage }</h3>
				</div>
			</div>
		</div>


		<div class="row">
			<div class="col-xs-3 col-sm-1"></div>
			<div class="col-xs-5 col-sm-8">
				<!-- Sort by, don't know if this was stretch goal or not -->
				<c:choose>
					<c:when test="${empty ideaKeyword}">
						<form action="sorting.do" method="GET">
					</c:when>
					<c:otherwise>
						<form action="searchSorting.do" method="GET">
					</c:otherwise>
				</c:choose>
				<select name="sortChoice">
					<option value="newest">Newest First</option>
					<option value="oldest">Oldest First</option>
					<option value="like">Most Likes</option>
					<option value="dislike">Most Dislikes</option>
					<option value="controversy">Sort by Controversy</option>
					<option value="username">Sort by Username</option>
				</select><input type="hidden" name="ideaKeyword" value="${ideaKeyword }" />
				<input type="submit" value="Submit" />
				</form>
			</div>

			<div class="col-xs-4 col-sm-3">
				<!-- Search bar for ideas -->
				<form action="search.do" method="GET">
					<input type="text" name="ideaKeyword" /> <input type="submit"
						value="Search for Idea" />
				</form>
			</div>

		</div>

		<!-- Listing for ideas -->
		<c:forEach var="i" items="${ideaList}">
			<div id="page-wrap">
				<div id="one-true" class="group">
					<div class="row">
						<div class="col-xs-2 col-md-1 ideaicons col1">
							<img alt="Picture of ${i.profile.user.username}"
								src="${i.profile.profilePic}" class="imgsize2">
						</div>
						<div class="col-xs-2 col-md-1 ideaicons col1">
							<br> <br> <a
								href="likeIdea.do?iid=${i.id }&ideaKeyword=${ideaKeyword }&sortChoice=${sortChoice }"><i
								class="fa fa-thumbs-o-up fa-2x" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<a
								href="dislikeIdea.do?iid=${i.id }&ideaKeyword=${ideaKeyword }&sortChoice=${sortChoice }""><i
								class="fa fa-thumbs-o-down fa-2x" aria-hidden="true"></i></a><br>${i.likes }
							&nbsp;&nbsp;&nbsp; - &nbsp;&nbsp;&nbsp; ${i.dislikes }
						</div>
						<div class="col-xs-8 col-md-10 idearow col"><br>
							<a href="toIdea.do?iid=${i.id }"><h3>${i.name}</h3></a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>

		<div class="footer">Powered by Unholy Desolation</div>
	</div>
</body>
</html>