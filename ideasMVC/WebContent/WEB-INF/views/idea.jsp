<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>idea.</title>
</head>
<body>

	<!-- Leading navigation bar, need to figure out logic for user -->
	<%@ include file="loggedOutNavBar.jsp"%>

	<h1>${message }</h1>

	<a href="toProfile.do?pid=${idea.profile.id }"><img
		src="${idea.profile.profilePic }"
		alt="Image of ${idea.profile.user.username }" /></a>
	<br>
	<h1>${idea.name }</h1>
	<p>${idea.content }</p>
	<br>

	<form action="deactivateIdea.do" method="GET">
		<input type="submit" value="Deactive Idea" /> <input type="hidden"
			name="iid" value="${idea.id }">
		<!-- This should be replaced by a session profile -->
		<input type="hidden" name="pid" value="${idea.profile.id}">
	</form>
		<hr>
				<!-- Sort by, don't know if this was stretch goal or not -->
		<form action="sortComments.do" method="GET">
			<select name="sortChoice">
				<option value="newest">Newest First</option>
				<option value="oldest">Oldest First</option>
				<option value="like">Most Likes</option>
				<option value="dislike">Most Dislikes</option>
				<option value="controversy">Sort by Controversy</option>
			</select>
			<input type="hidden" value ="${idea.id }" name="ideaId" /> <input type="submit" value="Submit" />
		</form>

		<!-- Listing for comments -->
		<c:forEach var="comment" items="${comments}">
			<h3>
				<a href="toProfile.do?pid=${comment.profile.id }"><img
					src="${comment.profile.profilePic }"
					alt="Image of ${comment.profile.user.username }" /></a><br />

			</h3>

			<p>${comment.content }</p>
			<p>${comment.dateCreated }</p>
			<br>
			<form action="deactivateComment.do" method="GET">
				<input type="submit" value="Deactive Comment" /> <input
					type="hidden" name="iid" value="${idea.id }">
				<!-- This should be replaced by a session profile -->
				<input type="hidden" name="cid" value="${comment.id}">

			</form>
		</c:forEach>

		<form action="comment.do" method="POST">
			<input type="Text" placeholder="Comment" name="content" /><br /> <input
				type="hidden" value="${profile }" name="profile" /> <input
				type="hidden" value="${idea }" name="idea" /> <input type="Submit"
				value="Post Comment" />
		</form>
</body>
</html>