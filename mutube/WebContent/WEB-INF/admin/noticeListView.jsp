<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/mutube/CSS/postListView.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board">
		   		<div class="table-view-container">
		   			<div class="post-information">
		   				<span class="post-title">Notice Information</span>
		   				<span class="back-button"><a href="/mutube/managementList.jsp">뒤로가기</a></span>
		   			</div>
		   			<div class="table-view-div">
				   		<table class="view-real-table">
							<tr class="border-line-bottom">
								<td class="table-title tc">선택</td>
								<td class="interval"></td>
								<td class="table-title tc">noticeId</td>
								<td class="interval"></td>
								<td class="table-title tc">userId</td>
								<td class="interval"></td>
								<td class="table-title tc">name</td>
								<td class="interval"></td>
								<td class="table-title pointer">title</td>
								<td class="table-title tc">write_date</td>
								<td class="interval"></td>
								<td class="table-title tc">update_date</td>
								<td class="interval"></td>
								<td class="table-title tc">views</td>
								<td class="interval"></td>
								<td class="table-title tc">able</td>
								<td class="interval"></td>
								<td class="table-title tc">삭제</td>
							</tr>
							<c:forEach var="notice" items="${noticePageINF.noticeList }">
								<tr class="border-line-bottom">
									<td class="tc"><input type="checkbox" name="check"></td>
									<td></td>
									<td class="tc">${notice.noticeId }</td>
									<td></td>
									<td class="tc">${notice.writer.userId }</td>
									<td></td>
									<td class="tc">${notice.writer.name }</td>
									<td></td>
									<td class="long-interval">
										<a href="/mutube/notice/readNotice?noticeId=${notice.noticeId }" class="full-widht">${notice.title }</a>
									</td>
									<td class="tc">${notice.write_date }</td>
									<td></td>
									<td class="tc">${notice.update_date }</td>
									<td></td>
									<td class="tc">${notice.views }</td>
									<td></td>
									<td class="tc">${notice.able }</td>
									<td></td>
									<td class="tc">
										<form action="/mutube/admin/deleteNotice?noticeId=${notice.noticeId }" method="post">
											<button>삭제</button>
										</form>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${noticePageINF.hasNotice() }">
								<tr class="tc">
									<td colspan="21">
										<c:if test="${noticePageINF.startPage > 6 }">
											<a href="noticeManagement?pageNum=${noticePageINF.startPage - 6 }" class="prev">이전</a>
										</c:if>
										<c:forEach var="pageNum" begin="${noticePageINF.startPage }" end="${noticePageINF.endPage }">
											<a href="noticeManagement?pageNum=${pageNum }" class="underline">${pageNum }</a>
										</c:forEach>
										<c:if test="${noticePageINF.endPage < noticePageINF.totalPages }">
											<a href="noticeManagement?pageNum=${noticePageINF.startPage + 6 }" class="next">다음</a>
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