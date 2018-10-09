<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>글쓰기</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="/mutube/CSS/postForm.css" />
<script src="/mutube/JavaScript/postForm.js"></script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
		<div class="center">
			<jsp:include page="/particular/nav.jsp"></jsp:include>
			<div class="postForm-container">
				<div class="title-container">
					<form action="write" method="post" enctype="multipart/form-data">
						<div class="title-form">
							<p class="write-title">제목</p>
							<input class="input" name="title" type="text">
						</div>
						<c:if test="${errors.genre }"><script>
							alert("장르를 선택하세요");
						</script></c:if>
						<c:if test="${errors.country }"><script>
							alert("가수를 선택하세요");
						</script></c:if>
						<c:if test="${errors.instrument }"><script>
							alert("악기를 선택하세요");
						</script></c:if>
						<div class="select-container">
							<div class="genre-form">
								<p class="write-title">장르</p>
								<select name="genre" class="select">
									<option value="jazz" selected class="option">재즈</option>
									<option value="blues" class="option">블루스</option>
									<option value="rock" class="option">락</option>
									<option value="balad" class="option">발라드</option>
									<option value="pop" class="option">팝</option>
									<option value="musical" class="option">뮤지컬</option>
									<option value="orchestra" class="option">오케스트라</option>
								</select>
							</div>
							<div class="musician-form">
								<p class="write-title">나라</p>
								<select name="country" class="select">
									<option value="korea" selected class="option">때한민꾺</option>
								</select>
							</div>
							<div class="musician-form">
								<p class="write-title">악기</p>
								<select name="instrument" class="select">
									<option value="vocal" selected class="option">보컬</option>
									<option value="guitar" class="option">기타</option>
									<option value="piano" class="option">피아노</option>
									<option value="violin" class="option">바이올린</option>
									<option value="chello" class="option">첼로</option>
									<option value="trumpet" class="option">트럼펫</option>
									<option value="trombone" class="option">트롬본</option>
								</select>
							</div>
						</div>
						<div class="video-container">
							<p class="write-title">영상 링크</p>
							<input type="text" name="video_link" class="video-input">
						</div>
						<div class="video-container">
							<p class="write-title">이미지 첨부하기</p>
							<input type="file" name="image" class="image-input" size="10">
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