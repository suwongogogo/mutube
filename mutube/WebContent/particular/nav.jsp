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
                <a href="user/login">
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
                <a href="user/logout">
                	<p class="loginout">로그아웃</p>
                </a>
                <ul class="logout-ul">
	                <li class="font">
	                	<span class="id">${loginUser.name }</span>님, 환영합니다. 
	                </li>
	                <li class="sub-font mypage" style="float: right;">
	                	<a href="user/confirmUserByPassword" style="display: block;">마이페이지</a>
	                </li>
               	</ul>
               	</c:if>
            </div>
            <ul class="drop-ul">
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="post/list">전체글</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="post/notice">공지</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <span class="droplist" onclick="openTest(this,'장르')">장르</span>
                        <ul class="dropdown" id="hidden1">
                            <li>
                                <a href="/mutube/post/searchgerne=jazz" class="title droplist">Jazz</a>
                            </li>
                            <li>
                                <a href="/mutube/post/searchgerne=blues" class="title droplist">Blues</a>
                            </li>
                            <li>
                                <a href="/mutube/post/searchgerne=rock" class="title droplist">Rock</a>
                            </li>
                            <li>
                                <a href="/mutube/post/searchgerne=ballad" class="title droplist">Ballad</a>
                            </li>
                            <li>
                                <a href="/mutube/post/searchgerne=pop" class="title droplist">POP</a>
                            </li>
                            <li>
                                <a href="/mutube/post/searchgerne=musical" class="title droplist">Musical</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?gerne=orchestra" class="title droplist">Orchestra</a>
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
                                <a href="/mutube/post/search?instrument=vocal" class="title droplist">Vocal</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?instrument=guitar" class="title droplist">Guitar</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?instrument=piano" class="title droplist">Piano</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?instrument=violin" class="title droplist">Violin</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?instrument=cello" class="title droplist">Cello</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?instrument=trumpet" class="title droplist">Trumpet</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?instrument=trombone" class="title droplist">Trombone</a>
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
                                <a href="/mutube/post/search?country=america" class="title droplist">America </a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?country=korea" class="title droplist">Korea</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?country=japen" class="title droplist">Japen</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?country=england" class="title droplist">England</a>
                            </li>
                            <li>
                                <a href="/mutube/post/search?country=europe" class="title droplist">Europe</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>