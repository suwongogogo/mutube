<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/readNotice.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
							<td colspan="6" class="view-title">제목</td>
						</tr>
						<tr>
							<td colspan="6">${noticeData.notice.title }</td>
						</tr>	
						<tr>
							<td colspan="6" class="text-none">
								<div class="view-content-container">
									<div class="margin-right">
										<c:if test="${noticeData.noticeContent.imageNames != null }">
											<c:forEach var="i" begin="0"
												end="${noticeData.noticeContent.imageNames.size()-1 }">
												<img src="/img/${noticeData.noticeContent.imageNames.get(i) }"
													width="660" height="420">
											</c:forEach>
										</c:if>
									</div>
									<div class="margin-right">
										<c:if test="${noticeData.noticeContent.video_link != null }">
											<iframe width="660" height="315"
												src="https://www.youtube.com/embed/${postData.noticeContent.video_link }"
												frameborder="0" allow="autoplay; encrypted-media"
												allowfullscreen></iframe>
										</c:if>
									</div>
									<div class="content">${noticeData.noticeContent.content }</div>
									<c:if
										test="${loginUser!=null && loginUser.userId== noticeData.notice.writer.userId }">
										<div class="view-button-container margin-right">
											<a href="updateNotice?noticeId=${noticeData.notice.noticeId }">
												<button class="view-button">수정하기</button>
											</a> <a href="deleteNotice?noticeId=${noticeData.notice.noticeId }">
												<button class="view-button">삭제하기</button>
											</a>
										</div>
									</c:if>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="comment-container">
									<c:if test="${loginUser == null }">
									</c:if>
									<c:if test="${loginUser != null }">
										<div class="userInf">
											<p>${loginUser.name }<br> <font size="2"
													color="gray">(${loginUser.loginId })</font>
											</p>
										</div>
									</c:if>
									<div class="comment-form">
										<form action="writeComment" method="post">
											<input type="hidden" name="pageNum" value="${param.pageNum }">
											<input type="hidden" name="postId" value="${param.no }">
											<div class="form">
												<textarea rows="4" class="comment-textarea" name="comment"></textarea>
												<input type="submit" value="댓글 작성" class="submit">
											</div>
										</form>
									</div>
								</div>
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