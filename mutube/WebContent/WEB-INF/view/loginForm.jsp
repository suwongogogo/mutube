<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/loginForm.css" />
<script src="CSS/loginForm.js"></script>
<title>로그인</title>
</head>
<body>
	<div class="loginForm-container">
        <form action="#" method="post" onsubmit="return false">
            <p>아이디</p>
            <input type="text" name="loginId" class="signin" placeholder="아이디"> <span class="error"></span>
            <p>비밀번호</p>
            <input type="password" name="password" class="signin" placeholder="비밀번호"> <span class="error"></span> <br>
            <a href="loginForm.html" class="register">회원가입 하기</a> <br>
            <input onclick="check()" type="submit" value="회원가입" class="submit">
        </form>
    </div>  
</body>
</html>