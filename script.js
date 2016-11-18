var ActiveCourseIDForm = false;
var socket = io();
console.log("IS THE SCRIPT WORKING");
socket.on('check password', function(msg) {
    console.log("MESSAGE RECIEVED");
    document.getElementById("demo").innerHTML = "PLEASE ENTER CORRECT USER";
    console.log("msg = ", msg);
    if (msg == "CORRECT") {
        document.getElementById("demo").innerHTML = "PLEASE CHOOSE A COURSE BELOW";
        CourseIDForm();
    }
});

$('form').submit(function() {
    socket.emit('chat message', $('#m').val());
    $('#m').val('');
    return false;
});
socket.on('chat message', function(msg) {
    $('#messages').append($('<li>').text(msg));
});
socket.on('check password', function(msg) {
    console.log("MESSAGE RECIEVED");
    document.getElementById("demo").innerHTML = "PLEASE ENTER CORRECT USER";
    if (msg == "CORRECT") {
        CourseIDForm();
    }
});

function myFunction() {
    socket.emit('button', "WOW you can press a button");
    var x = document.getElementById("frm1");
    var text = "";
    var i;
    console.log(x.elements[0].value);
    socket.emit('UserAndPass', [x.elements[0].value, x.elements[1].value]);
    console.log([x.elements[0].value, x.elements[1].value]);
    if (ActiveCourseIDForm == true) {
        CourseIDForm();
    }
    if (x.elements[0].value == "user1" || x.elements[0].value == "user2" || x.elements[0].value == "user3") {
        document.getElementById("demo").innerHTML = "CORRECT USERNAME"; //text;
        document.getElementById("courseSelector").style = "";
    } else {
        document.getElementById("demo").innerHTML = "Incorrect Username"; //text;
        document.getElementById("courseSelector").style = "display:none;";
    }
}

function CourseIDForm() {
    document.getElementById("demo").innerHTML = "Please Sign Up Below"; //text;
    ActiveCourseIDForm = true;

}

function RemoveCourseIDForm() {
    console.log("REMOVE REMOVE REMOVE");
    CouseIDInput = "PIE";
    if (ActiveCourseIDForm == true) {
        var CouseIDForm = document.remove("form");
        CouseIDForm.removeAttr('method', "post");

        var CouseIDInput = document.remove("input"); //input element, text
        CouseIDInput.removeAttr('type', "text");
        CouseIDInput.removeAttr('name', "username");

        var CouseIDSubmitButton = document.remove("input"); //input element, Submit button
        CouseIDSubmitButton.removeAttr('type', "submit");
        CouseIDSubmitButton.removeAttr('value', "Submit");

        CouseIDForm.remove(CouseIDInput);
        CouseIDForm.remove(CouseIDSubmitButton);

        document.getElementsByTagName('body')[0].appendChild(CouseIDForm);
    }
}

var i = 0;

function increment() {
    i += 1;
}
//EXPERIMENTAL Code
Element.prototype.remove = function() {
    this.parentElement.removeChild(this);
}
NodeList.prototype.remove = HTMLCollection.prototype.remove = function() {
        for (var i = this.length - 1; i >= 0; i--) {
            if (this[i] && this[i].parentElement) {
                this[i].parentElement.removeChild(this[i]);
            }
        }
    }
    //END EXPERIMENTAL CODE

function removeElement(elementID) {
    document.getElementById(elementID).remove();
}

function nameFunction(elementID) {
    var r = document.createElement('span');
    var y = document.createElement("INPUT");
    y.setAttribute("id", "textBox" + i);
    y.setAttribute("type", "text");
    y.setAttribute("placeholder", "Enter Course ID Here");
    var g = document.createElement("button");
    var t = document.createTextNode("REMOVE");
    g.appendChild(t);
    g.setAttribute("src", "delete.png");
    increment();
    y.setAttribute("Name", "textelement_" + i);
    r.appendChild(y);
    g.setAttribute("onclick", "removeElement(" + elementID + ",'id_" + i + "')");
    //g.setAttribute("onclick", "removeElement('myForm','id_" + i + "')");
    //r.appendChild(g);
    r.setAttribute("id", "id_" + i);
    document.getElementById(elementID).appendChild(r);
    linebreak = document.createElement("br");
    document.getElementById(elementID).appendChild(linebreak);
}

function textareaFunction(elementID) {
    var r = document.createElement('span');
    var y = document.createElement("TEXTAREA");
    y.setAttribute("id", "textBox" + i);
    var g = document.createElement("button");
    var t = document.createTextNode("REMOVE");
    g.appendChild(t);
    y.setAttribute("cols", "17");
    y.setAttribute("placeholder", "Course Code");
    g.setAttribute("src", "delete.png");
    increment();
    y.setAttribute("Name", "textelement_" + i);
    r.appendChild(y);
    g.setAttribute("onclick", "removeElement(" + elementID + ",'id_" + i + "')");
    //g.setAttribute("onclick", "removeElement('myForm','id_" + i + "')");
    //r.appendChild(g);
    r.setAttribute("id", "id_" + i);
    document.getElementById(elementID).appendChild(r);
    linebreak = document.createElement("br");
    document.getElementById(elementID).appendChild(linebreak);
}


function resetElements(elementID) {
    document.getElementById(elementID).innerHTML = '';
}

function PrintInputs() {
    console.log(i);
    for (var j = 0; j < i; j++) {
        console.log(j);
        console.log(document.getElementById("textBox" + j).value);
        //console.log(document.getElementById("id_" + j));
    }
}
