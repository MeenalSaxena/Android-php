<?php
	include_once 'config.php';
	$data="SELECT  `image_url` FROM `recipe_table` ";
		$query1= mysqli_query($con,$data);
		while ($row=mysqli_fetch_assoc($query1)) {
			$data2[]=$row;
		}
		$arr = array('data' => $data2 );

	echo json_encode($arr);
	//`recipe_id`, `recipe_name`, `recipe_description`,
?>