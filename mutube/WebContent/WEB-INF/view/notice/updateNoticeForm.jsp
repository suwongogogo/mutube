<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" media="screen"
	href="/mutube/CSS/postForm.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>공지 변경</title>
</head>
<body>
<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
		<div class="center">
			<jsp:include page="/particular/nav.jsp"></jsp:include>
			<div class="postForm-container">
				<div class="title-container">
					<form action="updateNotice" method="post" enctype="multipart/form-data">
						<input type="hidden" name="no" value="${noticeData.notice.noticeId }">
						<div class="title-form">
							<p class="write-title">제목</p>
							<input class="input" name="title" type="text" value="${noticeData.notice.title }">
						</div>
						<div class="video-container">
							<p class="write-title">영상 링크</p>
							<input type="text" name="video_link" class="video-input" value="${noticeData.noticeContent.video_link }">
						</div>
						<div class="video-container">
							<p class="write-title">이미지 첨부하기</p>
							<input type="file" name="image" class="image-input" size="10">
						</div>
						<div class="content">
							<p class="write-title content-title">내용</p>
							<textarea name="content" class="textarea" >${noticeData.noticeContent.content }</textarea>
						</div>
						<div class="button-container">
							<input type="submit" value="수정" class="submit button">
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