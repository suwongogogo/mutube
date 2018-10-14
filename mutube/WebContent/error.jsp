<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러</title>
</head>
<body>
	<%
		Map<String, String> map = new HashMap<String, String>();
		map = (Map) request.getAttribute("error");
		String errorCode = map.get("errorCode");
		String from = map.get("from");
		
		switch (errorCode) {
		case "userNotFound":
			out.print("<script>alert('없는 유저입니다.'); location.href='/mutube" + from + "';</script>");
			break;
		case "postNotFound":
			out.print("<script>alert('게시글을 찾을 수 없습니다.'); location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "dbError":
			out.print("<script>alert('DataBase에 오류가 발생하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "notAdmin":
			out.print("<script>alert('관리자 권한이 없습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + "/Main.jsp");
			break;
		case "passwordNotMatch":
			out.print("<script>alert('비밀번호가 맞지 않습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "userDeleteFail":
			out.print("<script>alert('회원 탈퇴에 실패하였습다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "WritePostFail":
			out.print("<script>alert('게시글 쓰기에 실패하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "ValueIsEmpty":
			out.print("<script>alert('값이 들어있지 않습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "NoticeNotFound":
			out.print("<script>alert('공지를 찾을 수 없습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "FailWriteNoticeComment":
			out.print("<script>alert('댓글 작성에 실패하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "PageNotFound":
			out.print("<script>alert('페이지를 찾을 수 없습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "UpdateNoticeFail":
			out.print("<script>alert('공지를 수정하는데 실패하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "DeleteNoticeFail":
			out.print("<script>alert('공지를 삭제하는데 실패하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "CommentNotFound":
			out.print("<script>alert('댓글 삭제에 실패하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "DeletePostFail":
			out.print("<script>alert('게시글 삭제에 실패하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "UpdatePostFail":
			out.print("<script>alert('게시글을 수정하는데 실패하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		case "WriteCommentFail":
			out.print("<script>alert('댓글 작성에 실패하였습니다.') location.href='/mutube" + from + "';</script>");
			response.sendRedirect(request.getContextPath() + from);
			break;
		} 
	%>
</body>
</html>