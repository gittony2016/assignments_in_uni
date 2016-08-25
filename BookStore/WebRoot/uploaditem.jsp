<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="com.mysql.jdbc.Driver"%>
<%@ page import="java.sql.*" %>
<%@ page import="
edu.comp9321.ass2.*,
bookstoreBean.*,
java.util.*,
java.sql.*"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    <title>upload</title>

<!-- jQuery -->

<link rel="stylesheet" type="text/css" href="easycheck/css/easycheck.css"/>  
<script type="text/javascript" src="easycheck/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="easycheck/easy.easycheck-4.0.0.min.js"></script>
<script type="text/javascript" src="easycheck/lang/easy.easycheck-lang-en.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
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
   <script type="text/javascript">
 function che() {
 //alert("cao ni ma");
    var form1 = document.getElementById("form1"); 
     var pl = document.getElementById("pl"); 
     //alert(document.getElementById("uid").value);
 	if (document.getElementById("uid").value != "") {
 	//alert("!!!!!!!!!!!!");
 		return true;
 	}
 	else {
 		//alert("cao ni ma");
 		pl.style.display="";
 		return false;
 	}
 }
 
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
  <div style="width:100%;"><jsp:include page="head.jsp"/></div>
  <center>
  <form action="control" method="post" name="up" id="form1" easycheck="true" enctype='multipart/form-data'>
  <input type="hidden" name="uid" id="uid" value="<%	
  JourneyBean jn = (JourneyBean) request.getSession().getAttribute("journey");
  if (jn.getUser_id() != null) {
  	out.print(jn.getUser_id());
  }
  %>">
  <table border="0" cellpadding="0" cellspacing="0" bordercolor="#FF9966" frame="below" width="50%" align="center">
  <tr>
  <td width="20%" align="left"><label>Item Name:</label></td><td width="40%"><input ecss="no" class="txt required" type="text" name="itemname" id="itemname" border="0" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin">
  <span id="ckuser" class="ckuser"></span>
  <br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Author:</label></td><td><input ecss="no"  class="txt required" name="author" id="Author" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Editor:</label></td><td><input ecss="no"  class="txt required" name="editor"  style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Title:</label></td><td><input type="text" name="title" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Volume:</label></td><td><input type="text" name="volume" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Pub Type:</label></td><td><input type="text" name="pubtype" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Item Type:</label></td><td>
<!--   <input ecss="no" type="text" name="itemtype" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"> -->
<select name="itemtype" id="itemtype">
<%
	for (int k=0;k<jn.getList_name().size();k++) {
		out.print("<OPTION selected>"+jn.getList_name().elementAt(k)+"</OPTION>");
	}

 %>

</select>
  <br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Venue:</label></td><td><input type="text" name="venue" style="border:0px solid #000000;background-color:#FFFFFF;border-bottom-width: thin"><br></td>
  </tr>
  <tr>
  <td width="20%" align="left"><label>Picture:</label></td>
  <td>
    <input type="file" name="picture" style="border-bottom-width: thin" class="required">
  </td>
  </tr>
  </table>

  <input type="submit" value="Submit" onclick="return che()">
  </form>
  <span id="pl" style='color:red;font-size:14px;display:none;'>Please log in first!</span>
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


