<%@page import="Post.Model.PostData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/mutube/CSS/readPost.css" />
<script type="text/javascript" src="/mutube/JavaScript/readPost.js"></script>
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
									<c:if test="${loginUser == null }">
										<div class="userInf">
											<p>
												<font size="2">로그인이 필요한 서비스 입니다.</font>
											</p>
										</div>
									</c:if>
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
									<form action="writeComment" method="post"
										onsubmit="return false;" id="comment-submit">
										<input type="hidden" name="pageNum" value="${param.pageNum }">
										<input type="hidden" name="postId" value="${param.no }">
										<div class="form">
											<textarea rows="5" class="comment-textarea" id="comment"
												name="comment" onkeydown="commnetLimit()"></textarea>
											<input type="submit" value="댓글 작성" class="submit"
												id="comment-submit" onclick="commnetPost()">
										</div>
									</form>
								</div>
							</td>
						</tr>
						<c:forEach var="comment"
							items="${postData.commentPage.commentList }">
							<tr class="real-comment-tr">
								<td class="name">${comment.writer.name}<br> <font
									size="2" color="darkgray">${comment.writer.loginId }</font>
								</td>
								<td class="comment">${comment.comment}</td>
								<td class="util"><c:if
										test="${loginUser.userId == comment.userId }">
										<td class="util"><a
											href="deleteComment?commentId=${comment.commentId }&no=${param.no}&pageNum=${param.pageNum}">
												삭제 </a></td>
									</c:if>
							</tr>
						</c:forEach>
						<c:if test="${postData.commentPage.hasComment()}">
							<tr class="tc">
								<td class="paging" colspan="9"><c:if
										test="${postData.commentPage.startPage > 5}">
										<a
											href="view?no=${param.no }&pageNum=${postData.commentPage.startPage-5 }"><span
											class="arrow">◀</span><span class="prev">이전</span></a>
									</c:if>
									<div class="inline pagination-border">
										<c:forEach var="pageNum"
											begin="${postData.commentPage.startPage }"
											end="${postData.commentPage.endPage }">
											<a href="view?no=${param.no }&pageNum=${pageNum }"
												class="pagenation">${pageNum }</a>
										</c:forEach>
									</div> <c:if
										test="${postData.commentPage.endPage < postData.commentPage.totalPages }">
										<a
											href="view?no=${param.no }&pageNum=${postData.commentPage.startPage+5 }"><span
											class="next">다음</span><span class="arrow">▶</span></a>
									</c:if>
								</td>
							</tr>
						</c:if>
					</table>
					<div class="button-container">
							<div class="view-button-container">
						<c:if
							test="${loginUser!=null && loginUser.userId== postData.post.writer.userId }">
								<a href="update?no=${postData.post.postId }">
									<button class="view-button">수정</button>
								</a> <a href="delete?no=${postData.post.postId }">
									<button class="view-button">삭제</button>
								</a>
						</c:if>
						<a href="list?pageNum=${param.pageNum }">
							<button class="view-button">목록</button>
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/particular/footer.jsp"></jsp:include>
	</div>
</body>
</html>