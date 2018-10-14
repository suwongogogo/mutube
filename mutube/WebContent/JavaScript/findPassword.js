function move() {
	var form = document.getElementById("form");
	var id = document.getElementById("id").value;
	var name = document.getElementById("name").value;
	var email = document.getElementById("email").value;
	var check = true;
	
	if(email.trim() == "") {
		check = false;
		document.getElementById("email-error").innerHTML = "이메일을 입력하세요.";
	} else if (email.indexOf("@") == -1) {
		check = false;
		document.getElementById("email-error").innerHTML = "이메일 형식을 지켜주세요.";
	} else if (email.lastIndexOf(".com") == -1 || email.lastIndexOf(".kr") == -1) {
		check = false;
		document.getElementById("email-error").innerHTML = "이메일 형식을 지켜주세요.";
	} else {
		document.getElementById("email-error").innerHTML = "";
	}
	
	if (email.lastIndexOf(".com") != -1 || email.lastIndexOf(".kr") != -1) {
		check = true;
		document.getElementById("email-error").innerHTML = "";
	}
	
	if(id.trim() == "") {
		check = false;
		document.getElementById("id-error").innerHTML = "아이디를 입력하세요.";
	} else {
		document.getElementById("id-error").innerHTML = "";
	}
	if(name.trim() == "") {
		check = false;
		document.getElementById("name-error").innerHTML = "이름을 입력하세요.";
	} else {
		document.getElementById("name-error").innerHTML = "";
	} 
	
	if(check) {
		form.submit();
	}
}

function moveMain() {
	location.href = "/mutube/Main.jsp";
}