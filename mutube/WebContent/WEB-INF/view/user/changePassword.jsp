<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/changePasswordForm.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
	<div class="loginForm-container">
        <form action="changePassword" method="post">
        <input type="hidden" name="userId" value="${loginUser.userId }">
            <div class="login" class="input-container">
                <div class="input-container">
                    <p>현재 비밀번호</p>
                    <input type="text" name="now_password" class="signin" placeholder="현재 비밀번호를 입력해주세요." value="${loginUser.password }"> 
                    <div class="underline"></div>
                    <span class="error"><c:if test="${errors.password }">현재 비밀번호를 입력하세요.</c:if></span>
                    <p>새 비밀번호</p>
                    <input type="text" name="new_password" class="signin" placeholder="새 비밀번호를 입력해주세요." > 
                    <div class="underline"></div>
                    <c:if test="${errors.new_password }">
                    	<span class="error">새 비밀번호를 입력하세요.</span>
                    </c:if>
                    <p>새 비밀번호 확인</p>
                    <input type="text" name="new_password_confirm" class="signin" placeholder="새 비밀번호 한번 더 입력해주세요."> 
                    <div class="underline"></div>
                    <c:if test="${errors.new_password_confirm }">
                    	<span class="error">새 비밀번호를 입력하세요.</span>
                    </c:if>
                    <c:if test="${errors.newPasswordNotMatch }">
                    	<span class="error">비밀번호가 일치하지 않습니다.</span>
                    </c:if>
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