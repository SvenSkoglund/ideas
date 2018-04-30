<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ideas.</title>
</head>
<body>

	<!-- Leading navigation bar, need to figure out logic for user -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a class="navbar-brand" href="index.do">ideas.</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item"><a href="toToLogin.do">Log In<span class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a href="logout.do">Log Out<span class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a href="toCreateAccount.do">Create Account<span class="sr-only">(current)</span></a></li>
		</ul>
	</div>
	</nav>

	<!-- Header for website -->
	<h1>ideas.</h1>

	<hr>
	<h1>${message }</h1>
	<!-- Search bar for ideas -->
	<form action="search.do" method="GET">
		<input type="text" name="ideaKeyword" />
		<input type="submit" value="Search for Idea" />
	</form><br>
	
	<!-- Sort by, don't know if this was stretch goal or not -->
	<form action="date.do" method="GET">
		<input type="submit" value="Sort by Date" />
	</form>
	
	<form action="like.do" method="GET">
		<input type="submit" value="Sort by Likes" />
	</form>
	
	<form action="controversy.do" method="GET">
		<input type="submit" value="Sort by Controversy" />
	</form>
	
	<form action="username.do" method="GET">
		<input type="submit" value="Sort by Username" />
	</form>
	
	<hr>
	
	<!-- Create your own idea form -->
	<form action="toPostIdea.do" method="GET">
		<input type="hidden" value="${profile }" name="profile"/>
		<input type="Submit" value="Create your own idea!" />
	</form>

	<!-- Listing for ideas -->
	<c:forEach var="i" items="${ideaList}">
		<h3>
			<a href="toIdea.do?iid=${i.id }" id="ideaLink">${i.name}</a>
		</h3>
		<br>
	</c:forEach>


</body>
</html>