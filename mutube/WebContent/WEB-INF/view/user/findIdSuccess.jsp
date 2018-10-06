<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="/mutube/CSS/findIdSuccess.css" />
</head>
<body>
	<c:set var="loginIdList" value="${loginIdList}" />
	회원님이 가지고 있는 ID
	<c:forEach var="loginIdList" items="${loginIdList }" begin="0" end="${fn:length(loginIdList) }" >
	아이디 : ${loginIdList.loginId }
	</c:forEach>
	<div class="findId-container">
        <P>회원님이 가지고 있는 ID</P>
        <p>아이디 : ${loginIdList.loginId }</p>
        <button class="check">
            <a href="login">확인</a>
        </button>
    </div>
	<!-- 회원님이 가지고 있는 ID : "아이디" -->
</body>
</html>