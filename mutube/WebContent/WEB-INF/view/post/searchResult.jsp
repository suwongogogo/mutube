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
	<c:if test="${errors.postList }">검색한 게시글을 찾을 수 없습니다.</c:if>
	<table>
		<c:forEach var="postList" items="${postList }">
			<tr>
				<td>${postList.postId }</td>
				<td>${postList.title }</td>
				<td>${postList.writer.name }</td>
				<td>${postList.genre }</td>
				<td>${postList.instrument }</td>
				<td>${postList.country }</td>
				<td>${postList.write_date }</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>