<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="changePassword" method="post">
		<table>
			<input type="hidden" name="loginId" value="${user.loginId }">
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="password" value="${user.password }"></td>
			</tr>
			<tr>
				<td><input type="submit" value="수정"></td>
				<td>취소</td>
			</tr>
		</table>
	</form>
</body>
</html>