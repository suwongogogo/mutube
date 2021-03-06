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
<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board-list">
		   		<div class="address">전체글</div> 
		   		<div class="board-list-container">
			  		<table>
						<tr class="line-bottom font18">
							<td class="table-title list-postid">번호</td>
							<td class="interval"></td>
							<td class="table-title list-title">제목</td>
							<td class="interval"></td>
							<td class="table-title list-author">작성자</td>
							<td class="interval"></td>
							<td class="table-title list-counter">조회수</td>
							<td class="interval"></td>
							<td class="table-title list-counter">작성 일자</td>
						</tr>
						<!-- 게시글이 없을 때 -->
						<c:if test="${!boardPage.hasPost()}">
							<tr>
								<td colspan="4">게시글이 없습니다.</td>
							</tr>
						</c:if>
						<!-- 게시글이 있을 때 -->
						<c:forEach var="board" items="${boardPage.boardList}">
							<tr class="list-tr">
								<td class="tc">${board.postId }</td>
								<td></td>
								<td class="pointer"><a href="view?no=${board.postId }&pageNum=${boardPage.currentPage}" class="title-container"><font size="2" color="darkgrey" >(장르:${post.genre}, 나라:${post.country}, 악기:${post.instrument})</font> ${post.title }</a></td>
								<td></td>
								<td class="tc">${board.writer.name }<br><font size="2" color="darkgrey" >(${board.writer.loginId })</font></td>
								<td></td>
								<td class="tc">${board.views}</td>
								<td></td>
								<td class="tc">${board.wdateStr}</td>
							</tr>
						</c:forEach>
						<tr class="line-top line-bottom write-tr">
							<td colspan="9" class="write">
								<a href="write">
									<button class="wrtie-button">게시글 쓰기</button>
								</a>
							</td>
						</tr>
						<!-- 게시글이 있다면 베이지 블럭도 표시 -->
						<c:if test="${boardPage.hasBoard()}">
							<tr class="tc">
								<td class="paging" colspan="9">
									<c:if test="${boardPage.startPage > 5}">
										<a href="board?pageNum=${boardPage.startPage-5 }"><span class="prev">이전</span></a>
									</c:if>
									<c:forEach var="pageNum" begin="${boardPage.startPage }" end="${boardPage.endPage }">
										<div class="inline pagination-border">
										<c:if test="${pageNum == param.pageNum}">
											<a href="board?pageNum=${pageNum }" class="pagination" style="color: blue;">${pageNum }</a>
										</c:if>
										<c:if test="${pageNum != param.pageNum}">
											<a href="board?pageNum=${pageNum }" class="pagination">${pageNum }</a>
										</c:if>
										</div>
									</c:forEach>
									<c:if test="${boardPage.endPage < boardPage.totalPages }">
										<a href="board?pageNum=${boardPage.startPage+5 }"><span class="next">다음</span></a>
									</c:if>
								</td>
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