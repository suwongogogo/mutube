<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="updatePost" method="post">
	<input type="hidden" name="no" value="${postData.post.postId }">
	<input type="text" name="title" value="${postData.post.title }">
	<input type="text" name="content" value="${postData.postContent.content }">
	<input type="text" name="video_link" value="${postData.postContent.video_link }">
	<input type="text" name="genre" value="${postData.post.genre }">
	<input type="text" name="country" value="${postData.post.country }">
	<input type="text" name="instrument" value="${postData.post.instrument }">
	<input type="submit" value="수정하기">
</form>
</body>
</html>