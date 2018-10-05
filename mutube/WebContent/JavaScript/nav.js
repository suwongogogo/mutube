var genre = false;
var instrument = false;
var musician = false;

function openTest(tag,name) {

    if (name == '장르') {
        if (genre) {
            genre = false;
            tag.style = "color: black;";
            tag.nextSibling.nextSibling.style = "display: none;";
            tag.parentElement.previousSibling.previousSibling.style = "background-color: white;";
        } else {
            genre = true;
            tag.style = "color: red;";
            tag.nextSibling.nextSibling.style = "display: block;";
            tag.parentElement.previousSibling.previousSibling.style = "background-color: red;";
        }
    } else if (name == '악기') {
        if (instrument) {
            instrument = false;
            tag.style = "color: black;";
            tag.nextSibling.nextSibling.style = "display: none;";
            tag.parentElement.previousSibling.previousSibling.style = "background-color: white;";
        } else {
            instrument = true;
            tag.style = "color: red;";
            tag.nextSibling.nextSibling.style = "display: block;";            
            tag.parentElement.previousSibling.previousSibling.style = "background-color: red;";
        }
    } else if (name == '뮤지션') {
        if (musician) {
            musician = false;
            tag.style = "color: black;";
            tag.nextSibling.nextSibling.style = "display: none;";
            tag.parentElement.previousSibling.previousSibling.style = "background-color: white;";
        } else {
            musician = true;
            tag.style = "color: red;";
            tag.nextSibling.nextSibling.style = "display: block;";
            tag.parentElement.previousSibling.previousSibling.style = "background-color: red;";
        }
    }
}