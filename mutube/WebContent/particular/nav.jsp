<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/nav.css" />
    <script src="/mutube/JavaScript/nav.js"></script>
</head>
<body>
    <div class="nav">
        <div class="left-padding">
            <div class="user-container">
                <c:if test="${loginUser==null }">
                <a href="login">
                    <p class="loginout">로그인</p>
                </a>
                <ul class="information">
                    <li class="sub-font">
                        <a href="register">회원가입</a>
                    </li>
                    <span class="sub-font"> / </span>
                    <li class="sub-font">
                        <a href="findLoginId">아이디 찾기</a>
                    </li>
                    <span class="sub-font"> / </span>
                    <li class="sub-font">
                        <a href="findPassword">비밀번호 찾기</a>
                    </li>
                </ul>
                </c:if>
                <c:if test="${loginUser!=null }">
                <a href="logout">
                	<p class="loginout">로그아웃</p>
                </a>
                <ul class="logout-ul">
	                <li class="font">
	                	<span class="id">${loginUser.name }</span>님, 환영합니다. 
	                </li>
	                <li class="sub-font mypage" style="float: right;">
	                	<a href="#" style="display: block;">마이페이지</a>
	                </li>
               	</ul>
               	</c:if>
            </div>
            <ul class="drop-ul">
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="list">전체글</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="notice">공지</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <span class="droplist" onclick="openTest(this,'장르')">장르</span>
                        <ul class="dropdown" id="hidden1">
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <span class="droplist" onclick="openTest(this,'악기')">악기</span>
                        <ul class="dropdown" id="hidden1">
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                        </ul>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <span class="droplist" onclick="openTest(this,'뮤지션')">뮤지션</span>
                        <ul class="dropdown" id="hidden1">
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">asd</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>