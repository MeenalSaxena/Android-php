<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "recipe";

$con = mysqli_connect($servername, $username, $password, $dbname);

$data=file_get_contents("php://input");
$data1=json_decode($data,true);
$inp=$data1["data"];
//var_dump($inp);
$sql1;
for ($i=0; $i <count($inp) ; $i++) { 
    	# code...
   	$rname=$inp[$i]["rname"];
   	$rdesc=$inp[$i]["rdesc"];
   	$ptime=$inp[$i]["ptime"];
	   $ctime=$inp[$i]["ctime"];
	   
	   // code to upload file start
	   // localhost\renew\Android-php\recipe_app\database\sdatabase.php

	   $data=$inp[$i]["img"];
	   $data = str_replace('data:image/jpeg;base64,', '', $data);
	   $data = str_replace(' ', '+', $data);
	   $data = base64_decode($data);
	   $file = 'images/'.$rname . '.jpg';
	   echo $file;
	   echo $data;
	   $success = file_put_contents($file, $data);
	  // echo $success;	
	//    $data = base64_decode($data); 
	//    $source_img = imagecreatefromstring($data);
	//    $rotated_img = imagerotate($source_img, 90, 0); 
	//    $file = 'images/'. rand(). '.jpg';
	//    $imageSave = imagejpeg($rotated_img, $file, 10);
	//    imagedestroy($source_img);


// file upload end
$sql1 = "INSERT INTO `recipe_table`(`recipe_id`, `recipe_name`, `recipe_description`, `prep_time`, `cook_time`,`image_url`) VALUES ('','$rname','$rdesc','$ptime','$ctime','$file')";
//$rid="SELECT MAX(recipe_id) FROM recipe_table";

//echo $rid;
	$query= mysqli_query($con,$sql1);
	var_dump($query);

// var_dump($sql1);

		
}


// $sql= "SELECT `u_id` FROM `user_table` ";
//echo $sql;

?>