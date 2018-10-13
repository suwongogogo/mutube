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
						<c:if test="${errors.title }"><script>
							alert("제목을 입력하세요");
						</script></c:if>
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
									<option value="blues" selected class="option">블루스</option>
									<option value="rock" class="option">락</option>
									<option value="jazz" class="option">재즈</option>
									<option value="r&b" class="option">R&B</option>
									<option value="funk" class="option">Funk</option>
									<option value="hiphop" class="option">힙합</option>
									<option value="metal" class="option">메탈</option>
									<option value="edm" class="option">EDM</option>
									<option value="classic" class="option">클래식</option>
									<option value="gospel" class="option">GOSPEL</option>
									<option value="fusion" class="option">퓨전 재즈</option>
									<option value="etc"  class="option">etc...</option>
									
								</select>
							</div>
							<div class="musician-form">
								<p class="write-title">나라</p>
								<select name="country" class="select">
									<option value="korea" class="option">대한민국</option>
									<option value="japan" class="option">일본</option>
									<option value="usa" class="option">미국</option>
									<option value="germany" class="option">독일</option>
									<option value="china" class="option">중국</option>
									<option value="spain" class="option">스페인</option>
									<option value="england" class="option">영국</option>
									<option value="france" class="option">프랑스</option>
									<option value="russia"  class="option">러시아</option>
									<option value="etc"  class="option">etc...</option>
								</select>
							</div>
							<div class="musician-form">
								<p class="write-title">악기</p>
								<select name="instrument" class="select">
									<option value="guitar" class="option">기타</option>
									<option value="bass" class="option">베이스</option>
									<option value="vocal" class="option">보컬</option>
									<option value="drum" class="option">드럼</option>
									<option value="piano" class="option">피아노</option>
									<option value="trumpet" class="option">트럼펫</option>
									<option value="saxophone" class="option">색소폰</option>
									<option value="trombone" class="option">트롬본</option>
									<option value="acoustic" class="option">어쿠스틱 기타</option>
									<option value="band" class="option">밴드</option>
									<option value="etc" class="option">etc...</option>
								</select>
							</div>
						</div>
						<div class="video-container">
							<p class="write-title">영상 링크</p>
							<input type="text" name="video_link" class="video-input">
						</div>
						<div class="video-container">
							<p class="write-title">이미지 첨부하기</p>
							<input type="file" name="image[]" multiple="multiple" class="image-input">
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