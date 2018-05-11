<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "facebook";

$con = mysqli_connect($servername, $username, $password, $dbname);

mysqli_select_db($con,"facebook");
// $data=file_get_contents("php://input");
// $data1=json_decode($data,true);
// $inp=$data1["data"];
// var_dump($inp);
if( isset($_POST["arr"]) ) {
$data = json_decode($_POST["arr"],true); 
// var_dump($data);
echo json_encode($data);
	$id=$data["id"];
	$name=$data["name"];
	$first_name=$data["first_name"];
	$last_name=$data["last_name"];
	$gender=$data["gender"];
	$link=$data["link"];
	$time=$data["timezone"];
	$local=$data["locale"];
	$update=$data["updated_time"];
	$verified=$data["verified"];
}
if($con)
	 {
	 	$sql="INSERT INTO `user_data`(`ID`,`Name`, `First_name`, `Last_name`, `Gender`,`Link`,`Time_zone`,`Locale`,`Updated_time`,`Verified`) VALUES ('$id','$name','$first_name','$last_name','$gender','$link','time','local','update','verified') ";
// $sql="insert into user_data(Name,First_name,Last_name,Gender) values( 8,`$name`,`$first_name`,'$last_name','$gender')";

$query =mysqli_query($con,$sql);
var_dump($query) ;

}


 ?>