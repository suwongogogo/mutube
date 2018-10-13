function move() {
	var oldId = document.getElementById("id").value;
	var oldName = document.getElementById("name").value;
	var oldEmail = document.getElementById("email").value;
	
	var check = true;
	
	if(oldId.trim() == "") {
		document.getElementById("id-error").innerHTML = "아이디를 입력해주세요.";
		check = false;
	}
	if(oldName.trim() == "") {
		document.getElementById("name-error").innerHTML = "이름을 입력해주세요.";
		check = false;
	}
	if(oldEmail.trim() == "") {
		document.getElementById("email-error").innerHTML = "이메일을 입력해주세요.";
		check = false;
	}
}