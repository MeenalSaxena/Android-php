var express = require("express");
var app = express();
var port = 3000;
var bodyParser = require('body-parser');
var mysql = require('mysql');
var myData='';

  
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

var mongoose = require("mongoose");
mongoose.Promise = global.Promise;
mongoose.connect("mongodb://localhost:27017/node-demo");
var nameSchema = new mongoose.Schema({
    firstName: String,
    lastName: String
});
var User = mongoose.model("User", nameSchema);

app.get("/", (req, res) => {
    res.sendFile(__dirname + "/index.html");
});

app.post("/addname", (req, res) => {
myData = new User(req.body);
    myData.save()
        .then(item => {
            res.send("Name saved to database");
        })
        .catch(err => {
            res.status(400).send("Unable to save to database");
        });
        console.log(myData);
var con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "root",
    database: "mydb"
  });

con.connect(function(err) {
    if (err) throw err;
    console.log("Connected!");
    console.log(req.body.id);
    var sql ='INSERT INTO Data (id,firstName,lastName) VALUES ("'+req.body._id+'","'+req.body.firstName+'","'+req.body.lastName+'")';
  // var sql="Select * From Data" ; 
   con.query(sql, function (err, result) {
      if (err) throw err;
      console.log("Insert");
      console.log(result);
    });
});
       
});

app.listen(port, () => {
    console.log("Server listening on port " + port);
});

