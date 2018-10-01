// 드롭다운
var checked1 = false;
var checked2 = false;
var checked3 = false;

function view1() {
    checked1 = !checked1;
    if(checked1 === true) {
        var hidden = document.getElementById("hidden1");
        hidden.id = "view1";
    } else if(checked1 === false) {
        var view = document.getElementById("view1");
        view.id = "hidden1";
    }
}

function view2() {
    checked2 = !checked2;
    if(checked2 === true) {
        var hidden = document.getElementById("hidden2");
        hidden.id = "view2";
    } else if(checked2 === false) {
        var view = document.getElementById("view2");
        view.id = "hidden2";
    }
}

function view3() {
    checked3 = !checked3;
    console.log(checked3)
    if(checked3 === true) {
        var hidden = document.getElementById("hidden3");
        hidden.id = "view3";
    } else if(checked1 === false) {
        var view = document.getElementById("view3");
        view.id = "hidden3";
    }
}