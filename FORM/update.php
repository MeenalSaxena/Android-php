<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "update";
$conn = mysqli_connect($servername, $username, $password, $dbname);
$ID = $_POST['ID'];
$NAME=$_POST['textnames'];

$password=$_POST['pwd'];

$DATEOFBIRTH=$_POST['dob'];

$GENDER=$_POST['gender'];


$EMAIL=$_POST['email'];

$TELEPHONE=$_POST['tel'];

$ADDRESS=$_POST['add'];

// Create connection

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$sql = "UPDATE data SET  `Name` ='$NAME' , `password` = '$password' ,`DateofBirth`='$DATEOFBIRTH' ,`Gender`= '$GENDER',`Email`='$EMAIL',`Telephone`='$TELEPHONE',`Address`='$ADDRESS' WHERE ID='$ID'";

if ($conn->query($sql) === TRUE) {
    echo "Record updated successfully";
} else {
    echo "Error updating record: " . $conn->error;
}

$conn->close();
?>