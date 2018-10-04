<%@page import="User.Service.FindPasswordByEmail"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String email = request.getParameter("email");

	FindPasswordByEmail findPasswordByEmail =  FindPasswordByEmail.getInstance();
	String password = findPasswordByEmail.findPasswordByEmail(email);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	회원님의 비밀번호는 '<%= password %>' 입니다.
</body>
</html>