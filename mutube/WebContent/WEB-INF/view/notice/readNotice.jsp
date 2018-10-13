<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/readPost.css" />
<script type="text/javascript" src="/mutube/JavaScript/readPost.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지</title>
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
							<td colspan="6" class="post-title tc padding-bottom-5">${noticeData.notice.title }</td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="img-vedio-div">
									<div class="img-div">
										<c:if test="${noticeData.noticeContent.imageNames != null }">
											<c:forEach var="i" begin="0"
												end="${noticeData.noticeContent.imageNames.size()-1 }">
												<img src="/img/${noticeData.noticeContent.imageNames.get(i) }"
													width="660" height="420" class="padding-top-15">
											</c:forEach>
										</c:if>
									</div>
									<div>
										<c:if test="${noticeData.noticeContent.video_link != null }">
											<iframe width="660" height="315"
												src="https://www.youtube.com/embed/${postData.noticeContent.video_link }"
												frameborder="0" allow="autoplay; encrypted-media"
												allowfullscreen class="vedio-div"></iframe>
										</c:if>
									</div>
								</div>
							<div class="content-div">${noticeData.noticeContent.content }</div>
						</tr>
					</table>
					<table class="comment-table">
						<tr class="comment-tr">
							<td class="comment-id">
								<div>
									<c:if test="${loginUser == null }">
										<div class="userInf">
											<p>
												댓글을 쓰시려면 로그인을 해주세요.
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
									<form action="writeNoticeComment" method="post" onsubmit="return false;" id="comment-submit">
										<input type="hidden" name="pageNum" value="${param.pageNum }">
										<input type="hidden" name="noticeId" value="${param.noticeId }">
										<div class="form">
											<textarea rows="5" class="comment-textarea" id="comment" name="comment" onkeydown="commnetLimit()"></textarea>
											<input type="submit" value="댓글 작성" class="submit" onclick="commnetPost()">
										</div>
									</form>
								</div>
							</td>
						</tr>
						<c:forEach var="comment" 
								items="${noticeData.noticeCommentPage.noticeCommentList }">
							<tr class="real-comment-tr">
								<td class="name">${comment.writer.name}
								<font size="2" color="darkgray">${comment.writer.loginId }</font>
								</td>
								<td class="comment">${comment.comment}</td>
								<td class="util">
								<c:if test="${loginUser.userId == comment.userId }">
								<td class="util">
									<a href="deleteNoticeComment?commentId=${comment.commentId }&noticeId=${param.noticeId}&pageNum=${param.pageNum}">
										삭제
									</a>
								</td>
								</c:if>
							</tr>
						</c:forEach>
						<c:if test="${noticeData.noticeCommentPage.hasComment()}">
							<tr class="tc">
								<td class="paging" colspan="9">
									<c:if test="${noticeData.noticeCommentPage.startPage > 5}">
										<a href="readNotice?noticeId=${param.no }&pageNum=${noticeData.noticeCommentPage.startPage-5 }"><span class="arrow">◀</span><span class="prev">이전</span></a>
									</c:if>
									<c:forEach var="pageNum" begin="${noticeData.noticeCommentPage.startPage }" end="${noticeData.noticeCommentPage.endPage }">
										<div class="inline pagination-border">
											<a href="readNotice?noticeId=${param.noticeId }&pageNum=${pageNum }" class="pagenation">${pageNum }</a>
										</div>
									</c:forEach>
									<c:if test="${noticeData.noticeCommentPage.endPage < noticeData.noticeCommentPage.totalPages }">
										<a href="readNotice?noticeId=${param.noticeId }&pageNum=${noticeData.noticeCommentPage.startPage+5 }"><span class="next">다음</span><span class="arrow">▶</span></a>
									</c:if>
								</td>
							</tr>
						</c:if>
					</table>
					<div class="button-container">
						<c:if 
							test="${loginUser!=null && loginUser.userId== noticeData.notice.writer.userId }">
							<div class="view-button-container">
								<a href="updateNotice?noticeId=${noticeData.notice.noticeId }">
									<button class="view-button">수정</button>
								</a>
								<a href="deleteNotice?noticeId=${noticeData.notice.noticeId }">
									<button class="view-button">삭제</button>
								</a>
								<a href="notice?pageNum=${param.pageNum }">
									<button class="view-button">목록</button>
								</a>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/particular/footer.jsp"></jsp:include>
	</div>
</body>
</html>