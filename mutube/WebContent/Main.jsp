<%@page import="User.DAO.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>우리의 프로젝트</title>
    <link rel="stylesheet" type="text/css" href="CSS/main.css" />
    <script src="JavaScript/main.js"></script>
</head> 
<body oncontextmenu="return false" onselectstart="return false" ondragstart="return false">
    <div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board">
		   		<div class="main-board">
		   			<div class="song">
		   				<p>오늘의 음악</p>
		   				<iframe src="https://www.youtube.com/embed/iklTkWj1wiY" allowfullscreen></iframe>
		   			</div>
		   			<div class="song-comment">
		   				<p>
		   					Show Me The Money777 [5회] Team 코드 쿤스트 & 팔로알토 (pH-1, 키드밀리, 루피, 콸라)<br> 
		   					Good Day(feat. 팔로알토)(prod. 코드쿤스트)/MV
		   				</p>
		   			</div>
		   		</div>
		   	</div>	
    	</div>
    	<jsp:include page="/particular/footer.jsp"></jsp:include>
    </div>
</body>
</html>