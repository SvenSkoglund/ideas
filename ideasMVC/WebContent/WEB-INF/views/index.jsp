<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ideas.</title>
</head>
<body>

	<!-- Leading navigation bar, need to figure out logic for user -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a class="navbar-brand" href="">Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav">
			<li class="nav-item"><a href="">Log In<span class="sr-only">(current)</span></a></li>
			<li class="nav-item"><a href="">Create Account<span class="sr-only">(current)</span></a></li>
		</ul>
	</div>
	</nav>

	<!-- Header for website -->
	<h1>ideas.</h1>

	<hr>
	
	<!-- Search bar for ideas -->
	<form action="" method="GET">
		<input type="text" name="ideaName" />
		<input type="submit" value="Search for Idea" />
	</form><br>
	
	<!-- Sort by, don't know if this was stretch goal or not -->
	<form action="" method="GET">
		<input type="submit" value="Sort by Date" />
	</form>
	
	<form action="" method="GET">
		<input type="submit" value="Sort by Likes" />
	</form>
	
	<form action="" method="GET">
		<input type="submit" value="Sort by Controversy" />
	</form>
	
	<form action="" method="GET">
		<input type="submit" value="Sort by Username" />
	</form>
	
	<hr>

	<!-- Listing for ideas -->
	<c:forEach var="i" items="${ideaList}">
		<h3>
			<a href="show.do?iid=${i.id }" id="ideaLink">${i.name}</a>
		</h3>
		<br>
	</c:forEach>


</body>
</html>