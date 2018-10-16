<%@page import="User.Model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	User user = (User) request.getAttribute("user");
	request.setAttribute("user", user);
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/myPage.css" />
<script type="text/javascript" src="JavaScript/myPage.js"></script>
<title>마이페이지</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
		<div class="center">
			<jsp:include page="/particular/nav.jsp"></jsp:include>
			<div class="mypage-container">
			<a href="/mutube/Main.jsp"> <img src="/mutube/Image/logo.jpg"
		width="200" height="100" style="position: absolute; top:246px; right:757px;">
	</a>
				<div class="mypage">
				
					<div class="input-container">
						<p>아이디</p>
						<input class="font30 outline" value="${loginUser.loginId }" readonly="readonly">
					</div>
					<div class="input-container">
						<p>이름</p>
						<input class="font30 outline" value="${loginUser.name }" readonly="readonly">
					</div>
					<div class="input-container">
						<p>이메일</p>
						<input class="font30 outline" value="${loginUser.email }" readonly="readonly">
					</div>
					<div class="button-container">
						<div class="flex">
							<a href="#">
								<button class="submit input">내가 쓴 글</button>
							</a>
							<a href="/mutube/user/changePassword?userId=${loginUser.userId }">
								<button class="submit input">비밀번호 수정</button>
							</a>
						</div>
						<div class="flex">
							<a href="/mutube/user/update?userId=${loginUser.userId }" class="block">
								<button class="submit input">회원 수정</button>
							</a>
							<%-- 패스워드 받기 --%>
							<form action="/mutube/user/deleteUserComfirmByPassword" onsubmit="return false" id="userOutForm" class="inline" onclick="userOut()" method="get">
								<span>
									<input type="hidden" value="${loginUser.userId }">
									<input type="submit" class="submit input" value="회원 탈퇴">
								</span>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/particular/footer.jsp"></jsp:include>
	</div>
</body>
</html>