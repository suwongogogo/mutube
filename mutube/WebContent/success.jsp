<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성공</title>
</head>
<body>
	<%
		Map<String, String> map = new HashMap<String, String>();
		map = (Map) request.getAttribute("success");
		String successCode = map.get("successCode");
		String from = map.get("from");

		switch (successCode) {
		case "writePost":
			out.print("<script>alert('게시글을 작성하였습니다.'); location.href='/mutube" + from + "';</script>");
			break;

		case "updatePost":
			out.print("<script>alert('게시글을 수정하였습니다.'); location.href='/mutube" + from + "';</script>");
			break;

		case "deletePost":
			out.print("<script>alert('게시글이 삭제되었습니다.'); location.href='/mutube" + from + "';</script>");
			break;

		case "updateUser":
			out.print("<script>alert('회원 수정 완료.'); location.href='/mutube" + from + "';</script>");
			break;

		case "changePassword":
			out.print("<script>alert('비밀번호 변경 완료.'); location.href='/mutube" + from + "';</script>");
			break;
		}
	%>
</body>
</html>