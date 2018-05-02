<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link href="images/favicon.ico" rel="icon" type="image/x-icon" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${profile.user.username }Profile</title>

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

					<h1 class="textformat">${profileDeActivatedMessage }</h1>
					<h1 class="textformat">${noPermissionDeActivateMessage }</h1>
					<h1 class="textformat">${noPermissionActivateMessage }</h1>
					<h1 class="textformat">${activaedMessage }</h1>
				</div>


				<!-- Big headline telling you whose profile you're looking at -->

				<img alt="Profile picture of ${profile.user.username }"
					src="${profile.profilePic }" class="imgsize4" align="center"><br>
				<h1 class="textformat" align="center">${profile.user.username }</h1>
				<h5 class="textformat" align="center">Total Ideas: ${size }</h5>

				<p class="textformat">${profile.bio }</p>
				<h5 class="textformat" align="center">Member since:
					${profile.createdDate }</h5>
				<br>


				<!-- Deactivate Profile form -->
				<c:if
					test="${(loggedInUser == profile.user || loggedInUser.profile.user.admin) && profile.active == true}">
					<form action="deactivateProfile.do" method="GET">
						<input type="submit" value="Deactivate Profile" /> <input
							type="hidden" name="pid" value="${profile.id }">
						<!-- This should be replaced by a session profile -->
					</form>
				</c:if>
				<br>
				<c:if
					test="${(loggedInUser == profile.user || loggedInUser.profile.user.admin) && profile.active == false}">
					<form action="activateProfile.do" method="GET">
						<input type="submit" value="Activate Profile" /> <input
							type="hidden" name="pid" value="${profile.id }">
						<!-- This should be replaced by a session profile -->
					</form>
				</c:if>

			</div>
		</div>

		<!-- Reputation probably special object in Controller -->
		<%-- 	<h5>Reputation: ${profile.reputation }</h5><br>
								 --%>
		<!-- ideaCount probably special object in Controller -->

		<br>


		<!-- Listing for user's ideas -->
		<c:forEach var="i" items="${ideas }">
			<div class="container-fluid">
				<div id="page-wrap">
					<div id="one-true" class="group">
						<div class="row">
							<div class="col-xs-2 col-md-1 ideaicons col1">
								<a href="toProfile.do?pid=${i.profile.id }"><img
									src="${i.profile.profilePic }"
									alt="Image of ${comment.profile.user.username }"
									class="imgsize2" /></a>

							</div>
							<div class="col-xs-2 col-md-1 ideaicons col1">
								<br> <br> <i class="fa fa-thumbs-o-up fa-2x"
									aria-hidden="true"></i>&nbsp;&nbsp;&nbsp; <i
									class="fa fa-thumbs-o-down fa-2x" aria-hidden="true"></i>
								&nbsp;&nbsp;&nbsp; <br>${i.likes } &nbsp;&nbsp;&nbsp;
								- &nbsp;&nbsp;&nbsp; ${i.dislikes }
							</div>
							<div class="col-xs-8 col-md-10 idearow col">
						
								<h3 class="textformatHeadline" >
									<a href="toIdea.do?iid=${i.id }" id="ideaLink">${i.name}</a>
								</h3>
								<br>
								<form action="destoryIdea.do" method="POST">
									<input type="hidden" value="${i }" name="idea">
								</form>
								<br>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>


		<!-- Account Settings form -->
		<c:if test="${loggedInUser.profile.user.admin}">
			<form action="toSettings.do" method="GET">
				<input type="submit" value="Account Settings" /> <input
					type="hidden" name="pid" value="${profile.id }" />
			</form>
		</c:if>
	</div>
	<div class="col-sm-2"></div>
	</div>
	<div class="footer">Powered by Unholy Desolation</div>
	</div>
</body>
</html>