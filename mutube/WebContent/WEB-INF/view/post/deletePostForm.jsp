<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="delete" method="post">
		<input type="hidden" name="no" value="${param.no }">
		<p>
			정말 삭제하시겠습니까??<br>
			<input type="submit" name="submit" value="예">
			<input type="button" name="submit" value="아니요">
		</p>
	</form>
</body>
</html>