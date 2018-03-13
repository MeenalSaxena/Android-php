<?php 
$host='localhost';
$user='root';
$password='';
$db='update';
$con=mysqli_connect($host,$user,$password,$db);
$ID=$_POST['ID'];
$NAME=$_POST['name'];

$password=$_POST['pwd'];

$DATEOFBIRTH=$_POST['dob'];

$GENDER=$_POST['gender'];


$EMAIL=$_POST['email'];

$TELEPHONE=$_POST['tel'];

$ADDRESS=$_POST['add'];

if($con)
echo 'connected successfully';

$sql="insert into data(Name,password,DateofBirth,Gender,Email,Telephone,Address
) values( '$NAME','$password','$DATEOFBIRTH' ,'$GENDER', '$EMAIL','$TELEPHONE','$ADDRESS')";

$query =mysqli_query($con,$sql);



 ?>