<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>우리의 프로젝트</title>
    <link rel="stylesheet" type="text/css" href="CSS/main.css" />
    <script src="JavaScript/main.js"></script>
</head>
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div class="wrapper">
        <!-- 헤더 -->
        <header class="header">
            <div class="search-container">
                <form action="#" method="get">
                    <div class="">
                        <input class="search" type="text" name="keyword">
                        <button class="search-button">검색</button>
                    </div>
                </form>
            </div>
        </header>
        <!-- 회원가입 -->
        <div class="middle">
            <div class="nav">
                <div class="user-container">
                    <a href="login">
                        <p class="login">로그인</p>
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
                    <%--
                    <a href="login">
                        <p class="logout">로그아웃</p>
                    </a>
                    <div class="login-container">
                        <p class="welcome font14">
                            <span class="name">진국</span><span>님, 환영합니다.</span>
                        </p>
                        <a href="/myPage.jsp?userId=${userId}" class="myPage font14">마이페이지</a>
                    </div>
                     --%>
                </div>
                <!-- 보기 -->
                <div class="genre-container">
                    <ul class="genre-ul">
                        <li class="genre-li">
                            <span>·</span>
                            <a class="genre" href="list">전체글</a>
                        </li>
                        <li class="genre-li">
                            <span>·</span>
                            <a class="genre" href="notice">공지</a>
                        </li>
                        <!-- dropdown -->
                        <li class="genre-li">
                            <span>·</span> <span onclick="view1()">장르</span>
                            <ul class="dropdown" id="hidden1">
                                <li>
                                    <a href="#">asd</a>
                                </li>
                                <li>
                                    <a href="#">asd</a>
                                </li>
                                <li>
                                    <a href="#">asd</a>
                                </li>
                            </ul>
                        </li>
                        <li class="genre-li">
                            <span>·</span> <span onclick="view2()">악기</span>
                            <ul class="dropdown" id="hidden2">
                                <li>
                                    <a href="#">asd</a>
                                </li>
                                <li>
                                    <a href="#">asd</a>
                                </li>
                                <li>
                                    <a href="#">asd</a>
                                </li>
                            </ul>
                        </li>
                        <li class="genre-li">
                            <span>·</span> <span onclick="view3()">뮤지션</span>
                            <ul class="dropdown" id="hidden3">
                                <li>
                                    <a href="#">asd</a>
                                </li>
                                <li>
                                    <a href="#">asd</a>
                                </li>
                                <li>
                                    <a href="#">asd</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="board"></div>
        </div>
    </div>
    <footer class="footer">
        <div class="footer-container">
            <p class="footer-text">People Who Made Project</p>
            <p class="people">박진국, 이수원, 김승용, 김영진</p>
            <p class="phone">불만있는 새끼 전화 걸어라 010-5543-1787</p>
        </div>
    </footer>
</body>
</html>