<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "recipe";
$con = mysqli_connect($servername, $username, $password, $dbname);

$rid=$_REQUEST['rid'];
$sql="SELECT `step_description` FROM `recipe_steps` WHERE `recipe_id`=$rid";
$run=mysqli_query($con,$sql);
while ($row=mysqli_fetch_assoc($run)) {
	$data[]=$row;
}
echo json_encode($data);
//echo $data;
?>