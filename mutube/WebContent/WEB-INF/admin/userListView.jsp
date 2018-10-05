<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th>회원 정보</th>
		</tr>
		<tr>
			<td>UserId</td>
			<td>LoginId</td>
			<td>password</td>
			<td>email</td>
			<td>name</td>
			<td>register_date</td>
			<td>authority</td>
		</tr>
		<c:forEach var="user" items="${pageINF.userList }">
			<tr>
				<td>user.userId</td>
				<td>user.loginId</td>
				<td>user.password</td>
				<td>user.email</td>
				<td>user.name</td>
				<td>user.register_date</td>
				<td>user.authority</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>