
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
							<td colspan="6">
								<form action="comment" method="post">
									<div class="comment">
										<textarea rows="6"></textarea>
										<input type="submit" value="작성">
									</div>
								</form>
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