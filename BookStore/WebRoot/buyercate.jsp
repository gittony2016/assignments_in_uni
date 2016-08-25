<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>buyercate</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">

  </head>  
  <body>
	<div>
	<a href="#" target="itemdetail.jsp"> itemdetail.jsp </a><br>
	<a href="#" target="userinfo.jsp"> userinfo.jsp </a><br>
	</div>
  </body>
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
		xmlhttp.open("POST","control?item_list=1",true); 
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
  <script type="text/javascript"> 
var xmlhttp; 
function startrefresh(posttype){ 
var val = posttype.innerHTML;
val=val.replace(/\&amp;/g,"%26");
//alert(val);
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
		xmlhttp.open("POST","control?item_list=2&posttype="+val,true); 
		/*xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");*/
		//alert("jin lai le"); 
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = process1;
		function process1(){ 
	   	if(xmlhttp.readyState == 4) {
	   		//alert(xmlhttp.readyState);
	       if(xmlhttp.status == 200) {
	       	//alert(xmlhttp.responseText);
	       	xmlhttp.open("POST","products.jsp?posttype=encodeURLComponet("+val+")",true); 
			/*xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");*/
			//alert("jin lai le"); 
			xmlhttp.send(null);
			xmlhttp.onreadystatechange = process;
			function process(){ 
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
		}
		}
}
</script>
</html>
