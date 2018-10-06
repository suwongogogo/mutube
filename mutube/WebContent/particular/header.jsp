<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/header.css" />
</head>
<body>
    <div class="middle">
        <!-- 헤더 -->
        <header class="header">
            <div class="search-container">
                <form action="post/search" method="get">
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