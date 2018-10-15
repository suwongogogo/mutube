<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/mutube/CSS/postListView.css" />
<title>회원 정보</title>
</head>
<body>
	<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board" style="margin-left: 347px;">
		   		<div class="table-view-container">
		   			<div class="post-information">
		   				<span class="post-title">User Information</span>
		   				<span class="back-button"><a href="/mutube/managementList.jsp">뒤로가기</a></span>
		   			</div>
		   			<div class="table-view-div">
				   		<table class="view-real-table">
							<tr class="border-line-bottom">
								<td class="table-title tc">UserId</td>
								<td class="interval"></td>
								<td class="table-title tc">LoginId</td>
								<td class="interval"></td>
								<td class="table-title tc">password</td>
								<td class="interval"></td>
								<td class="table-title tc">email</td>
								<td class="interval"></td>
								<td class="table-title tc">name</td>
								<td class="interval"></td>
								<td class="table-title tc">register_date</td>
								<td class="interval"></td>
								<td class="table-title tc">authority</td>
								<td class="interval"></td>
								<td class="table-title tc">삭제</td>
							</tr>
							<c:forEach var="user" items="${userpageINF.userList }">
								<tr class="border-line-bottom">
									<td class="tc">${user.userId }</td>
									<td></td>
									<td class="tc">${user.loginId }</td>
									<td></td>
									<td class="tc">${user.password }</td>
									<td></td>
									<td class="tc">${user.email }</td>
									<td></td>
									<td class="tc">${user.name }</td>
									<td></td>
									<td class="tc">${user.register_date }</td>
									<td></td>
									<td class="tc">${user.authority }</td>
									<td></td>
									<td class="tc">
										<form action="/mutube/admin/deleteUser?userId=${user.userId }" method="post">
											<button>삭제</button>
										</form>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${userpageINF.hasUser() }">
								<tr class="tc">
									<td colspan="21">
										<c:if test="${userpageINF.startPage > 6 }">
											<a href="userManagement?pageNum=${userpageINF.startPage - 6 }" class="prev">이전</a>
										</c:if>
										<c:forEach var="pageNum" begin="${userpageINF.startPage }" end="${userpageINF.endPage }">
											<a href="userManagement?pageNum=${pageNum }" class="underline">${pageNum }</a>
										</c:forEach>
										<c:if test="${userpageINF.endPage < userpageINF.totalPage }">
											<a href="userManagement?pageNum=${userpageINF.startPage + 6 }" class="next">다음</a>
										</c:if> 
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</div>
		   	</div>	
    	</div>
    	<jsp:include page="/particular/footer.jsp"></jsp:include>
    </div>
</body>
</html>