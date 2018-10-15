<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/noticeList.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 보기</title>
</head>
<body>
	<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board-list">
		   		<div class="address">공지 사항</div> 
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
						<c:if test="${!noticePage.hasNotice()}">
							<tr>
								<td colspan="4">게시글이 없습니다.</td>
							</tr>
						</c:if>
						
						<!-- 게시글이 있을 때 -->
						<c:forEach var="notice" items="${noticePage.noticeList}">
							<tr class="list-tr">
								<td class="tc">${notice.noticeId }</td>
								<td></td>
								<td class="pointer"><a href="readNotice?noticeId=${notice.noticeId }&pageNum=${noticePage.currentPage}" class="title-container">${notice.title }</a></td>
								<td></td>
								<td class="tc">${notice.writer.name }</td>
								<td></td>
								<td class="tc">${notice.views}</td>
								<td></td>
								<td class="tc">${notice.wdateStr}</td>
							</tr>
						</c:forEach>
						<tr class="line-top line-bottom write-tr">
							<td colspan="9" class="write">
								<a href="writeNotice">
									<button class="wrtie-button">게시글 쓰기</button>
								</a>
							</td>
						</tr>
						<!-- 게시글이 있다면 베이지 블럭도 표시 -->
						<c:if test="${noticePage.hasNotice()}">
							<tr class="tc">
								<td class="paging" colspan="9">
									<c:if test="${noticePage.startPage > 5}">
										<a href="notice?pageNum=${noticePage.startPage-5 }"><span class="prev">이전</span></a>
									</c:if>
									<c:forEach var="pageNum" begin="${noticePage.startPage }" end="${noticePage.endPage }">
										<div class="inline pagination-border">
											<c:if test="${pageNum == param.pageNum }">
												<a href="notice?pageNum=${pageNum }" class="pagenation" style="color: blue;">${pageNum }</a>
											</c:if>
											<c:if test="${pageNum != param.pageNum }">
												<a href="notice?pageNum=${pageNum }" class="pagenation">${pageNum }</a>
											</c:if>
										</div>
									</c:forEach>
									<c:if test="${noticePage.endPage < noticePage.totalPages }">
										<a href="notice?pageNum=${noticePage.startPage+5 }"><span class="next">다음</span></a>
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