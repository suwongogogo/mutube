<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen"
	href="/mutube/CSS/postForm.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 쓰기</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
		<div class="center">
			<jsp:include page="/particular/nav.jsp"></jsp:include>
			<div class="postForm-container">
				<div class="title-container">
					<form action="writeNotice" method="post" enctype="multipart/form-data">
						<div class="title-form">
							<p class="write-title">제목</p>
							<input class="input" name="title" type="text">
						</div>
						<c:if test="${errors.title }">
							<script>
								alert("제목을 입력하세요");
							</script>
						</c:if>
						<div class="video-container">
							<p class="write-title">영상 링크</p>
							<input type="text" name="video_link" class="video-input">
						</div>
						<div class="video-container">
							<p class="write-title">이미지 첨부하기</p>
							<input type="file" name="image[]" multiple="multiple"
								class="image-input">
						</div>
						<div class="content">
							<p class="write-title content-title">내용</p>
							<textarea name="content" class="textarea"></textarea>
						</div>
						<div class="button-container">
							<input type="submit" value="글쓰기" class="submit button">
							<button class="cancle button" onclick="cancle()">취소</button>
						</div>
					</form>
				</div>
			</div>
		</div>
		<jsp:include page="/particular/footer.jsp"></jsp:include>
	</div>
</body>
</html>