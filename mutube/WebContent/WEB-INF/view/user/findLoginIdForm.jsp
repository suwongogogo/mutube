<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="/mutube/CSS/findForm.css" />
<script type="text/javascript" src="/mutube/JavaScript/findLoginId.js"></script>
</head>
<body>

	<div class="findForm-container" style="height: 260px">
		<a href="/mutube/Main.jsp"> <img src="/mutube/Image/logo.jpg"
			width="200" height="100" style="
				position: absolute;
				top: -103px;
				left: 90px;
			">
		</a>
		<form action="findLoginId" method="post" onsubmit="return false" id="form">
			<div class="findLoginId" class="input-container">
				<div class="input-container">
					<p>이름</p>
					<input type="text" name="name" class="find"
						placeholder="이름을 입력해주세요" id="name">
					<div class="underline"></div>
					<span class="error" id="name-error"><c:if test="${errors.name }">이름을 입력하세요.</c:if></span>
				</div>
				<div class="input-container">
					<p>E-mail</p>
					<input type="email" name="email" class="find"
						placeholder="이메일을 입력해주세요" id="email">
					<div class="underline"></div>
					<span class="error" id="email-error"><c:if test="${errors.email }">이메일를 입력하세요.</c:if></span>
				</div>
				<input type="submit" value="아이디 찾기" class="submit" onclick="move()">
                <button class="submit" onclick="moveMain()">취소</button>
			</div>
		</form>
	</div>
</body>
</html>