<html>

<head>



<style>

table

{

border-style:solid;

 border: 1px solid black;
    border-collapse: collapse;

border-color:black;

}

</style>

</head>

<body bgcolor="#EEFDEF">

	<?php
$host = "localhost";
$user = "root";
$password = "";
$db = "update";
$search = $_POST['find'];
// Create connection
$con =  mysqli_connect($host, $user, $password, $db);
// Check connection
if ($con) {
	$sql = "SELECT * FROM `data` WHERE `Name` = '$search'";

$result =$con->query($sql);
echo "<table border='1'>

<tr>

<th>Name</th>
<th>password</th>
<th>DateofBirth</th>
<th>Gender</th>
<th>Email</th>
<th>Telephone</th>
<th>Address</th>

</tr>";
if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
    	 echo "<tr>";

  echo "<td>" . $row['Name'] . "</td>";

  echo "<td>" . $row['password'] . "</td>";

  echo "<td>" . $row['DateofBirth'] . "</td>";
  echo "<td>" . $row['Gender'] . "</td>";
  echo "<td>" . $row['Email'] . "</td>";
  echo "<td>" . $row['Telephone'] . "</td>";
  echo "<td>" . $row['Address'] . "</td>";

  echo "</tr>";

  }
      
                

        echo "</table>";

    }
}
    else{
     	echo "no data match";
  }

?>