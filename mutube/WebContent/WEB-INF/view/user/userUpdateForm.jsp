<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
<link rel="stylesheet" type="text/css" href="CSS/loginForm.css" />
</head>
<body>
	<div class="loginForm-container" style="height: 360px;">
        <form action="update" method="post">
        <form action="update" method="post" onsubmit="return false;" id="form">
        <input type="hidden" name="userId" value="${loginUser.userId }">
            <div class="login" class="input-container">
                <div class="input-container">
                    <p>아이디</p>
                    <input type="text" name="loginId" class="signin" placeholder="아이디" value="${loginUser.loginId }">
                    <div class="underline"></div>
                    <span class="error"><c:if test="${errors.loginId }">아이디를 입력하세요.</c:if>
                    </span>
                </div>
                <div class="input-container">
                    <p>이름</p>
                    <input type="text" name="name" class="signin" placeholder="이름" value="${loginUser.name }"> 
                    <div class="underline"></div>
                    <span class="error"><c:if test="${errors.password }">비밀번호를 입력하세요.</c:if></span>
                </div>
                <div class="input-container">
                    <p>이메일</p>
                    <input type="email" name="email" class="signin" placeholder="이메일" value="${loginUser.email }"> 
                    <div class="underline"></div>
                    <span class="error"><c:if test="${errors.password }">이메일을 입력하세요.</c:if></span>
                </div>
                <input type="submit" value="수정" class="submit">
                <a href="/mutube/myPage.jsp">
                	<button class="submit">취소</button>
                </a>
            </div>
        </form>
    </div> 
</body>
</html>