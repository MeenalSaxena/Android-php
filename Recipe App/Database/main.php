<!DOCTYPE html>
<html>
<head>
	<title>Main Page</title>
</head>
<body>
	<form action="rsteps.php" method="POST" id="mainForm">
		
  		<input name="rname" type="text" placeholder="Recipe Name"><br>
  		<textarea name="rdesc" type="textarea" placeholder="Recipe Description"></textarea><br>
  		<input name="ptime" type="text" placeholder="Preparation Time"><br>
  		<input name="ctime" type="text" placeholder="Cooking Time"><br>
  		<input type="button" name="rbutton" value="Next" onclick="submitAll()">
	</form>
</body>
<script>
	function createStep(rname,rdesc,ptime,ctime)
		{
		    this.rname=rname;
		    this.rdesc=rdesc;
		    this.ptime=ptime;
		    this.ctime=ctime;

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
		     	var step= new createStep(rname.value, rdesc.value, ptime.value, ctime.value);
        		arr.push(step);		        
		     }
		     console.log(arr);
		     var xmlhttp = new XMLHttpRequest();
			 xmlhttp.open("POST", "http://localhost/renew/rdatabase.php", true);
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
		
		}
</script>

</html>