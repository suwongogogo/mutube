<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>비밀번호 찾기</title>
    <link rel="stylesheet" type="text/css" media="screen" href="CSS/findForm.css" />
</head>
<body>
	<div class="logo">
		<a href="mutube/Main.jsp"><img src="/mutube/Image/수원이얼굴.jpg"></a>
	</div>
	<div class="findForm-container" style="height: 305px">
            <form action="findPassword" method="post">
                <div class="findForm" class="input-container">
                    <div class="input-container">
                        <p>ID</p>
                        <input type="text" name="loginId" class="find" placeholder="아이디를 입력해주세요">
                        <div class="underline"></div>
                        <span class="error"></span>
                    </div>
                    <div class="input-container">
                        <p>이름</p>
                        <input type="text" name="name" class="find" placeholder="이름을 입력해주세요">
                        <div class="underline"></div>
                        <span class="error"></span>
                    </div>
                    <div class="input-container">
                        <p>E-mail</p>
                        <input type="text" name="email" class="find" placeholder="이메일을 입력해주세요"> 
                        <div class="underline"></div>
                        <span class="error"></span>
                    </div>
                    <input type="submit" value="비밀번호 찾기" class="submit">
                </div>
            </form>
        </div>
</body>
</html>