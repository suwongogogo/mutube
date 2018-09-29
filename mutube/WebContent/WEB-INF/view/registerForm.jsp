<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>회원가입</title>
    <link rel="stylesheet" type="text/css" media="screen" href="/CSS/registerForm.css" />
    <script src="JavaScript/registerForm.js"></script>
</head>
<body>
    <div class="register-container">
        <div class="register">
            <form action="register" method="post" onsubmit="return false" id="form">
                <p>아이디</p>
                <input type="text" name="loginId" class="signup" placeholder="아이디" value="${param.loginId }"> <span class="error"></span>
                <c:if test="${errors.loginId }">아이디를 입력하세요</c:if>
                <p>이름</p>
                <input type="text" name="name" class="signup" placeholder="이름" value="${param.name }"> <span class="error"></span>
                <c:if test="${errors.name }">이름을 입력하세요</c:if>
                <p>비밀번호</p>
                <input type="password" name="password" class="signup" placeholder="비밀번호" > <span class="error"></span>
                <c:if test="${errors.password }">비밀번호를 입력하세요</c:if>
                <p>비밀번호 확인</p>
                <input type="password" name="confirmPassword" class="signup" placeholder="비밀번호 확인"> <span class="error"></span>
                <c:if test="${errors.confirmPassword }">확인 비밀번호를 입력하세요</c:if>
                <c:if test="${errors.notMatch }">확인 비밀번호가 일치하지 않습니다.</c:if>
                <p>이메일</p>
                <input type="text" name="email" class="signup" placeholder="이메일" value="${param.email }"> <span class="error"></span>
                <c:if test="${errors.email }">이메일을 입력하세요</c:if><br>
                
                
                <input type="submit" value="회원가입">
            </form>
        </div>
    </div>
</body>
</html>