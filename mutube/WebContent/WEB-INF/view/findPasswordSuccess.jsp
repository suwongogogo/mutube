<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String password = (String)request.getAttribute("userPassword");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="findId-container">
        <P>회원님이 가지고 있는 ID</P>
        <p>아이디 : ${loginIdList.loginId }</p>
        <button class="check">
            <a href="login">확인</a>
        </button>
    </div>
	<c:if test="<%= password != null %>">
		<script>
			setTimeout(function() {
				alert("이메일 전송을 완료하였습니다.");
				location.href = "/mutube/Main.jsp";
			}, 2000);
		</script>
	</c:if>
	<c:if test="<%= password == null || password.isEmpty() %>">
		<script>
			setTimeout(function() {
				alert("이메일 전송을 실패하였습니다.");
				location.href = "/mutube/Main.jsp";
			}, 2000);
		</script>
	</c:if>
</body>
</html>