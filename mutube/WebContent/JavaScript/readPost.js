function commnetLimit() {
	console.log(event.keyCode);
	var keyCode = event.keyCode;
	var comment = document.getElementById("comment").value;
	var length = comment.length;
	var str="";
		
	
	if(length > 201) {
		if(keyCode != 8) {
			alert("댓글 최대 글자 수가 초과 되었습니다!")
			document.getElementById("comment").value = comment.substr(0,200);
		}
	}
}