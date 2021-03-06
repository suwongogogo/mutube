<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/loginForm.css" />
<script type="text/javascript" src="/mutube/JavaScript/miscellaneous.js"></script>
</head>
<body>
	<div class="loginForm-container" style="height: 360px;">
		<a href="/mutube/Main.jsp"> <img src="/mutube/Image/logo.jpg"
			width="200" height="100">
		</a>
        <form action="update" method="post" onsubmit="return false;" id="form">
        <input type="hidden" name="userId" value="${loginUser.userId }">
            <div class="login" class="input-container">
                <div class="input-container">
                    <p>아이디</p>
                    <input type="text" name="loginId" class="signin" placeholder="아이디" value="${loginUser.loginId }" id="id">
                    <div class="underline"></div>
                    <span class="error" id="id-error"><c:if test="${errors.loginId }">아이디를 입력하세요.</c:if>
                    </span>
                </div>
                <div class="input-container">
                    <p>이름</p>
                    <input type="text" name="name" class="signin" placeholder="이름" value="${loginUser.name }" id="name"> 
                    <div class="underline"></div>
                    <span class="error" id="name-error"><c:if test="${errors.password }">비밀번호를 입력하세요.</c:if></span>
                </div>
                <div class="input-container">
                    <p>이메일</p>
                    <input type="email" name="email" class="signin" placeholder="이메일" value="${loginUser.email }" id="email"> 
                    <div class="underline"></div>
                    <span class="error" id="email-error"><c:if test="${errors.email }">이메일을 입력하세요.</c:if></span>
                </div>
                <input type="submit" value="수정" class="submit" onclick="move()">
                <button class="submit" onclick="moveMyPage()">취소</button>
            </div>
        </form>
    </div> 
</body>
</html>