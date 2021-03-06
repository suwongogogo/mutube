<%@page import="Post.Model.PostData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	PostData postData = (PostData) request.getAttribute("postData");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/postForm.css" />
<script src="/mutube/JavaScript/postForm.js"></script>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
		<div class="center">
			<jsp:include page="/particular/nav.jsp"></jsp:include>
			<div class="postForm-container">
				<div class="title-container">
					<form action="update" method="post" enctype="multipart/form-data"
						onsubmit="return false" id="form">
						<input type="hidden" name="no" value="${postData.post.postId }">
						<div class="title-form">
							<p class="write-title">제목</p>
							<input class="input" name="title" type="text"
								value="${postData.post.title }">
						</div>
						<c:if test="${errors.genre }">
							<script>
							alert("장르를 선택하세요");
						</script>
						</c:if>
						<c:if test="${errors.country }">
							<script>
							alert("가수를 선택하세요");
						</script>
						</c:if>
						<c:if test="${errors.instrument }">
							<script>
							alert("악기를 선택하세요");
						</script>
						</c:if>
						<div class="select-container">
							<div class="genre-form">
								<p class="write-title">장르</p>
								<select name="genre" class="select">
									<option value="blues"
										<%if (postData.getPost().getGenre().equals("blues")) {%>
										selected <%}%> class="option">블루스</option>
									<option value="rock"
										<%if (postData.getPost().getGenre().equals("rock")) {%>
										selected <%}%> class="option">락</option>
									<option value="jazz"
										<%if (postData.getPost().getGenre().equals("jazz")) {%>
										selected <%}%> class="option">재즈</option>
									<option value="r&b"
										<%if (postData.getPost().getGenre().equals("r&b")) {%> selected
										<%}%> class="option">R&B</option>
									<option value="funk"
										<%if (postData.getPost().getGenre().equals("funk")) {%>
										selected <%}%> class="option">FUNK</option>
									<option value="hiphop"
										<%if (postData.getPost().getGenre().equals("hiphop")) {%>
										selected <%}%> class="option">힙합</option>
									<option value="metal"
										<%if (postData.getPost().getGenre().equals("metal")) {%>
										selected <%}%> class="option">메탈</option>
									<option value="edm"
										<%if (postData.getPost().getGenre().equals("edm")) {%> selected
										<%}%> class="option">EDM</option>
									<option value="classic"
										<%if (postData.getPost().getGenre().equals("classic")) {%>
										selected <%}%> class="option">클래식</option>
									<option value="gospel"
										<%if (postData.getPost().getGenre().equals("gospel")) {%>
										selected <%}%> class="option">GOSPEL</option>
									<option value="fusion"
										<%if (postData.getPost().getGenre().equals("fusion")) {%>
										selected <%}%> class="option">Fusion Jazz</option>
									<option value="etc"
										<%if (postData.getPost().getGenre().equals("etc")) {%> selected
										<%}%> class="option">etc...</option>

								</select>
							</div>
							<div class="musician-form">
								<p class="write-title">나라</p>
								<select name="country" class="select">
									<option value="korea"
										<%if (postData.getPost().getCountry().equals("korea")) {%>
										selected <%}%> class="option">대한민국</option>
									<option value="japan"
										<%if (postData.getPost().getCountry().equals("japan")) {%>
										selected <%}%> class="option">일본</option>
									<option value="usa"
										<%if (postData.getPost().getCountry().equals("usa")) {%>
										selected <%}%> class="option">미국</option>
									<option value="germany"
										<%if (postData.getPost().getCountry().equals("germany")) {%>
										selected <%}%> class="option">독일</option>
									<option value="china"
										<%if (postData.getPost().getCountry().equals("china")) {%>
										selected <%}%> class="option">중국</option>
									<option value="spain"
										<%if (postData.getPost().getCountry().equals("spain")) {%>
										selected <%}%> class="option">스페인</option>
									<option value="england"
										<%if (postData.getPost().getCountry().equals("england")) {%>
										selected <%}%> class="option">영국</option>
									<option value="france"
										<%if (postData.getPost().getCountry().equals("france")) {%>
										selected <%}%> class="option">프랑스</option>
									<option value="russia"
										<%if (postData.getPost().getCountry().equals("russia")) {%>
										selected <%}%> class="option">러시아</option>
								</select>
							</div>
							<div class="musician-form">
								<p class="write-title">악기</p>
								<select name="instrument" class="select">
									<option value="guitar"
										<%if (postData.getPost().getInstrument().equals("guitar")) {%>
										selected <%}%> class="option">기타</option>
									<option value="bass"
										<%if (postData.getPost().getInstrument().equals("bass")) {%>
										selected <%}%> class="option">베이스</option>
									<option value="vocal"
										<%if (postData.getPost().getInstrument().equals("vocal")) {%>
										selected <%}%> class="option">보컬</option>
									<option value="drum"
										<%if (postData.getPost().getInstrument().equals("drum")) {%>
										selected <%}%> class="option">드럼</option>
									<option value="piano"
										<%if (postData.getPost().getInstrument().equals("piano")) {%>
										selected <%}%> class="option">피아노</option>
									<option value="trumpet"
										<%if (postData.getPost().getInstrument().equals("trumpet")) {%>
										selected <%}%> class="option">트럼펫</option>
									<option value="saxophone"
										<%if (postData.getPost().getInstrument().equals("saxophone")) {%>
										selected <%}%> class="option">색소폰</option>
									<option value="trombone"
										<%if (postData.getPost().getInstrument().equals("trombone")) {%>
										selected <%}%> class="option">트롬본</option>
									<option value="acoustic"
										<%if (postData.getPost().getInstrument().equals("acoustic")) {%>
										selected <%}%> class="option">어쿠스틱 기타</option>
									<option value="etc"
										<%if (postData.getPost().getInstrument().equals("etc")) {%>
										selected <%}%> class="option">etc...</option>
								</select>
							</div>
						</div>
						<div class="video-container">
							<p class="write-title">영상 링크</p>
							<input type="text" name="video_link" class="video-input"
								value="${postData.postContent.video_link }">
						</div>
						<c:if
							test="${postData.postContent.imageNames!=null && !postData.postContent.imageNames.isEmpty() }">
							<div class="video-container">
								${i=0;''}
								<p class="write-title">이미지 미리보기</p>
								<c:forEach var="image"
									items="${postData.postContent.imageNames }">
									<span id="image${i= i+1 }"> <img src="/img/${image }"
										width="220" height="120"> <input type="hidden"
										name="image" value="${image }">
										<button onclick="deleteImage(image${i})">이미지 삭제</button>
									</span>
								</c:forEach>
							</div>
							<br>
						</c:if>

						<div class="video-container">
							<p class="write-title">이미지 첨부하기</p>
							<input type="file" name="image[]" multiple="multiple"
								class="image-input" size="10">
						</div>
						<div class="content">
							<p class="write-title content-title">내용</p>
							<textarea name="content" class="textarea">${postData.postContent.content }</textarea>
						</div>
						<div class="button-container">
							<input type="submit" value="수정" onclick="move()"
								class="submit button">
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