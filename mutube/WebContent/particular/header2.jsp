<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/header2.css" />
</head>
<body>
    <div class="middle">
        <header class="header">
        	<div class="logo">
        		<a href="/mutube/Main.jsp">
        			<img src="/mutube/Image/logo.jpg" width="200" height="100">
        		</a>
        	</div>
            <div class="search-container">
                <form action="/mutube/post/search" method="get">
                    <div class="">
                        <input class="search" type="text" name="keyword">
                        <button class="search-button">검색</button>
                    </div>
                </form>
            </div>
        </header>
    </div>
</body>
</html>