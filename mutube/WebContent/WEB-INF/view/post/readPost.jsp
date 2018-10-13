
<%@page import="Post.Model.PostData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
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
					<table class="read-post-contaienr">
						<tr class="bottom-line">
							<td colspan="6" class="post-title tc padding-bottom-5">${postData.post.title }</td>
						</tr>
						<tr class="bottom-line">
							<td colspan="2" class="post-subtitle tc padding-top-bottom-5">${postData.post.genre }</td>
							<td colspan="2" class="post-subtitle tc padding-top-bottom-5">${postData.post.country }</td>
							<td colspan="2" class="post-subtitle tc padding-top-bottom-5">${postData.post.instrument }</td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="img-vedio-div">
									<div class="img-div">
										<c:if test="${postData.postContent.imageNames != null }">
											<c:forEach var="i" begin="0"
												end="${postData.postContent.imageNames.size()-1 }">
												<img src="/img/${postData.postContent.imageNames.get(i) }"
													width="660" height="420" class="padding-top-15">
											</c:forEach>
										</c:if>
									</div>
									<div>
										<c:if test="${postData.postContent.video_link != null }">
											<iframe width="660" height="315"
												src="https://www.youtube.com/embed/${postData.postContent.video_link }"
												frameborder="0" allow="autoplay; encrypted-media"
												allowfullscreen class="vedio-div"></iframe>
										</c:if>
									</div>
								</div>
							<div class="content-div">${postData.postContent.content }</div>
						</tr>
					</table>
					<table class="comment-table">
						<tr class="comment-tr">
							<td class="comment-id">
								<div>
									<c:if test="${loginUser == null }"></c:if>
									<c:if test="${loginUser != null }">
										<div class="userInf">
											<p>${loginUser.name }<br> <font size="2"
													color="gray">(${loginUser.loginId })</font>
											</p>
										</div>
									</c:if>
								</div>
							</td>
							<td class="padding-none" colspan="2">
								<div class="comment-form">
									<form action="writeComment" method="post">
										<input type="hidden" name="pageNum" value="${param.pageNum }">
										<input type="hidden" name="postId" value="${param.no }">
										<div class="form">
											<textarea rows="5" class="comment-textarea" name="comment"></textarea>
											<input type="submit" value="댓글 작성" class="submit">
										</div>
									</form>
								</div>
							</td>
						</tr>
						<c:forEach var="comment" 
								items="${postData.commentPage.commentList }">
							<tr class="real-comment-tr">
								<td class="name">${comment.writer.name}</td>
								<td class="comment">${comment.comment}</td>
								<td class="util">수정 삭제</td>
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
<%-- <div class="button-container">
	<c:if 
		test="${loginUser!=null && loginUser.userId== postData.post.writer.userId }">
		<div class="view-button-container">
			<a href="update?no=${postData.post.postId }">
				<button class="view-button">수정하기</button>
			</a> <a href="delete?no=${postData.post.postId }">
				<button class="view-button">삭제하기</button>
			</a>
		</div>
	</c:if>
</div> --%>