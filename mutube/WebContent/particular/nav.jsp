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
                <a href="/user/login">
                    <p class="loginout">로그인</p>
                </a>
                <ul class="information">
                    <li class="sub-font">
                        <a href="/user/register">회원가입</a>
                    </li>
                    <span class="sub-font"> / </span>
                    <li class="sub-font">
                        <a href="/user/findLoginId">아이디 찾기</a>
                    </li>
                    <span class="sub-font"> / </span>
                    <li class="sub-font">
                        <a href="/user/findPassword">비밀번호 찾기</a>
                    </li>
                </ul>
                </c:if>
                <c:if test="${loginUser!=null }">
                <a href="/user/logout">
                	<p class="loginout">로그아웃</p>
                </a>
                <ul class="logout-ul">
	                <li class="font">
	                	<span class="id">${loginUser.name }</span>님, 환영합니다. 
	                </li>
	                <li class="sub-font mypage" style="float: right;">
	                	<a href="/user/confirmUserByPassword" style="display: block;">마이페이지</a>
	                </li>
               	</ul>
               	</c:if>
            </div>
            <ul class="drop-ul">
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="/post/list">전체글</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <a class="droplist" href="/post/notice">공지</a>
                    </div>
                </li>
                <li class="drop-li">
                    <div class="clicked"></div>
                    <div class="list-container">
                        <span class="droplist" onclick="openTest(this,'장르')">장르</span>
                        <ul class="dropdown" id="hidden1">
                            <li>
                                <a href="#" class="title droplist">${jazz }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${blues }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${rock }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${ballad }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${pop }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${musical }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${orchestra }</a>
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
                                <a href="#" class="title droplist">${vocal }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${guitar }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${piano }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${violin }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${cello }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${trumpet }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${trombone }</a>
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
                                <a href="#" class="title droplist">${america }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${korea }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${japen }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${england }</a>
                            </li>
                            <li>
                                <a href="#" class="title droplist">${europe }</a>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</body>
</html>