
<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "recipe";


$con = mysqli_connect($servername, $username, $password, $dbname);
$data=file_get_contents("php://input");
$data1=json_decode($data,true);
$inp=$data1["data"];
// var_dump($inp);
$db_found = mysqli_select_db($con, $dbname);

for ($i=0; $i <count($inp) ; $i++) 
   {
   		$r_id=$inp[$i]["rid"];
	   	$snum=$inp[$i]["snum"];
	   	$stime=$inp[$i]["stime"];
	   	$sdesc=$inp[$i]["sdesc"];
		$sql1 = "INSERT INTO `recipe_steps`(`step_id`, `recipe_id`, `step_number`, `step_description`, `step_time`) VALUES('',$r_id,$snum,'$sdesc',$stime)";



		$query= mysqli_query($con,$sql1);
		if ($query) 
		{
			echo "Data inserted successfully";			
		}
	}


	

		
?>

