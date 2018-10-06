<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="12">회원 정보</th>
		</tr>
		<tr>
			<td>postId</td>
			<td>userId</td>
			<td>name</td>
			<td>email</td>
			<td>title</td>
			<td>genre</td>
			<td>country</td>
			<td>instrument</td>
			<td>write_date</td>
			<td>update_date</td>
			<td>views</td>
			<td>able</td>
		</tr>
		<c:forEach var="post" items="${postPageINF.postList }">
			<tr>
				<td>${post.postId }</td>
				<td>${post.userId }</td>
				<td>${post.name }</td>
				<td>${post.title }</td>
				<td>${post.genre }</td>
				<td>${post.country }</td>
				<td>${post.instrument }</td>
				<td>${post.write_date }</td>
				<td>${post.update_date }</td>
				<td>${post.views }</td>
				<td>${post.able }</td>
			</tr>
		</c:forEach>
		<c:if test="${postPageINF.hasUser() }">
			<tr>
				<td colspan="7">
					<a href="userManagement?pageNum=1">[처음으로]</a>
					<c:if test="${postPageINF.startPage > 6 }">
						<a href="userManagement?pageNum=${postPageINF.startPage - 6 }">[이전으로]</a>
					</c:if>
					<c:forEach var="pageNum" begin="${postPageINF.startPage }" end="${postPageINF.endPage }">
						<a href="userManagement?pageNum=${pageNum }">[${pageNum }]</a>
					</c:forEach>
					<c:if test="${postPageINF.endPage < postPageINF.totalPage }">
						<a href="userManagement?pageNum=${postPageINF.startPage + 6 }">[다음]</a>
					</c:if> 
					<a href="userManagement?pageNum=${postPageINF.totalPage }">[마지막으로]</a>
				</td>
			</tr>
		</c:if>
	</table>
</body>
</html>