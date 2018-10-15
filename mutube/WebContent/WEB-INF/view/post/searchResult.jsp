<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="/mutube/CSS/searchResult.css" />
<title>전체글</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
		<div class="center">
			<jsp:include page="/particular/nav.jsp"></jsp:include>
			<div class="board-list">
				<c:if test="${param.category == null }">
					<div class="address">
						검색 결과<br>
						<font size="2" color="darkgray">(${param.keyword }에 대한 결과 입니다.)</font>
					</div>
				</c:if>
				<c:if test="${param.category == 'country' }">
					<div class="address">${param.keyword } 글 목록</div>
				</c:if>
				<c:if test="${param.category == 'genre' }">
					<div class="address">${param.keyword } 글 목록</div>
				</c:if>
				<c:if test="${param.category == 'instrument' }">
					<div class="address">${param.keyword } 글 목록</div>
				</c:if>
				<div class="board-list-container">
					<table>
						<tr class="line-bottom font18">
							<td class="table-title list-postid">번호</td>
							<td class="interval"></td>
							<td class="table-title list-title">제목</td>
							<td class="interval"></td>
							<td class="table-title list-public">작성자</td>
							<td class="interval"></td>
							<td class="table-title list-public">장르</td>
							<td class="interval"></td>
							<td class="table-title list-public">악기</td>
							<td class="interval"></td>
							<td class="table-title list-public">국가</td>
							<td class="interval"></td>
							<td class="table-title list-date">작성 일자</td>
						</tr>
						<c:if test="${!postPage.hasPost() }">
							<tr>
								<td colspan="13">검색한 게시글을 찾을 수 없습니다.</td>
							</tr>
						</c:if>
						<c:forEach var="post" items="${postPage.postList }">
							<tr class="user-board">
								<td class="tc height">${post.postId }</td>
								<td></td>
								<td class="pointer height"><a
									href="view?no=${post.postId }&pageNum=${postPage.currentPage}" class="title-container">${post.title }</a></td>
								<td></td>
								<td class="tc height">${post.writer.name }</td>
								<td></td>
								<td class="tc height">${post.genre }</td>
								<td></td>
								<td class="tc height">${post.instrument }</td>
								<td></td>
								<td class="tc height">${post.country }</td>
								<td></td>
								<td class="tc height">${post.wdateStr }</td>
							</tr>
						</c:forEach>
						<c:if test="${postPage.hasPost() }">
							<tr class="tc">
								<td colspan="14" class="line-top"><c:if
										test="${postPage.startPage > 5}">
										<a href="/mutube/post/search?keyword=${param.keyword }&pageNum=${postPage.startPage-5 }&category=${param.category}">
										<span class="prev">이전</span></a>
									</c:if> <c:forEach var="pageNum" begin="${postPage.startPage }"
										end="${postPage.endPage }">
										<div class="inline pagination-border">
											<c:if test="${pageNum == param.pageNum }">
												<a href="/mutube/post/search?keyword=${param.keyword }&pageNum=${pageNum }&category=${param.category}"
													style="color: blue;" class="pagenation">${pageNum }</a>
											</c:if>
											<c:if test="${pageNum != param.pageNum }">
												<a href="/mutube/post/search?keyword=${param.keyword }&pageNum=${pageNum }&category=${param.category}"
													class="pagenation">${pageNum }</a>
											</c:if>
										</div>
									</c:forEach> <c:if test="${postPage.endPage > postPage.total }">
										<a href="/mutube/post/search?keyword=${param.keyword }&pageNum=${postPage.startPage+5 }&category=${param.category}"><span
											class="next">다음</span></a>
									</c:if></td>
							</tr>
						</c:if>
					</table>
				</div>
			</div>
		</div>
		<jsp:include page="/particular/footer.jsp"></jsp:include>
	</div>
</body>
</html>