function changePassword() {
	var form = document.getElementById("form");
	var oldPassword = document.getElementById("old-password").value;
	var newPassword = document.getElementById("new-password").value;
	var newRePassword = document.getElementById("new-confirm-password").value;
	var check = true;
	
	if(oldPassword.trim() == "") {
		document.getElementById("old-error").innerHTML = "현재 비밀번호를 입력해주세요.";
		check = false;
	}
	if(newPassword.trim() == "") {
		document.getElementById("new-error").innerHTML = "바꿀 비밀번호를 입력해주세요.";
		check = false;
	}
	if(newRePassword.trim() == "") {
		document.getElementById("re-new-error").innerHTML = "비밀번호 확인을 입력해주세요.";
		check = false;
	}
	if(check) {
		form.submit();
	}
}

function moveMain() {
	location.href="/mutube/myPage.jsp";
}