<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "recipe";
$con = mysqli_connect($servername, $username, $password, $dbname);


$data=file_get_contents("php://input");
$data1=json_decode($data,true);
$inp=$data1["data"];
//$a=array_values($inp[0]);
//$rid=$a[0];
$rid=4;
$sql="SELECT * FROM `recipe_steps` WHERE `recipe_id`=$rid";
 // echo $sql;
$result = mysqli_query($con, $sql);
// // if ($result != false) {
//      while($row = $result->fetch_assoc()) {
//         $sd=$row["step_description"];
//     }
//         echo json_encode($sd);
//     // }

//echo $data;
        if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
       echo json_encode($row);
    }
}
?>
