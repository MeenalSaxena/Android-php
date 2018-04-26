<?php
	include_once 'config.php';
	$data="SELECT  `recipe_name`,`recipe_description`,`image_url` FROM `recipe_table` ";
		$query1= mysqli_query($con,$data);
		while ($row=mysqli_fetch_assoc($query1)) {
			$data2[]=$row;
		}
		$arr = array('data' => $data2 );

		for($i=0;$i<count($arr["data"]);$i++)
		{
			// use your absolute url in place of this url

			$url="http://10.0.0.2/renew/Android-php/RecipeApp3/database/";

			// $url="http://localhost/recipe/Android-php/RecipeApp3/database/";

			$arr["data"][$i]["image_url"]=$url.$arr["data"][$i]["image_url"];
		}
	echo json_encode($arr);

	//`recipe_id`, `recipe_name`, `recipe_description`,
?>