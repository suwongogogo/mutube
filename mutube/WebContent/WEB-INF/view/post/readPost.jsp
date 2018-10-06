<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
제목: ${postData.post.title }<br>

<br>
<c:if test="${!postDate.postContent.video_link eq null }">
	<iframe src="${postData.postContent.video_link }">
	</iframe>
</c:if>

내용 : ${postData.postContent.content }<br>
<c:if test="${loginUser!=null && loginUser.userId== postData.post.writer.userId }">
	<a href="updatePost?no=${postData.post.postId }">수정하기</a>
	<a href="deletePost?no=${postData.post.postId }">삭제하기</a>
</c:if>
</body>
</html>