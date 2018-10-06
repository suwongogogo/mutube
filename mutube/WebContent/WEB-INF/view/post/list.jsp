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
			<td colspan="4"><a href="write">[게시글 쓰기]</a></td>
		</tr>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
		</tr>
	<!-- 게시글이 없을 때 -->
	<c:if test="${!postPage.hasPost()}">
		<tr>
			<td colspan="4">게시글이 없습니다.</td>
		</tr>
	</c:if>
	
	<!-- 게시글이 있을 때 -->
	<c:forEach var="post" items="${postPage.postList}">
		<tr>
			<td>${post.postId }</td>
			<td><a href="viewPost?no=${post.postId }&pageNum=${postPage.currentPage}">${post.title }</a></td>
			<td>${post.writer.name }</td>
			<td>${post.views}</td>
		</tr>
	</c:forEach>

	<!-- 게시글이 있다면 베이지 블럭도 표시 -->
	<c:if test="${postPage.hasPost()}">
		<tr>
			<td colspan="4">
				<c:if test="${postPage.startPage > 5}">
					<a href="list?pageNum=${postPage.startPage-5 }">[이전]</a>
				</c:if>
				<c:forEach var="pNum" begin="${postPage.startPage }" end="${postPage.endPage }">
					<a href="list?pageNum=${pNum }">[${pNum }]</a>
				</c:forEach>
				<c:if test="${postPage.endPage < postPage.totalPages }">
					<a href="list?pageNum=${postPage.startPage+5 }">[다음]</a>
				</c:if>
			</td>
		</tr>
	</c:if>
	</table>
</body>
</html>