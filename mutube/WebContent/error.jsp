<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>에러</title>
</head>
<body>
<script type="text/javascript">
	var error = '<%=request.getParameter("")%>' or '${}';
	console.log(a);
	if(error == "") {
		alert("~~~를 하세요");
		location.href="원하는 페이지.jsp";
	}
</script>
</body>
</html>