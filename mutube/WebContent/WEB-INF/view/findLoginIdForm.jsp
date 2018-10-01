<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>아이디 찾기</title>
<link rel="stylesheet" type="text/css" media="screen"
	href="CSS/findForm.css" />
</head>
<body>
	<div class="logo">
		<a href="mutube/Main.jsp"><img src="/mutube/Image/수원이얼굴.jpg"></a>
	</div>
	<div class="findForm-container" style="height: 220px">
		<form action="#" method="post">
			<div class="findLoginId" class="input-container">
				<div class="input-container">
					<p>ID</p>
					<input type="text" name="name" class="find"
						placeholder="아이디를 입력해주세요">
					<div class="underline"></div>
					<span class="error"><c:if test="${errors.loginId }">아이디를 입력하세요.</c:if></span>
				</div>
				<div class="input-container">
					<p>E-mail</p>
					<input type="text" name="email" class="find"
						placeholder="이메일을 입력해주세요">
					<div class="underline"></div>
					<span class="error"><c:if test="${errors.email }">이메일를 입력하세요.</c:if></span>
				</div>
				<input type="submit" value="아이디 찾기" class="submit">
			</div>
		</form>
	</div>
</body>
</html>