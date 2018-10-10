<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 수정</title>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/searchResult.css" />
</head>
<body>
	<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board">
		   		<form action="update" method="post" enctype="multipart/form-data">
					<input type="hidden" name="no" value="${postData.post.postId }">
					<input type="text" name="title" value="${postData.post.title }">
					<c:if test="${errors.title }">제목이 비어있습니다.</c:if>
					<input type="text" name="content" value="${postData.postContent.content }">
					<c:if test="${errors.content }">내용이 비어있습니다.</c:if>
					<input type="text" name="video_link" value="${postData.postContent.video_link }">
					
					<input type="text" name="genre" value="${postData.post.genre }">
					<c:if test="${errors.genre }">장르가 비어있습니다.</c:if>
					<input type="text" name="country" value="${postData.post.country }">
					<c:if test="${errors.country }">국가가 비어있습니다.</c:if>
					<input type="text" name="instrument" value="${postData.post.instrument }">
					<c:if test="${errors.insrument }">악기가 비어있습니다.</c:if>
					<input type="submit" value="수정하기">
				</form>
		   	</div>	
    	</div>
    	<jsp:include page="/particular/footer.jsp"></jsp:include>
    </div>
</body>
</html>