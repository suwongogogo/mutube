<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/mutube/CSS/postListView.css" />
<title>글 정보</title>
</head>
<body>
	<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board">
		   		<div class="table-view-container">
		   			<div class="post-information">
		   				<span class="post-title">Post Information</span>
		   				<span class="back-button"><a href="/mutube/managementList.jsp">뒤로가기</a></span>
		   			</div>
		   			<div class="table-view-div">
				   		<table class="view-real-table">
							<tr class="border-line-bottom">
								<td class="table-title tc">postId</td>
								<td class="interval"></td>
								<td class="table-title tc">userId</td>
								<td class="interval"></td>
								<td class="table-title tc">name</td>
								<td class="interval"></td>
								<td class="table-title pointer">title</td>
								<td class="table-title tc">genre</td>
								<td class="interval"></td>
								<td class="table-title tc">country</td>
								<td class="interval"></td>
								<td class="table-title tc">instrument</td>
								<td class="interval"></td>
								<td class="table-title tc">write_date</td>
								<td class="interval"></td>
								<td class="table-title tc">update_date</td>
								<td class="interval"></td>
								<td class="table-title tc">views</td>
								<td class="interval"></td>
								<td class="table-title tc">able</td>
								<td class="interval"></td>
								<td class="table-title tc">삭제</td>
							</tr>
							<c:forEach var="post" items="${postPageINF.postList }">
								<tr class="border-line-bottom">
									<td class="tc">${post.postId }</td>
									<td></td>
									<td class="tc">${post.writer.userId }</td>
									<td></td>
									<td class="tc">${post.writer.name }</td>
									<td></td>
									<td class="long-interval">
										<a href="/mutube/post/view?no=${post.postId }" class="full-widht">${post.title }</a>
									</td>
									<td class="tc">${post.genre }</td>
									<td></td>
									<td class="tc">${post.country }</td>
									<td></td>
									<td class="tc">${post.instrument }</td>
									<td></td>
									<td class="tc">${post.write_date }</td>
									<td></td>
									<td class="tc">${post.update_date }</td>
									<td></td>
									<td class="tc">${post.views }</td>
									<td></td>
									<td class="tc">${post.able }</td>
									<td></td>
									<td class="tc">
										<form action="/mutube/admin/deletePost?postId=${post.postId }" method="post">
											<button>삭제</button>
										</form>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${postPageINF.hasUser() }">
								<tr class="tc">
									<td colspan="21">
										<c:if test="${postPageINF.startPage > 6 }">
											<a href="postManagement?pageNum=${postPageINF.startPage - 6 }" class="prev">이전</a>
										</c:if>
										<c:forEach var="pageNum" begin="${postPageINF.startPage }" end="${postPageINF.endPage }">
											<a href="postManagement?pageNum=${pageNum }" class="underline">${pageNum }</a>
										</c:forEach>
										<c:if test="${postPageINF.endPage < postPageINF.totalPage }">
											<a href="postManagement?pageNum=${postPageINF.startPage + 6 }" class="next">다음</a>
										</c:if> 
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
		   	</div>	
    	</div>
    	<jsp:include page="/particular/footer.jsp"></jsp:include>
    </div>
</body>
</html>