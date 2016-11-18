None of the shit is working I either need to get the code of the
  origional chat socket.io example or rever a commit.
Upgrade the users.json to be a SQL database insead of a JSON file.
Upgrade the server to use encryption point to point.
Use correct Log In Procedules so the system can not be hacked.

Right now all the modules all work but they are not communicating with one another.
Using the NodeJS shell JS module NodeJS can communicate with Java by running
  the JAVA app with an argument of a JSON or txt file.

Right now the NodeJS Server and the Java application communicate using text files
  This is a really really bad design and we want to use JSON (Javascript Object
  Notation) to communicate between NodeJS. Java has many JSON interpreters.
