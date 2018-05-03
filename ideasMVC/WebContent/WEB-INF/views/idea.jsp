<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
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
			<div class="col-sm-8 idearow">

				<div align="center" class="errorMessage">
					<h1 class="textformat">${ideaCreatedMessage }</h1>
					<h1 class="textformat">${ideaNotDeletedMessage }</h1>
					<h1 class="textformat">${mustBeLoggedInMessage }</h1>
					<h1 class="textformat">${noPermDeactivateIdeaMessage }</h1>
					<h1 class="textformat">${noPermActivateIdeaMessage }</h1>
					<h1 class="textformat">${noPermDeactivateCommentMessage }</h1>
					<h1 class="textformat">${activatedIdeaMessage }</h1>
					<h1 class="textformat">${deactivatedIdeaMessage }</h1>
					<h1 class="textformat">${deactivateMessage }</h1>
				</div>

				<a href="toProfile.do?pid=${idea.profile.id }"><img
					src="${idea.profile.profilePic }"
					alt="Image of ${idea.profile.user.username }" class="imgsize4" /></a>
				<h1 class="textformat" align="center"><c:out value="${idea.name }"></c:out></h1>
				<div align="center">
					<a href="likeIdeaFromIdea.do?iid=${idea.id }"><i
						class="fa fa-thumbs-o-up fa-2x" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;<a
						href="dislikeIdeaFromIdea.do?iid=${idea.id }"><i
						class="fa fa-thumbs-o-down fa-2x" aria-hidden="true"></i></a><br>${idea.likes }&nbsp;&nbsp;&nbsp;
					- &nbsp;&nbsp;&nbsp;${idea.dislikes }
				</div>
				<h4 class="textformat"><c:out value="${idea.content }"></c:out></h4>
				<br>
				<c:if
					test="${(loggedInUser == idea.profile.user || loggedInUser.profile.user.admin) && idea.active == true}">
					<form action="deactivateIdea.do" method="GET">
						<input type="submit" value="Deactivate Idea" /> <input
							type="hidden" name="iid" value="${idea.id }">
						<!-- This should be replaced by a session profile -->
						<input type="hidden" name="pid" value="${idea.profile.id}">
					</form>
				</c:if>
				<c:if
					test="${(loggedInUser == idea.profile.user || loggedInUser.profile.user.admin) && idea.active == false}">
					<form action="activateIdea.do" method="GET">
						<input type="submit" value="Activate Idea" /> <input
							type="hidden" name="iid" value="${idea.id }">
						<!-- This should be replaced by a session profile -->
						<input type="hidden" name="pid" value="${idea.profile.id}">
					</form>
				</c:if>
			</div>

		</div>
		<div class="container-fluid">


			<div class="row">
				<div class="col-sm-2"></div>
				<!-- Sort by, don't know if this was stretch goal or not -->
				<div class="col-sm-5">
					<form action="sortComments.do" method="GET">
						<select name="sortChoice">
							<option value="newest">Newest First</option>
							<option value="oldest">Oldest First</option>
							<option value="like">Most Likes</option>
							<option value="dislike">Most Dislikes</option>
							<option value="controversy">Sort by Controversy</option>
						</select><input type="hidden" value="${idea.id }" name="ideaId" /> <input
							type="submit" value="Submit" />
					</form>
				</div>
				<div class="col-sm-4">

					<form action="postComment.do" method="POST">
						<input type="Text" placeholder="Comment" name="content" /> <input
							type="hidden" value="${loggedInUser.profile }" name="profile" />
						<input type="hidden" value="${idea.id }" name="ideaId" /> <input
							type="Submit" value="Post Comment" />
					</form>
				</div>
			</div>
		</div>
		<!-- Listing for comments -->
	</div>
	<c:forEach var="comment" items="${comments}">
		<div class="container-fluid">


			<%-- 				<div class="row">
					<div class="col-sm-3"></div>
					<div class="col-sm-1 idearow">
						<h3>
							&nbsp;&nbsp;&nbsp; <a
								href="toProfile.do?pid=${comment.profile.id }"><img
								src="${comment.profile.profilePic }"
								alt="Image of ${comment.profile.user.username }"
								class="imgsize2" /></a>

						</h3>
						<a href="likeComment.do?cid=${comment.id }&iid=${idea.id}"><i
							class="fa fa-thumbs-o-up fa-2x" aria-hidden="true"></i>&nbsp;&nbsp;&nbsp;</a>
						<a href="dislikeComment.do?cid=${comment.id }&iid=${idea.id}"><i
							class="fa fa-thumbs-o-down fa-2x" aria-hidden="true"></i></a>
						&nbsp;&nbsp;&nbsp; <br>${comment.likes } &nbsp;&nbsp;&nbsp; -
						&nbsp;&nbsp;&nbsp; ${comment.dislikes }
					</div>
					<div class="col-sm-5 idearow">

						${comment.content }
						<p>${comment.dateCreated }</p>
						<br>
					</div> --%>

			<div id="page-wrap">
				<div id="one-true" class="group">
					<div class="row">
						<div class="col-xs-2 col-md-1 ideaicons col1">
							<a href="toProfile.do?pid=${comment.profile.id }"><img
								src="${comment.profile.profilePic }"
								alt="Image of ${comment.profile.user.username }"
								class="imgsize2" /></a>

						</div>
						<div class="col-xs-2 col-md-1 ideaicons col1">
							<br> <br> <a
								href="likeComment.do?cid=${comment.id }&iid=${idea.id}"><i
								class="fa fa-thumbs-o-up fa-2x" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
							<a href="dislikeComment.do?cid=${comment.id }&iid=${idea.id}"><i
								class="fa fa-thumbs-o-down fa-2x" aria-hidden="true"></i></a>
							&nbsp;&nbsp;&nbsp; <br>${comment.likes } &nbsp;&nbsp;&nbsp;
							- &nbsp;&nbsp;&nbsp; ${comment.dislikes }
						</div>
						<div class="col-xs-8 col-md-10 idearow col">
							<h4>${comment.content }</h4>
							<br>
							<h6>${comment.dateCreated }</h6>
							<c:if
								test="${loggedInUser == comment.profile.user || loggedInUser.profile.user.admin}">
								<form action="deactivateComment.do" method="GET">
									<input type="submit" value="Deactivate Comment" /> <input
										type="hidden" name="iid" value="${idea.id }">
									<!-- This should be replaced by a session profile -->
									<input type="hidden" name="cid" value="${comment.id}">
								</form>
							</c:if>
						</div>
					</div>
				</div>
			</div>





		</div>

	</c:forEach>


	<div class="col-sm-2"></div>
	<div class="footer">Powered by Unholy Desolation</div>
</body>
</html>