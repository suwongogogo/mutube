function checkAll() {
    var all = document.getElementsByName("delete");
    var check = document.getElementById("allDelete").checked;

    if(check == true) {
        for (let i = 0; i < all.length; i++) {
            all[i].checked = true;
        }
    } else if(check == false) {
        for (let i = 0; i < all.length; i++) {
            all[i].checked = false;
        }
	}
}