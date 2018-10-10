<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/mutube/CSS/searchResult.css" />
<title>전체글</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
	    	<div class="center">
	        	<jsp:include page="/particular/nav.jsp"></jsp:include>
			   	<div class="board-list">
			   		<div class="address">검색결과를 써용 ㅎㅎ</div> 
			   		<div class="board-list-container">
				   		<c:if test="${errors.postList }">검색한 게시글을 찾을 수 없습니다.</c:if>
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
								<c:forEach var="postList" items="${postList.postList }">
									<tr>
										<td class="tc height">${postList.postId }</td>
										<td></td>
										<td class="pointer height"><a href="view?no=${post.postId }&pageNum=${postPage.currentPage}">${postList.title }</a></td>
										<td></td>
										<td class="tc height">${postList.writer.name }</td>
										<td></td>
										<td class="tc height">${postList.genre }</td>
										<td></td>
										<td class="tc height">${postList.instrument }</td>
										<td></td>
										<td class="tc height">${postList.country }</td>
										<td></td>
										<td class="tc height">${postList.write_date }</td>
									</tr>
								</c:forEach>
								<tr class="tc">
									<td colspan="14" class="line-top">
										<c:if test="${postPage.startPage > 5}">
											<a href="list?pageNum=${postPage.startPage-5 }"><span class="arrow">◀</span><span class="prev">이전</span></a>
										</c:if>
										<c:forEach var="postNum" begin="${postList.startPage }" end="${postList.endPage }">
											<div class="inline pagination-border">
												<a href="list?pageNum=${postNum }" class="pagenation">${postNum }</a>
											</div>
										</c:forEach>
										<c:if test="${postPage.endPage < postPage.total }">
											<a href="list?pageNum=${postPage.startPage+5 }"><span class="next">다음</span><span class="arrow">▶</span></a>
										</c:if>
									</td>
								</tr>
						</table>
					</div>
			   	</div>
	    	</div>
    	<jsp:include page="/particular/footer.jsp"></jsp:include>
	</div>
</body>
</html>