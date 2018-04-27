<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>idea.</title>
</head>
<body>

	<!-- Leading navigation bar to return home -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a
		class="navbar-brand" href="">Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	</nav>

	<h1>${message }</h1>

	<a href="WEB-INF/views/profile.jsp"><img
		src="${idea.profile.profilePic }"
		alt="Image of ${idea.profile.user.username }" /></a>
	<br />
	<h1>${idea.name }</h1>
	<p>${idea.content }</p>
	<br>

	<form action="destoryIdea.do" method="POST">
		<input type="hidden" value="${idea }" name="idea">
	</form>

	<hr>

	<!-- Listing for comments -->
	<c:forEach var="comment" items="${idea.comments}">
		<h3>
			<a href="WEB-INF/views/profile.jsp"><img
				src="${comment.profile.profilePic }"
				alt="Image of ${comment.profile.user.username }" /></a><br />

		</h3>

		<p>${comment.content }</p>
		<p>${comment.dateCreated }</p>
		<br>
	</c:forEach>

	<form action="comment.do" method="POST">
		<input type="Text" placeholder="Comment" name="content" /><br /> <input
			type="hidden" value="${profile }" name="profile" /> <input
			type="hidden" value="${idea }" name="idea" /> <input type="Submit"
			value="Post Comment" />
	</form>

</body>
</html>