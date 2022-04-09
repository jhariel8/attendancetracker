// Makes a request to generate a class meeting
function generateClassMeeting() {
    var classid = document.forms["genClassForm"]["sectionid"];
    var duration = document.forms["genClassForm"]["duration"];

    console.log(duration.value);

    if(classid.value == "") {
        var error = document.getElementById("formerror");
        error.innerHTML = "A class ID must be provided.";
        return false;
    }
    if(duration.value == "" || duration.value < 1) {
        var error = document.getElementById("formerror");
        error.innerHTML = "You must give a duration that is longer than 0 hours.";
        return false; 
    }
}