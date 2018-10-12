<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/header.css" />
</head>
<body>
	<div class="custom-header-container">
		<a href="/mutube/Main.jsp">
			<img src="/mutube/Image/logo.jpg" width="200px" height="100px" class="header-image">
		</a>
		<div class="search-form-container">
			<form action="/mutube/post/search" method="get" class="search-real-form">
				<div class="search-in-div">
					<input type="text" name="keyward" class="search-real-input"><button class="search-submit"></button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>