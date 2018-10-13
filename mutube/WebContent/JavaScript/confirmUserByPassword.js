function move() {
	var form = document.getElementById("confirm-password-form");
	var password = document.getElementById("password").value;
	if(password.trim() == "") {
		alert("패스워드를 입력해주세요.");ㅣ
	} else {
		form.submit();
	}
}

function moveMain() {
	console.log("asd");
	location.href="/mutube/Main.jsp";
}