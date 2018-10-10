<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>비밀번호 변경</title>
	<link rel="stylesheet" type="text/css" media="screen" href="/mutube/CSS/confirmUserByPassword.css" />
</head>
<body>
	<div class="confirm-password-container">
        <form action="confirmUserByPassword" method="post">
            <div class="login" class="input-container">
                <div class="input-container">
                    <p>비밀번호</p>
                    <input type="password" name="password" class="password-input" placeholder="비밀번호를 입력하세요">
                    <%-- <span class="error"><c:if test="${errors.password }">비밀번호를 입력하세요.</c:if></span> --%>
                </div>
                <input type="submit" value="확인" class="submit">
            </div>
        </form>
    </div>
</body>
</html>