<%@page import="User.Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User) request.getAttribute("user");
	request.setAttribute("user", user);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>마이페이지</title>
</head>
<body>
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" value="${loginUser.loginId }" readonly></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value="${loginUser.name }" readonly></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" value="${loginUser.email }" readonly></td>
		</tr>
	</table>
	<a href="updateUser?userId=${loginUser.userId }">회원 수정</a>
	<a href="changePassword?userId=${loginUser.userId }">비밀번호 수정</a>
	<a href="deleteUser?userId=${loginUser.userId }">회원 탈퇴</a>
</body>
</html>