<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 수정</title>
</head>
<body>
	<form action="update" method="post">
		<table>
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" value="${user.getId }" ></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="text" name="id" value="${user.getPassword }"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="id" value="${user.getName }"></td>
			</tr>
			<tr>
				<td>이메일</td>
				<td><input type="text" name="id" value="${user.getEmail }"></td>
			</tr>
			<tr>
				<td><input type="submit" value="수정"></td>
				<td><input type="button" value="취소"></td>
			</tr>
		</table>
	</form>
</body>
</html>