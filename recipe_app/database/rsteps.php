<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
   
        <link rel="stylesheet" type="text/css" href="style.css">
    
</head>
<body >
  <?php
  $servername = "localhost";
$username = "root";
$password = "";
$dbname = "recipe";


$con = mysqli_connect($servername, $username, $password, $dbname);

$sql = "SELECT * FROM recipe_table ";
// echo $sql;
$result = mysqli_query($con, $sql);
if ($result != false) {
     while($row = $result->fetch_assoc()) {
        $rid=$row["recipe_id"];
        // echo $rid;
    }
    }
  ?>
    <button onclick="add()">Add Steps</button>
    

<div id="main">

</div>    
<button onclick="submitAll()">Submit</button>
</body>
<script >
 var count=0;   
function add ()
{
  count++;
    var form = document.createElement("form");
    form.method="";
    form.action="";
    
    var snum=document.createElement("input");
    snum.type="text";
    snum.value=count;
    snum.style.display="none";
    form.appendChild(snum);

    var sdesc = document.createElement("textarea");
    sdesc.name="sdesc";
    sdesc.placeholder="description";
    form.appendChild(sdesc);

    var stime = document.createElement("input");
    stime.type="text";
    stime.name="stime";
    stime.placeholder="time";
    form.appendChild(stime);

    var rid=document.createElement("input");
    rid.type="hidden";
    rid.value=<?php echo json_encode($rid); ?>;;
    form.appendChild(rid);


    document.getElementById("main").appendChild(form);
}
// function submitAll()
//{
 //    var allForms= document.getElementsByTagName("form");
  //       for(i=0;i<allForms.length;i++)
  //   {
  //       allForms[i].submit();
  //   }
// }

function createStep(rid,snum,sdesc,stime)
{
    this.rid=rid;
    this.snum=snum;
    this.stime = stime;
    this.sdesc=sdesc;
    
}

function submitAll ()
{
    var arr =[];
    var allForms= document.getElementsByTagName("form");
     for(i=0;i<allForms.length;i++)
     {
        var rid=allForms[i].getElementsByTagName("input")[2];
        var snum=allForms[i].getElementsByTagName("input")[0];
        var stime=allForms[i].getElementsByTagName("input")[1];   
        var sdesc=allForms[i].getElementsByTagName("textarea")[0];   
        var step= new createStep(rid.value,snum.value,sdesc.value,stime.value);
        arr.push(step);
     }
     console.log(arr);
     var xmlhttp = new XMLHttpRequest();
xmlhttp.open("POST", "sdatabase.php", true);
xmlhttp.setRequestHeader("Content-type", "text/JSON");
xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
xmlhttp.onreadystatechange = function() {
    if (this.readyState === 4 || this.status === 200){ 
       // console.log("ha");
        console.log(this.responseText); // echo from php
    }       
};
xmlhttp.send(JSON.stringify({data:arr}));
// window.location="connect.php";
//     method:'post';
}
</script>

</html>