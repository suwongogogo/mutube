<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<c:if test="${ errors.keyword}">
				<div class="search-in-div">
					<input type="text" name="keyword" class="search-real-input" placeholder="검색어를 입력하세요"><button class="search-submit"></button>
				</div>
			</c:if>
			<c:if test="${ !errors.keyword}">
				<div class="search-in-div">
					<input type="text" name="keyword" class="search-real-input" placeholder="${params.keyword }"><button class="search-submit"></button>
				</div>
			</c:if>
			
			</form>
		</div>
	</div>
</body>
</html>