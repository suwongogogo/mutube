
<%@page import="Post.Model.PostData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/mutube/CSS/readPost.css" />
<title>게시글 보기</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
		<div class="center">
			<jsp:include page="/particular/nav.jsp"></jsp:include>
			<div class="board-container">
				<div class="board">
					<table class="view-table" border="1">
						<tr>
							<td class="view-title">제목</td>
							<td colspan="5">${postData.post.title }</td>
						</tr>
						<tr>
							<td class="view-title">장르</td>
							<td>오케스뚜라</td>
							<td class="view-title">국가</td>
							<td>방글라데시</td>
							<td class="view-title">악기</td>
							<td>탬버린</td>
						</tr>
						<tr>
							<td colspan="6" class="text-none">
								<div class="view-content-container">
									<div class="margin-right">
										<c:if test="${postData.postContent.video_link != null }">
											<iframe width="560" height="315"
												src="https://www.youtube.com/embed/${postData.postContent.video_link }"
												frameborder="0" allow="autoplay; encrypted-media"
												allowfullscreen></iframe>
										</c:if>
									</div>
									<div class="margin-right">
										<c:if test="${postData.postContent.imageName != null }">
											<img src="${postData.postContent.imageName }">
											<%
												PostData postData = (PostData) request.getAttribute("postData");
													out.print(postData.getPostContent().getImageName());
											%>
										</c:if>
									</div>
									<div>${postData.postContent.content }</div>
									<c:if
										test="${loginUser!=null && loginUser.userId== postData.post.writer.userId }">
										<div class="view-button-container margin-right">
											<a href="update?no=${postData.post.postId }">
												<button class="view-button">수정하기</button>
											</a> <a href="delete?no=${postData.post.postId }">
												<button class="view-button">삭제하기</button>
											</a>
										</div>
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								${loginUser.loginId }
							</td>
							<td colspan="5">
								<form action="writeComment" method="post">
									<input type="hidden" name="pageNum"
										value="<%=request.getParameter("pageNum")%>"> <input
										type="hidden" name="postId" value="${postData.post.postId }">
									<div class="comment">
										<textarea rows="4" name="comment"></textarea>
										<input type="submit" value="작성">
									</div>
								</form>
							</td>
						</tr>
						<c:forEach var="comment"
							items="${postData.commentPage.commentList }">
							<tr>
								<td colspan="2">${postData.commentPage.commentList.userId }
								</td>
								<td colspan="3">${postData.commentPage.commentList.comment }
								</td>
								<td>[답변]<br> [수정]<br> [삭제]
								</td>
							</tr>
						</c:forEach>

					</table>
				</div>
			</div>
		</div>
		<jsp:include page="/particular/footer.jsp"></jsp:include>
	</div>
</body>
</html>