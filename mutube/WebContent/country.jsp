<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/main.css" />
<link rel="stylesheet" href="CSS/bootstrap.css">
<link rel="stylesheet" href="CSS/custom.css">
<link rel="stylesheet" href="CSS/public-list.css">
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic|Open+Sans" rel="stylesheet">
<title>국가</title>
</head>
<body>
	<div class="wrapper">
    	<jsp:include page="/particular/header.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board">
		   		<div class="col-xs-9">
				<!-- Tab panes -->
				<div class="tab-content">
					<div class="tab-pane active" id="country">
						<div class="content">
							<h2>나라</h2>
							<hr class="list-line">
							<table class="country">
								<tr>
									<td><a href="/post/search?keyword=korea&category=country"><img src="Image/country/south_korea.png" alt="sorry"></a></td>
									<td><a href="/post/search?keyword=japan&category=country"><img src="Image/country/japan.png" alt="sorry"></a></td>
									<td><a href="/post/search?keyword=USA&category=country"><img src="Image/country/USA.png" alt="sorry"></a></td>
									<td><a href="/post/search?keyword=germanty&category=country"><img src="Image/country/germany.png" alt="sorry"></a></td>
								</tr>
								<tr>
									<td>대한민국</td>
									<td>일본</td>
									<td>미국</td>
									<td>독일</td>
								</tr>
							</table>
							<br> <br> <br> <br>
							<table class="country">
								<tr>
									<td><a href="/post/search?keyword=china&category=country"><img src="Image/country/china.png" alt="sorry"></a></td>
									<td><a href="/post/search?keyword=spain&category=country"><img src="Image/country/spain.png" alt="sorry"></a></td>
									<td><a href="/post/search?keyword=england&category=country"><img src="Image/country/England.jpg" alt="sorry"></a></td>
									<td><a href="/post/search?keyword=france&category=country"><img src="Image/country/france.png" alt="sorry"></a></td>
								</tr>
								<tr>
									<td>중국</td>
									<td>스페인</td>
									<td>영국</td>
									<td>프랑스</td>
								</tr>
							</table>
							<br> <br> <br> <br>
							<table class="country">
								<tr>
									<td><a href="/post/search?keyword=russia&category=country"><img src="Image/country/russia.png" alt="sorry"></a></td>
								</tr>
								<tr>
									<td>러시아</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		   	</div>
    	</div>
    	<jsp:include page="/particular/footer.jsp"></jsp:include>
    </div>
</body>
</html>