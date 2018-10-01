<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/registerForm.css" />
    <script src="/mutube/JavaScript/registerForm.js"></script>
</head>
<body>
    <div class="register-container">
        <div class="register">
            <form action="#" method="post" onsubmit="return false">
                <div class="form-contents">
                    <div class="input-container">
                        <p>아이디</p>
                        <input type="text" name="loginId" class="signup" placeholder="아이디">
                        <div class="underline"></div>
                        <span class="error"></span>
                    </div>
                    <div class="input-container">
                        <p>이름</p>
                        <input type="text" name="name" class="signup" placeholder="이름">
                        <div class="underline"></div>
                        <span class="error"></span>
                    </div>
                    <div class="input-container">
                        <p>비밀번호</p>
                        <input type="password" name="password" class="signup" placeholder="비밀번호">
                        <div class="underline"></div>
                        <span class="error"></span>
                    </div>
                    <div class="input-container">
                        <p>비밀번호 확인</p>
                        <input type="password" name="confirmPassword" class="signup" placeholder="비밀번호 확인">
                        <div class="underline"></div>
                        <span class="error"></span>
                    </div>
                    <div class="input-container">
                        <p>이메일</p>
                        <input type="text" name="email" class="signup" placeholder="이메일">
                        <div class="underline"></div>
                        <span class="error"></span> <br> 
                    </div>
                    <div>
                        <a href="loginForm.jsp" class="login">로그인 하기</a>
                    </div>
                    <input onclick="check()" type="submit" value="회원가입" class="submit">
                </div>
            </form>
        </div>
    </div>
</body>
</html>