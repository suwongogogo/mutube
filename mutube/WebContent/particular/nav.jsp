<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/nav.css" />
</head>
<body>
    <div class="nav">
        <div class="left-padding">
            <div class="user-container">
                <c:if test="${loginUser==null }">
                <a href="/mutube/user/login">
                    <p class="loginout">로그인</p>
                </a>
                <ul class="information">
                    <li class="sub-font">
                        <a href="user/register">회원가입</a>
                    </li>
                    <span class="sub-font"> / </span>
                    <li class="sub-font">
                        <a href="user/findLoginId">아이디 찾기</a>
                    </li>
                    <span class="sub-font"> / </span>
                    <li class="sub-font">
                        <a href="user/findPassword">비밀번호 찾기</a>
                    </li>
                </ul>
                </c:if>
                <c:if test="${loginUser!=null }">
                <a href="/mutube/user/logout">
                	<p class="loginout">로그아웃</p>
                </a>
                <ul class="logout-ul">
	                <li class="font">
	                	<span class="id">${loginUser.name }</span>님, 환영합니다. 
	                </li>
	                <li class="sub-font mypage" style="float: right;">
	                	<a href="/mutube/user/confirmUserByPassword" style="display: block;">마이페이지</a>
	                </li>
               	</ul>
               	</c:if>
            </div>
            <ul class="drop-ul">
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="/mutube/post/list">전체글</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="/mutube/notice/notice">공지</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="/mutube/genre.jsp">장르</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="/mutube/instrument.jsp">악기</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="/mutube/country.jsp">국가</a>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>