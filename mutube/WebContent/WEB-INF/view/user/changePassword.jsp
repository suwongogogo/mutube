<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/changePasswordForm.css" />
<script type="text/javascript" src="/mutube/JavaScript/changePassword.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>비밀번호 변경</title>
</head>
<body>
	<%-- 비밀번호 공백 확인 및 현재 비밀번호 맞는지 확인 --%>
	<div class="loginForm-container">
        <form action="changePassword" method="post" onsubmit="return false;" id="form">
        <input type="hidden" name="userId" value="${loginUser.userId }">
            <div class="login" class="input-container">
                <div class="input-container">
                    <p>현재 비밀번호</p>
                    <input type="password" name="now_password" class="signin" placeholder="현재 비밀번호를 입력해주세요." id="old-password"> 
                    <div class="underline"></div>
                    <div class="widht">
	                    <span class="error" id="old-error"></span>
	                    <span class="error"><c:if test="${errors.passwordNotMatch }">비밀번호가 일치하지 않습니다.</c:if></span>
                    </div>
                    <p>새 비밀번호</p>
                    <input type="password" name="new_password" class="signin" placeholder="새 비밀번호를 입력해주세요." id="new-password"> 
                    <div class="underline"></div>
                    <div class="widht">
                    	<span class="error" id="new-error"></span>
                    </div>
                    <p>새 비밀번호 확인</p>
                    <input type="password" name="new_password_confirm" class="signin" placeholder="새 비밀번호 한번 더 입력해주세요." id="new-confirm-password"> 
                    <div class="underline"></div>
                    <div class="widht">
	                    <span class="error" id="re-new-error"></span>
	                    <c:if test="${errors.newPasswordNotMatch }">
	                    	<span class="error">비밀번호가 일치하지 않습니다.</span>
	                    </c:if>
                    </div>
                </div>
                <input type="submit" value="수정" class="submit" onclick="changePassword()">
              	<button class="submit" onclick="moveMain()">취소</button>
            </div>
        </form>
    </div> 
</body>
</html>