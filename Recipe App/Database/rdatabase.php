<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "recipe";

$con = mysqli_connect($servername, $username, $password, $dbname);

$data=file_get_contents("php://input");
$data1=json_decode($data,true);
$inp=$data1["data"];
var_dump($inp);
for ($i=0; $i <count($inp) ; $i++) { 
    	# code...
   	$rname=$inp[$i]["rname"];
   	$rdesc=$inp[$i]["rdesc"];
   	$ptime=$inp[$i]["ptime"];
   	$ctime=$inp[$i]["ctime"];
$sql1 = "INSERT INTO `recipe_table`(`recipe_id`, `recipe_name`, `recipe_description`, `prep_time`, `cook_time`) VALUES ('','$rname','$rdesc','$ptime','$ctime')";
// var_dump($sql1);
//$rid="SELECT MAX(recipe_id) FROM recipe_table";

//echo $rid;
	$query= mysqli_query($con,$sql1);}



	if ($query) 
		{
			echo "Data inserted successfully";
			
		}
// $sql= "SELECT `u_id` FROM `user_table` ";
//echo $sql;

?>