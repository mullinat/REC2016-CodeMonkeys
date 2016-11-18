//Dependencies
var fs = require("fs");
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);
// Read Synchrously
console.log("\n *START* \n");
var user_database_string = fs.readFileSync("users.json");
var user_database = JSON.parse(user_database_string);
//console.log("Output Content : \n"+ user_database_string);
console.log("\n *RUNNING SOCKET SERVER* \n");
console.log("user1 = " + user_database["user1"]);
console.log("user2 = " + user_database["user2"]);
console.log("user3 = " + user_database["user3"]);
//First some express stuff
app.get('/', function(req, res) {
    res.sendFile(__dirname + '/index.html');
});
app.get('/', function(req, res) {
    res.sendFile(__dirname + './script.js');
});
//SOCKET IO AND FORM STUFF
io.on('connection', function(socket) {
    console.log("SOMEONE CONNECTED");
    socket.on('chat message', function(msg) {
        io.emit('chat message', msg);
        console.log("Message Posted On Server = " + msg);
    });
    socket.on('button', function(msg) {
        console.log("button socket activated");
    });
    socket.on('UserAndPass', function(msg) {
        console.log("Username : " + msg);
        CheckPassword(user_database, msg[0], "password");
    });
    socket.on('check password', function(msg) {
        console.log("check password socket activated");
    });
});
//Host the HTTP server with the embeded javascript
http.listen(3000, function() {
    console.log('listening on *:3000');
});
//Check if password is in database
function CheckPassword(user_database, user, pass) {
    Profile = user_database[user];
    console.log(Profile);
    if (Profile == undefined) {
        io.emit('check password', "Please enter the correct username");
    } else {
        io.emit('check password', "CORRECT");
    }
}
