function search() {
	var search = document.getElementById("search-result").value;
	
	if(search.trim() == "") {
		alert("검색어를 입력해주세요!");
	} else {
		document.getElementById("search-form").submit();
	}
}