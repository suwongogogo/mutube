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
			<th colspan="7">회원 정보</th>
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
		<c:forEach var="user" items="${userpageINF.userList }">
			<tr>
				<td>${user.userId }</td>
				<td>${user.loginId }</td>
				<td>${user.password }</td>
				<td>${user.email }</td>
				<td>${user.name }</td>
				<td>${user.register_date }</td>
				<td>${user.authority }</td>
			</tr>
		</c:forEach>
		<c:if test="${userpageINF.hasUser() }">
			<tr>
				<td colspan="7">
					<a href="userManagement?pageNum=1">[처음으로]</a>
					<c:if test="${pageINF.startPage > 6 }">
						<a href="userManagement?pageNum=${userpageINF.startPage - 6 }">[이전으로]</a>
					</c:if>
					<c:forEach var="pageNum" begin="${userpageINF.startPage }" end="${userpageINF.endPage }">
						<a href="userManagement?pageNum=${pageNum }">[${pageNum }]</a>
					</c:forEach>
					<c:if test="${pageINF.endPage < pageINF.totalPage }">
						<a href="userManagement?pageNum=${userpageINF.startPage + 6 }">[다음]</a>
					</c:if> 
					<a href="userManagement?pageNum=${userpageINF.totalPage }">[마지막으로]</a>
				</td>
			</tr>
		</c:if>
	</table>
</body>
</html>