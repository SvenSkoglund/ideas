<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>idea.</title>
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
				
				
					<h1 class="textformat">${ideaCreatedMessage }</h1>
					<h1 class="textformat">${ideaNotDeletedMessage }</h1>
					<h1 class="textformat">${mustBeLoggedInMessage }</h1>
					<h1 class="textformat">${noPermDeactivateIdeaMessage }</h1>
					<h1 class="textformat">${noPermActivateIdeaMessage }</h1>
					<h1 class="textformat">${noPermDeactivateCommentMessage }</h1>
				
					<a href="toProfile.do?pid=${idea.profile.id }"><img src="${idea.profile.profilePic }" alt="Image of ${idea.profile.user.username }" class="imgsize3" /></a>
					<br>
					<h1 class="textformat">${idea.name }</h1>
					<p class="textformat">${idea.content }</p>
					<br><a href="likeIdeaFromIdea.do?iid=${idea.id }"><i class="fa fa-thumbs-o-up fa-2x" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<a href="dislikeIdeaFromIdea.do?iid=${idea.id }"><i class="fa fa-thumbs-o-down fa-2x" aria-hidden="true"></i></a><br>${idea.likes }&nbsp;&nbsp;&nbsp; - &nbsp;&nbsp;&nbsp;${idea.dislikes }
					
						<c:if test="${(loggedInUser == idea.profile.user || loggedInUser.profile.user.admin) && idea.active == true}">
						<form action="deactivateIdea.do" method="GET">
							<input type="submit" value="Deactivate Idea" /> <input type="hidden"
								name="iid" value="${idea.id }">
							<!-- This should be replaced by a session profile -->
							<input type="hidden" name="pid" value="${idea.profile.id}">
						</form>
					</c:if>
						<c:if test="${(loggedInUser == idea.profile.user || loggedInUser.profile.user.admin) && idea.active == false}">
						<form action="activateIdea.do" method="GET">
							<input type="submit" value="Activate Idea" /> <input type="hidden"
								name="iid" value="${idea.id }">
							<!-- This should be replaced by a session profile -->
							<input type="hidden" name="pid" value="${idea.profile.id}">
						</form>
					</c:if>
						
						
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
							<br>
							<input type="hidden" value ="${idea.id }" name="ideaId" /> <input type="submit" value="Submit" />
						</form>
						<br><br>
						<!-- Listing for comments -->
						<c:forEach var="comment" items="${comments}">
							<h3>
								<a href="toProfile.do?pid=${comment.profile.id }"><img
									src="${comment.profile.profilePic }"
									alt="Image of ${comment.profile.user.username }" class="imgsize3"/></a><br />
				
							</h3>
				<a href="likeComment.do?cid=${comment.id }&iid=${idea.id}"><i class="fa fa-thumbs-o-up fa-2x" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;</a>
				<a href="dislikeComment.do?cid=${comment.id }&iid=${idea.id}"><i class="fa fa-thumbs-o-down fa-2x" aria-hidden="true"></i></a><br>${comment.likes } &nbsp;&nbsp;&nbsp; - &nbsp;&nbsp;&nbsp; ${comment.dislikes }
					
							<p>${comment.content }</p>
							<p>${comment.dateCreated }</p>
							<br>
							<c:if test="${loggedInUser == comment.profile.user || loggedInUser.profile.user.admin}">
								<form action="deactivateComment.do" method="GET">
									<input type="submit" value="Deactivate Comment" /> <input
										type="hidden" name="iid" value="${idea.id }">
									<!-- This should be replaced by a session profile -->
									<input type="hidden" name="cid" value="${comment.id}">
								</form>
							</c:if>
				
						</c:forEach>
				
						<form action="postComment.do" method="POST">
							<input type="Text" placeholder="Comment" name="content" /><br /> <input
								type="hidden" value="${loggedInUser.profile }" name="profile" /> <input
								type="hidden" value="${idea.id }" name="ideaId" /> <input type="Submit"
								value="Post Comment" />
						</form>
				</div>
				<div class="col-sm-2"></div>
			</div>
			<div class="footer">powered by unholy desolation</div>
		</div>
</body>
</html>