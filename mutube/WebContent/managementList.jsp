<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS/managementList.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="wrapper">
		<jsp:include page="/particular/header.jsp"></jsp:include>
		<div class="center">
			<jsp:include page="/particular/nav.jsp"></jsp:include>
			<div class="mypage-container">
				<div class="management-List">
					<div class="button-container">
						<a href="/mutube/admin/userManagement">
							<button class="submit input">모든 회원 정보 조회</button>
						</a>
						<a href="/mutube/admin/postManagement">
							<button class="submit input">모든 게시물 조회</button>
						</a>
						<a href="/mutube/admin/noticeManagement">
							<button class="submit input">모든 공지 조회</button>
						</a>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="/particular/footer.jsp"></jsp:include>
	</div>
</body>
</html>