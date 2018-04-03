<!DOCTYPE html>
<html>
<head>
	<title>Main Page</title>
</head>
<body>
	<form action="rsteps.php" method="post" id="mainForm" enctype="multipart/form-data">
		
  		<input name="rname" type="text" placeholder="Recipe Name"><br>
  		<textarea name="rdesc" type="textarea" placeholder="Recipe Description"></textarea><br>
  		<input name="ptime" type="text" placeholder="Preparation Time"><br>
  		<input name="ctime" type="text" placeholder="Cooking Time"><br>
		<input type="file" name="image" id="image" onchange="loadFile(event)">
		<img style="height:150px" id="output"/> <br>
  		<input type="button" name="rbutton" value="Next" onclick="submitAll()">
	</form>
</body>
<script>
//  function to get image as data to show preview and send to DB
var loadFile = function(event) {
    var reader = new FileReader();
    reader.onload = function(){
      var output = document.getElementById('output');
      output.src = reader.result;
    };
    reader.readAsDataURL(event.target.files[0]);
  };
//  end of a functiom

	function createStep(rname,rdesc,ptime,ctime,img)
		{
		    this.rname=rname;
		    this.rdesc=rdesc;
		    this.ptime=ptime;
		    this.ctime=ctime;
			this.img=img;

		}

		function submitAll ()
		{	
		    var arr =[];
		    var allForms= document.getElementsByTagName("form");
		     for(i=0;i<allForms.length;i++)
		     {
		     	var rname=allForms[i].getElementsByTagName("input")[0];
		     	var ctime=allForms[i].getElementsByTagName("input")[2];
		     	var ptime=allForms[i].getElementsByTagName("input")[1];
		     	var rdesc=allForms[i].getElementsByTagName("textarea")[0];
				 var image=allForms[i].getElementsByTagName("img")[0];
				
		     	var step= new createStep(rname.value, rdesc.value, ptime.value, ctime.value,image.src);
        		arr.push(step);		        
		     }
		     console.log(arr);
		     var xmlhttp = new XMLHttpRequest();
			 xmlhttp.open("POST", "rdatabase.php", true);
			 xmlhttp.setRequestHeader("Content-type", "text/JSON");
			 xmlhttp.setRequestHeader("Access-Control-Allow-Origin", "*");
			 xmlhttp.onreadystatechange = function() {
		     if (this.readyState === 4 || this.status === 200){ 
		       // console.log("ha");
		        console.log(this.responseText); // echo from php
		        document.getElementById('mainForm').submit();
		     }       
		};
		xmlhttp.send(JSON.stringify({data:arr}));
		//window.location="rsteps.php";
		//method:'post';
		}
</script>

</html>