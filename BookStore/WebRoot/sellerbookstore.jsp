<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'sellerbookstore.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <script type="text/javascript"> 
var xmlhttp; 
function startrefresh(posttype){
var val = posttype;
		if(window.XMLHttpRequest) { 
		xmlhttp = new XMLHttpRequest();
		xmlhttp=new XMLHttpRequest(); 
		if (xmlhttp.overrideMimeType) {
		xmlhttp.overrideMimeType("text/xml");
		}
		}
		else if (window.ActiveXObject) { 
		try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
		try {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {}
		}
		}
		if (!xmlhttp) { 
		window.alert("Can not create XMLHttpRequest.");
		return false;
		}		
		xmlhttp.open("POST",val,true); 
		/*xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");*/
		//alert("jin lai le"); 
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = process1;
		function process1(){ 
	   	if(xmlhttp.readyState == 4) {
	   		//alert(xmlhttp.readyState);
	       if(xmlhttp.status == 200) {
	       	//alert(xmlhttp.responseText);
	       	document.getElementById("products").innerHTML=xmlhttp.responseText;
	       	re();
	    }
		}
		}
}
</script>
<script type="text/javascript">
  		function re() {
  		if(window.XMLHttpRequest) { 
  		//alert("jin lai le");
		xmlhttp = new XMLHttpRequest();
		xmlhttp=new XMLHttpRequest(); 
		if (xmlhttp.overrideMimeType) {
		xmlhttp.overrideMimeType("text/xml");
		}
		}
		else if (window.ActiveXObject) { 
		try {
		xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
		try {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} catch (e) {}
		}
		}
		if (!xmlhttp) { 
		window.alert("Can not create XMLHttpRequest.");
		return false;
		}
		xmlhttp.open("POST","buyerbookstore.jsp",true); 
		/*xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");*/
		//alert("jin lai le"); 
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = process1;
		function process1(){ 
	   	if(xmlhttp.readyState == 4) {
	   		//alert(xmlhttp.readyState);
	       if(xmlhttp.status == 200) {
	       	//alert(xmlhttp.responseText);
	    }
		}
		}	
		}
  		</script>
    <div style="width:100%;"><jsp:include page="head.jsp"/></div>
 <div>	
  		<div style="width:20%; float:left;border:solid 0px #333333;vertical-align:middle;padding:50px 0px 50px 0px">
		<font size='4'><a style="color:#072A45;">User Operation:</a></font>
  		<ul>
  		<font size='4'><a href="javascript:;" onClick="startrefresh('userstaticinfo.jsp')" style="color:#072A45;">User Information</a></font><br>
		<font size='4'><a href="javascript:;" onClick="startrefresh('mycart.jsp')" style="color:#072A45;">My Cart</a></font><br>
		<font size='4'><a href="javascript:;" onClick="startrefresh('allorder.jsp')" style="color:#072A45;">All Order</a></font><br>
		<font size='4'><a href="javascript:;" onClick="startrefresh('unconorder.jsp')" style="color:#072A45;">Unconform Order</a></font><br>
		<font size='4'><a href="javascript:;" onClick="startrefresh('conorder.jsp')" style="color:#072A45;">Conformed Order</a></font><br>
		</ul>		
  		</div>
  		<div style="width:80%; float:left; border:solid 0px #333333;resize:none;" id="products">

  		</div>
  		</div>
  </body>
</html>
