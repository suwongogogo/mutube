<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String userPassword = (String) request.getAttribute("userPassword");
%>
<jsp:forward page="/WEB-INF/view/passwordConfirmForm.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
이메일 전송 완료
	<script>
		setTimeout(function(){
			alert("이메일 전송을 완료하였습니다.");
			location.href = "/mutube/Main.jsp";
		}, 2000);
	</script>
</body>
</html>