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
		map = (Map)request.getAttribute("error");
		
		String errorCode = map.get("errorCode");
		String from = map.get("from");
	%>
	<c:choose>
		<c:when test="<%=errorCode %> == 'postNotFound'" >
			<script>
				alert("게시글을 찾을 수 없습니다.");
				location.href = '<%=from %>';
			</script>
		</c:when>
		<c:when test="<%=errorCode %> == 'userNotFound'">
			<script>
				alert("유저를 찾을 수 없습니다.");
				location.href = '<%=from %>';
			</script>
		</c:when>
	</c:choose>
</body>
</html>