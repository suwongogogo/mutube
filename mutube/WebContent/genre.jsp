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
<title>장르</title>
</head>
<body>
	<div class="wrapper">
    	<jsp:include page="/particular/header2.jsp"></jsp:include>
    	<div class="center">
        	<jsp:include page="/particular/nav.jsp"></jsp:include>
		   	<div class="board">
		   		<div class="col-xs-9">
					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane active" id="genre">
							<div class="content">
								<h2>장르</h2>
								<hr class="list-line">
								<table class="genre">
									<tr>
										<td><a href="/post/search?keyword=blues&category=genre"><img src="Image/genre/blues.jpg" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=rock&category=genre"><img src="Image/genre/rock.jpg" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=jazz&category=genre"><img src="Image/genre/jazz.png" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=R&B&category=genre"><img src="Image/genre/R&B.png" alt="sorry"></a></td>
									</tr>
									<tr>
										<td>Blues</td>
										<td>Rock</td>
										<td>Jazz</td>
										<td>R&B</td>
									</tr>
								</table>
								<br> <br> <br> <br>
								<table class="genre">
									<tr>
										<td><a href="/post/search?keyword=funk&category=genre"><img src="Image/genre/funk.jpg" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=hiphop&category=genre"><img src="Image/genre/hiphop.jpg" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=metal&category=genre"><img src="Image/genre/metal.jpg" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=edm&category=genre"><img src="Image/genre/edm.jpg" alt="sorry"></a></td>
									</tr>
									<tr>
										<td>Funk</td>
										<td>HipHop</td>
										<td>Metal</td>
										<td>EDM</td>
									</tr>
								</table>
								<br> <br> <br> <br>
								<table class="genre">
									<tr>
										<td><a href="/post/search?keyword=classic&category=genre"><img src="Image/genre/classic.jpg" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=gospel&category=genre"><img src="Image/genre/gospel.jpg" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=band&category=genre"><img src="Image/genre/band.jpg" alt="sorry"></a></td>
										<td><a href="/post/search?keyword=fusion jazz&category=genre jazz"><img src="Image/genre/fusion_jazz.jpg" alt="sorry"></a></td>
									</tr>
									<tr>
										<td>Classic</td>
										<td>Gospel</td>
										<td>Band</td>
										<td>Fusion Jazz</td>
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