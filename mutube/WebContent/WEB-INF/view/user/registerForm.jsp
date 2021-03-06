<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>회원가입</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="/mutube/CSS/registerForm.css" />
</head>
<body>
	<div class="register-container">
	<a href="/mutube/Main.jsp"> <img src="/mutube/Image/logo.jpg"
		width="200" height="100" style="position: absolute; top: -102px; right: 85px;">
	</a>
		<div class="register">
			<form action="register" method="post">
				<div class="form-contents">
					<div class="input-container">
						<p>아이디</p>
						<input type="text" name="loginId" class="signup" placeholder="아이디" value="${param.loginId }">
						<div class="underline"></div>
						<span class="error"><c:if test="${errors.loginId }">아이디를 입력하세요.</c:if>
							<c:if test="${errors.userExist }">이미 존재하는 아이디입니다.</c:if></span>
					</div>
					<div class="input-container">
						<p>이름</p>
						<input type="text" name="name" class="signup" placeholder="이름" value="${param.name }">
						<div class="underline"></div>
						<span class="error"><c:if test="${errors.name }">이름을 입력하세요.</c:if></span>
					</div>
					<div class="input-container">
						<p>비밀번호</p>
						<input type="password" name="password" class="signup"
							placeholder="비밀번호" value="${param.password }">
						<div class="underline"></div>
						<span class="error"><c:if test="${errors.password }">비밀번호를 입력하세요.</c:if></span>
					</div>
					<div class="input-container">
						<p>비밀번호 확인</p>
						<input type="password" name="confirmPassword" class="signup"
							placeholder="비밀번호 확인" value="${param.confirmPassword }">
						<div class="underline"></div>
						<span class="error"><c:if test="${errors.confirmPassword }">확인 비밀번호를 입력하세요.</c:if>
							<c:if test="${errors.notMatch }">확인 비밀번호가 일치하지 않습니다.</c:if></span>
					</div>
					<div class="input-container">
						<p>이메일</p>
						<input type="text" name="email" class="signup" placeholder="이메일" value="${param.email }">
						<div class="underline"></div>
						<span class="error">
							<c:choose>
								<c:when test="${errors.email }">이메일을 입력하세요.</c:when>
								<c:when test="${errors.emailForm }">이메일 형식을 지켜주세요.</c:when>
							</c:choose>
						</span>
						<br>
					</div>
					<div>
						<a href="login" class="login">로그인 하기</a>
					</div>
					<input type="submit" value="회원가입" class="submit">
				</div>
			</form>
			<a href="/mutube/Main.jsp">
				<button class="submit">취소</button>
			</a>
		</div>
	</div>
</body>
</html>