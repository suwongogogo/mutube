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
	<c:if test="${errors.postList }">검색한 게시글을 찾을 수 없습니다.</c:if>
	<table>
		<tr>
			<td>게시글 번호</td>
			<td>제목</td>
			<td>작성자</td>
			<td>장르</td>
			<td>악기</td>
			<td>국가</td>
			<td>작성 일자</td>
		</tr>
		<c:forEach var="postList" items="${postList.postList }">
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
		<c:if test="${postPage.startPage > 5}">
			<a href="list?pageNum=${postPage.startPage-6 }"><span
				class="arrow">◀</span><span class="prev">이전</span></a>
		</c:if>
		<c:forEach var="postNum" begin="${postList.startPage }"
			end="${postList.endPage}">
			<a href="search?pageNum=${postNum }">${postNum }</a>
		</c:forEach>
		<c:if test="${postPage.endPage < postPage.total}">
			<a href="list?pageNum=${postPage.startPage-6 }"><span
				class="arrow">◀</span><span class="prev">이전</span></a>
		</c:if>
	</table>
</body>
</html>