
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
							<td>${postData.post.genre }</td>
							<td class="view-title">국가</td>
							<td>${postData.post.country }</td>
							<td class="view-title">악기</td>
							<td>${postData.post.instrument }</td>
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
							<td colspan="6">
								<div class="comment-container">
									<div class="userInf">
										<p>${loginUser.name }<br>
										<font size="2" color="gray">(${loginUser.loginId })</font></p>
									</div>
									<div class="comment-form">
										<form action="writeComment" method="post">
											<div class="form">
												<textarea rows="5" class="comment-textarea"></textarea>
												<input type="submit" value="댓글 작성" class="submit">
											</div>
										</form>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div class="view-comment-container">
									<div class="id-container">
										<div class="id inline">아이디</div>
										<div class="comment-control-div">
											<a href="#">수정</a> <a href="#">삭제</a>
										</div>
									</div>
									<div class="comment inline">라라라라~라라라라라라랄~그냥 게임 하자~</div>
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