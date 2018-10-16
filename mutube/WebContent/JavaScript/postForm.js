
var check = true;

function move() {
	var form = document.getElementById("form");
	
	if(check) {
		form.submit();
		check = false;
	}
}

function cancle() {
    var check = confirm("현재 페이지에서 이동하면 작성 중인 사항은 모두 사라집니다.");
    if(check) {
    	location.href="/mutube/post/list";
    }
}


function deleteImage(image){
	var parent = image.parentNode;
	parent.removeChild(image);
}
