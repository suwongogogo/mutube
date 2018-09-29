function check() {
    var id = document.getElementsByName("loginId")[0].value;
    var name = document.getElementsByName("name")[0].value;
    var password = document.getElementsByName("password")[0].value;
    var confirmPassword = document.getElementsByName("confirmPassword")[0].value;
    var email = document.getElementsByName("email")[0].value;
    var error = true;
    var checkEmail = email.indexOf('@');

    for(var i=0; i<5; i++) {
        document.getElementsByClassName("error")[i].innerHTML = "";
    }

    if(id == '') {
        document.getElementsByClassName("error")[0].innerHTML = "아이디를 입력하세요.";
        error = false;
    }
    if(name == '') {
        document.getElementsByClassName("error")[1].innerHTML = "이름을 입력하세요.";
        error = false;
    }
    if(password == '') {
        document.getElementsByClassName("error")[2].innerHTML = "비밀번호를 입력하세요.";
        error = false;
    }
    if(confirmPassword == '') {
        document.getElementsByClassName("error")[3].innerHTML = "비밀번호 확인을 입력하세요.";
        error = false;
    }
    if(checkEmail == -1 || email.substring(checkEmail+1).indexOf('.com') == -1) {
        document.getElementsByClassName("error")[4].innerHTML = "이메일 형식이 맞지 않습니다.";
        error = false;
    }
    if(email == '') {
        document.getElementsByClassName("error")[4].innerHTML = "이메일을 입력하세요.";
        error = false;
    }
    if(password != confirmPassword) {
        document.getElementsByClassName("error")[3].innerHTML = "비밀번호가 같지않습니다.";
        error = false;
    }
    
    if(error === true) {
        document.getElementById("form").submit();
    }

}