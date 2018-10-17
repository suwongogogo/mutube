<%@page import="User.DAO.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="CSS/googleMap.css" />
	<title>구글맵</title>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBmEVEtbPbxRC-hdnYV7awHEYAVwAa3laE&sensor=false">
	</script>
	
	<script src="JavaScript/googleMap.js"></script>
</head>

<body onload="initialize()">
	<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board">
		   		<div id="map_view" style="width: 500px; height: 300px;"></div>
		   	</div>	
    	</div>
    	<jsp:include page="/particular/footer.jsp"></jsp:include>
    </div>
</body>
</html>