<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>비밀번호 확인</title>
	<link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/confirmUserByPassword.css" />
	<script type="text/javascript" src="/mutube/JavaScript/confirmUserByPassword.js"></script>
</head>

<body>
	<div class="confirm-password-container">
	<a href="/mutube/Main.jsp"> <img src="/mutube/Image/logo.jpg"
		width="200" height="100" style="position: absolute;top: -103px;left: 85px;">
	</a>
        <form action="/mutube/user/deleteUserComfirmByPassword" onsubmit="return false" method="post" id="confirm-password-form">
            <div class="login" class="input-container">
                <div class="input-container">
                    <p>확인 비밀번호</p>
                    <input type="hidden" name="userId" value="${loginUser.userId }">
                    <input type="password" name="comfirmPassword" class="password-input" placeholder="탈퇴하시려면 비밀번호를 입력하세요" id="password">
                    <div class="underline"></div>
                    <%--비밀번호 틀리는지 검사 비밀번호 빈칸은 javascript에서 확인함. --%>
                    <%-- <span class="error"><c:if test="${errors.password }">비밀번호가 틀렸습니다.</c:if></span> --%>
                </div>
                <input type="submit" value="확인" class="submit" onclick="move()">
                <a href="/mutube/Main.jsp">
                	<button class="submit" onclick="moveMain()">취소</button>
                </a>
            </div>
        </form>
    </div>
</body>
</html>