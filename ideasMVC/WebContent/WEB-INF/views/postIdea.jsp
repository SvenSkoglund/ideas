<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>new idea.</title>
</head>
<body>

	<!-- Leading navigation bar to return home -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light"> <a class="navbar-brand" href="index.do">Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	</nav>
	
	
	<!-- I know that this page has to have a form or sorts to fill out and submit/post but I feel a little over my head and need walk through it with some help -->
<!-- Posting Idea form -->
	<form action="postIdea.do" method="POST">
		<input type="Text" placeholder="Title/Heading" name="name" /><br/>
	
	<!-- Stylesheet to make text box in a box form using divs to style? -->
	

		
		<input class="" type="Text" placeholder="idea" name="content" />
		
		<input class="" type="hidden" value="${profile }" name="profile" />
	

	
	<!-- I feel like the idea should get posted before people can start commenting. do we need another jsp? -->

		<input type="Submit" value="Post Idea" />
	</form>

</body>
</html>