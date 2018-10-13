<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/header.css" />
    <script type="text/javascript" src="/mutube/JavaScript/header.js"></script>
</head>
<body>
	<div class="custom-header-container">
		<a href="/mutube/Main.jsp">
			<img src="/mutube/Image/logo.jpg" width="200px" height="100px" class="header-image">
		</a>
		<div class="search-form-container">
			<form action="/mutube/post/search" method="get" class="search-real-form" id="search-form" onsubmit="return false;">
				<div class="search-in-div">
					<input type="text" name="keyword" class="search-real-input" id="search-result" value="${param.keyword }"><button class="search-submit" onclick="search()"></button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>