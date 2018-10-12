<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/mutube/CSS/postListView.css" />
<title>글 정보</title>
</head>
<body>
	<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board">
		   		<table border="1">
					<tr>
						<th colspan="12">글 정보</th>
					</tr>
					<tr>
						<td>postId</td>
						<td>userId</td>
						<td>name</td>
						<td>title</td>
						<td>genre</td>
						<td>country</td>
						<td>instrument</td>
						<td>write_date</td>
						<td>update_date</td>
						<td>views</td>
						<td>able</td>
					</tr>
					<c:forEach var="post" items="${postPageINF.postList }">
						<tr>
							<td class="short-interval">${post.postId }</td>
							<td class="short-interval">${post.writer.userId }</td>
							<td class="short-interval">${post.writer.name }</td>
							<td class="long-interval">${post.title }</td>
							<td class="short-interval">${post.genre }</td>
							<td class="short-interval">${post.country }</td>
							<td class="short-interval">${post.instrument }</td>
							<td class="short-interval">${post.write_date }</td>
							<td class="short-interval">${post.update_date }</td>
							<td class="short-interval">${post.views }</td>
							<td class="short-interval">${post.able }</td>
						</tr>
					</c:forEach>
					<c:if test="${postPageINF.hasUser() }">
						<tr>
							<td colspan="7">
								<a href="postManagement?pageNum=1">[처음으로]</a>
								<c:if test="${postPageINF.startPage > 6 }">
									<a href="postManagement?pageNum=${postPageINF.startPage - 6 }">[이전으로]</a>
								</c:if>
								<c:forEach var="pageNum" begin="${postPageINF.startPage }" end="${postPageINF.endPage }">
									<a href="postManagement?pageNum=${pageNum }">[${pageNum }]</a>
								</c:forEach>
								<c:if test="${postPageINF.endPage < postPageINF.totalPage }">
									<a href="postManagement?pageNum=${postPageINF.startPage + 6 }">[다음]</a>
								</c:if> 
								<a href="postManagement?pageNum=${postPageINF.totalPage }">[마지막으로]</a>
							</td>
						</tr>
					</c:if>
				</table>
		   	</div>	
    	</div>
    	<jsp:include page="/particular/footer.jsp"></jsp:include>
    </div>
</body>
</html>