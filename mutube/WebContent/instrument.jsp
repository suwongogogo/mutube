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
<title>악기</title>
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
						<div class="tab-pane active" id="instrument">
							<div class="content">
								<h2>악기</h2>
								<hr class="">
								<table class="instrument">
									<tr>
										<td><a href="buttonAction.jsp?cat=instrument&value=guitar"><img src="Image/instrument/guitar.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=instrument&value=bass"><img src="Image/instrument/bass.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=instrument&value=vocal"><img src="Image/instrument/vocal.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=instrument&value=drum"><img src="Image/instrument/drum.jpg" alt="sorry" name="value"></a></td>
									</tr>
									<tr>
										<td>Guitar</td>
										<td>Bass</td>
										<td>Vocal</td>
										<td>Drum</td>
									</tr>
								</table>
								<br> <br> <br> <br>
								<table class="instrument">
									<tr>
										<td><a href="buttonAction.jsp?cat=instrument&value=piano"><img src="Image/instrument/piano.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=instrument&value=trumpet"><img src="Image/instrument/trumpet.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=instrument&value=saxophone"><img src="Image/instrument/saxophone.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=instrument&value=trombone"><img src="Image/instrument/trombone.jpg" alt="sorry" name="value"></a></td>
									</tr>
									<tr>
										<td>Piano</td>
										<td>Trumpet</td>
										<td>SaxoPhone</td>
										<td>Trombone</td>
									</tr>
								</table>
								<br> <br> <br> <br>
								<table class="instrument">
									<tr>
										<td><a href="buttonAction.jsp?cat=instrument&value=acoustic guitar"><img src="Image/instrument/acoustic_guitar.jpg" alt="sorry" name="value"></a></td>
										<td><a href="buttonAction.jsp?cat=instrument&value=contrabass"><img src="Image/instrument/Contrabass.jpg" alt="sorry" name="value"></a></td>
									</tr>
									<tr>
										<td>Acoustic Guitar</td>
										<td>ContraBass</td>
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