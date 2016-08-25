<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*" %> 


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    <jsp:include page="head.jsp"/>
    <title>Register</title>

<!-- jQuery -->

<link rel="stylesheet" type="text/css" href="easycheck/css/easycheck.css"/>  
<script type="text/javascript" src="easycheck/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="easycheck/easy.easycheck-4.0.0.min.js"></script>
<script type="text/javascript" src="easycheck/lang/easy.easycheck-lang-en.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
<script>
  $(function() {
    $( "#datepicker" ).datepicker();
      $( "#datepicker" ).datepicker('option', {dateFormat:"yy-mm-dd"});
  });
  </script>
<script language="javascript">
var http_request = false;
function send_request(url) {
http_request = false;
		if(window.XMLHttpRequest) { 
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {
		http_request.overrideMimeType("text/xml");
		}
		}
		else if (window.ActiveXObject) {
		try {
		http_request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
		try {
		http_request = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {}
		}
		}
		if (!http_request) {
		window.alert("Can not create XMLHttpRequest.");
		return false;
		}
		http_request.onreadystatechange = processRequest;
		http_request.open("GET", url, true);
		http_request.send(null);
		}
		function processRequest() {
			
			if (http_request.readyState == 4) {
			//alert(http_request.status);
				if (http_request.status == 200) {
				    //alert("wocao");
					document.getElementById("ckuser").innerHTML=http_request.responseText;
					//document.getElementById("ckuser").style.display="none";
					//alert(http_request.responseText);
				} 
				else { 
			//return false
				//alert("wocao");
				
			}
		}
		}
</script>
<script type="text/javascript">
    EasyCheck.blurChk=true;    
    EasyCheck.keyupChk=true;  
    EasyCheck.loadChk=true; 
    EasyCheck.addChk(".isexist",
    function(o){
    	send_request('control?checkuser='+document.getElementById("username").value);
    	if (document.getElementById("ckuser").innerHTML != "") {
    		return false
    	}
    	else {return true}
    }
    ,
    "");
    EasyCheck.easyCheckKeyupIgnore[".isexist"]=true;
</script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
.ckuser {
	color:#FF2A2B;
	display: inline;
	font-size: 13px;
}
</style>
  </head>
  <body>
  <center>
  <form action="control" method="post" name="register" id="form1" easycheck="true">
  <input type="hidden" name="reg">
  <input type="hidden" name="nextpage" value="registerResult.jsp">
  <table border="0" cellpadding="0" cellspacing="0" bordercolor="#FF9966" frame="below" width="50%" align="center">
  <tr>
  <td width="20%" align="left"><label>User Name:</label></td><td width="40%"><input ecss="no" class="isexist required" type="text" name="username" id="username" border="0" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin">
  <span id="ckuser" class="ckuser"></span>
  <br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Password:</label></td><td><input ecss="no"  class="txt required" maxlength="12" minlength="6" type="password" name="passwd" id="passwd" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Confirm Password:</label></td><td><input ecss="no"  class="txt required" maxlength="12" minlength="6" type="password" name="confirmpassword" equalto="passwd" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Nick Name:</label></td><td><input type="text" name="nickname" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>First Name:</label></td><td><input type="text" name="firstname" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Last Name:</label></td><td><input type="text" name="lastname" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>E-mail:</label></td><td><input ecss="no" class="email required" type="text" name="mail" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Phone Number:</label></td><td><input type="text" name="phone" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Birthday:</label></td><td><input type="text" class="required" name="birthday" id="datepicker" readonly style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Address:</label></td><td><input type="text" name="address" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Credit Card:</label></td><td><input type="text" name="credit" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>User Type:</label></td><td>
  <select name="an" id="an">
  <OPTION selected>Buyer</OPTION>
  <OPTION>Seller</OPTION>
  </select>
  </td>
  </tr>
  </table>
<!--   <input type="hidden" name="mail" value='document.getElementById("email").value'> -->
  <input type="submit" value="Submit">
  </form>
  </center>
  </body>
 
<!-- <style type="text/css">
input {border:0px solid #000000;}
input {star : expression(
onmouseover=function(){this.style.borderColor="#060"},
onmouseout=function(){this.style.borderColor="#c00"}
)}
</style> -->
</html>


