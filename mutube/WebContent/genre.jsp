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
    	<jsp:include page="/particular/header.jsp"></jsp:include>
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
										<td><a href="buttonAction.jsp?cat=genre&value=blues"><img src="Image/genre/blues.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=rock"><img src="Image/genre/rock.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=jazz"><img src="Image/genre/jazz.png" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=R&B"><img src="Image/genre/R&B.png" alt="sorry" name="value"></a></td>
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
										<td><a href="buttonAction.jsp?cat=genre&value=funk"><img src="Image/genre/funk.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=hiphop"><img src="Image/genre/hiphop.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=metal"><img src="Image/genre/metal.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=edm"><img src="Image/genre/edm.jpg" alt="sorry" name="value"></a></td>
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
										<td><a href="buttonAction.jsp?cat=genre&value=classic"><img src="Image/genre/classic.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=gospel"><img src="Image/genre/gospel.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=band"><img src="Image/genre/band.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=genre&value=fusion jazz"><img src="Image/genre/fusion_jazz.jpg" alt="sorry" name="value"></a></td>
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