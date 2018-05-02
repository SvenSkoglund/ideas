<!DOCTYPE html>
<html lang="en">
<!--class full means-->

<head>
<title>loggedInNavBar</title>

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

<link rel="stylesheet" type="text/css" href="CSS/ideas.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">

<!--Check for CDN if false load local dist-->

<!--fonts-->
<link
	href="https://fonts.googleapis.com/css?family=Crimson+Text|Raleway"
	rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<header class="main-header">
		<div class="row navbarcolor">
			<div class="col-sm-4 ideastext"><a href="index.do"><h1 class="linkcolor">ideas.</h1></a>
			</div>
			<div class="col-sm-5 navtext"></div>
			<div class="col-sm-1 navtext"><a href="toPostIdea.do"><h4 class="linkcolor">Create New Idea</h4></a>
			
			</div>
			<div class="col-sm-2 navtext">
			<div class="dropdown">
			<img alt="Picture of ${loggedInUser.username }" src="${loggedInUser.profile.profilePic }" class="imgsizeNav">
				  <div class="dropdown-content">
				    <a href="toProfile.do?pid=${loggedInUser.profile.id }">My Profile</a>
				    <a href="toSettings.do?pid=${loggedInUser.profile.id }">Profile Settings</a>
				    <a href="logout.do">Logout</a>
				  </div>
				</div>
		
			</div>
			
		</div>
		</header>
	</div>
</body>
</html>