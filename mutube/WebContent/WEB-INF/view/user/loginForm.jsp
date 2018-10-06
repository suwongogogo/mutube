<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/loginForm.css" />
<script src="/mutube/CSS/loginForm.js"></script>
<title>로그인</title>
</head>
<body>
	<div class="logo">
		<a href="/mutube/Main.jsp"><img src="/mutube/Image/수원이얼굴.jpg"></a>
	</div>
	<div class="loginForm-container">
        <form action="login" method="post">
            <div class="login" class="input-container">
                <div class="input-container">
                    <p>아이디</p>
                    <input type="text" name="loginId" class="signin" placeholder="아이디" value="${param.loginId }">
                    <div class="underline"></div>
                    <span class="error"><c:if test="${errors.loginId }">아이디를 입력하세요.</c:if>
                    <c:if test="${errors.userNotFound }">존재하지 않는 유저입니다.</c:if>
                    <c:if test="${errors.passwordNotMatch }">아이디 또는 비밀번호가 올바르지 않습니다.</c:if>
                    </span>
                </div>
                <div class="input-container">
                    <p>비밀번호</p>
                    <input type="password" name="password" class="signin" placeholder="비밀번호"> 
                    <div class="underline"></div>
                    <span class="error"><c:if test="${errors.password }">비밀번호를 입력하세요.</c:if></span>
                </div>
                <div>
                    <a href="register" class="register">회원가입 하기</a> <br>
                </div>
                <input type="submit" value="로그인" class="submit">
            </div>
        </form>
    </div>  
</body>
</html>