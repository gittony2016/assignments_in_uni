<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="
edu.comp9321.ass2.*,
bookstoreBean.*,
java.util.*,
java.sql.*"%>
<jsp:useBean id="journey" class="edu.comp9321.ass2.JourneyBean" scope="session" />
<jsp:useBean id="myuser" class="bookstoreBean.myUser" scope="session" />
<jsp:useBean id="cart" class="bookstoreBean.Cart" scope="session" />
<%
	myUser userbean = new myUser();
	session.setAttribute("myuser", userbean);
	Cart cartbean = new Cart();	
	session.setAttribute("cart", cartbean);
		
	JourneyBean jn = (JourneyBean) request.getSession().getAttribute("journey");
	if (jn.getItem_name().size() == 0) {
		String site = new String("control?item_list=1");
   		response.setStatus(response.SC_MOVED_TEMPORARILY);
    	response.setHeader("Location", site); 
	}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
  		function re() {
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <!-- <form action='control' method="post">
  	<input type="hidden" name="action" value="index.jsp">
    <input type='submit' value='SQL'></form> -->
    <!-- <form action='control' enctype='multipart/form-data' method="post">
    <input type="file" name="picture">
    <input type="submit" value="Upload">
    </form> -->
  <body bgcolor="" onload=re()>
<%--   	<%
  	//JourneyBean jn = (JourneyBean) request.getSession().getAttribute("journey");
  	//out.print(jn.getRS().size());
  	/*if (jn.getPlaces().size() != 0) {
  		out.print("<p>"+jn.getPlaces().firstElement() + "     </p>");
  	}*/
  	if (jn.getUser_name() != null) {
	out.print("<p>"+jn.getUser_id() + "     </p>");
	out.print("<p>"+jn.getUser_name() + "     </p>");
	out.println("<p>"+ jn.getUser_email() + "     </p>"); 
	}
	 %> --%>

  <div style="width:100%;"><jsp:include page="head.jsp"/></div>
  <div>	
  		<div style="width:20%; float:left;border:solid 0px #333333;vertical-align:middle;padding:50px 0px 50px 0px">
  		<p style="color:#333333;">Type List:</p>
  			<center>
  			<% 
  			for (int k=0;k<jn.getList_name().size();k++) {
  				out.println("<font size='4'><a href='javascript:;' onClick='startrefresh(this.innerHTML)' style='color:#072A45;'>"+jn.getList_name().elementAt(k)+"</a></font></br>");
  			}
  			//jn.setList_name(null);
  			//String site = new String("control?item_list=1");
   			//response.setStatus(response.SC_MOVED_TEMPORARILY);
    		//response.setHeader("Location", site);
  			%>
  			</center>
  		</div>
<!--   		"+jn.getList_name().elementAt(k)+" -->
<script type="text/javascript"> 
var xmlhttp; 
function startrefresh(posttype){ 
//alert("123");
var val = posttype;
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
		xmlhttp.open("POST","control?item_list=3&posttype="+val,true); 
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
<script type="text/javascript"> 
var xmlhttp; 
function searchrefresh(search){ 
//alert("123");
var val = search;
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
		xmlhttp.open("POST","control?item_list=2&search="+val,true); 
		/*xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");*/
		//alert("jin lai le"); 
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = process1;
		function process1(){ 
	   	if(xmlhttp.readyState == 4) {
	   		//alert(xmlhttp.readyState);
	       if(xmlhttp.status == 200) {
	       	//alert(xmlhttp.responseText);
	       	xmlhttp.open("POST","products.jsp?search=encodeURLComponet("+val+")",true); 
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
<script type="text/javascript"> 
var xmlhttp; 
function Asearchrefresh(search,searchtype){ 
//alert("123");
var val = search;
var val2 = searchtype;
if (val2 == "Item Name") {
	val2="itemname";
}
else if (val2 == "Author") {
	val2="author";
}
else if (val2 == "Editor") {
	val2="editor";
}
else if (val2 == "Title") {
	val2="title";
}
else if (val2 == "Volume") {
	val2="volume";
}
else if (val2 == "Pub Type") {
	val2="pubtype";
}
else if (val2 == "Venue") {
	val2="venue";
}
else if (val2 == "Online Date") {
	val2="onlinedate";
}
val=val.replace(/\&amp;/g,"%26");
		//alert(val2);
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
		xmlhttp.open("POST","control?item_list=2&asearch="+val+"&searchtype="+val2,true); 
		/*xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");*/
		//alert("jin lai le"); 
		xmlhttp.send(null);
		xmlhttp.onreadystatechange = process1;
		function process1(){ 
	   	if(xmlhttp.readyState == 4) {
	   		//alert(xmlhttp.readyState);
	       if(xmlhttp.status == 200) {
	       	//alert(xmlhttp.responseText);
	       	xmlhttp.open("POST","products.jsp?search=encodeURLComponet("+val+")",true); 
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
		<div><input type="text" border='0' style='width:30%; float:left;border:0px solid #000000;border-bottom-width:thin;' name="search" id="search" ecss='no'><input type="button" onClick="searchrefresh(document.getElementById('search').value)" value='Search'></div>
  		<br><div><input type="text" border='0' style='width:20%; float:left;border:0px solid #000000;border-bottom-width:thin;' name="adsearch" id="adsearch" ecss='no'>
  		<select name="searchtype" id="searchtype">
  		<OPTION selected>Item Name</OPTION>
  		<OPTION>Author</OPTION>
  		<OPTION>Editor</OPTION>
  		<OPTION>Title</OPTION>
  		<OPTION>Volume</OPTION>
  		<OPTION>Pub Type</OPTION>
  		<OPTION>Venue</OPTION>
  		<OPTION>Online Date</OPTION>
  		</select>
  		<input type="button" onClick="Asearchrefresh(document.getElementById('adsearch').value,document.getElementById('searchtype').value)" value='Advanced Search'>
  		</div>
  		<div style="width:80%; float:left; border:solid 0px #333333;resize:none;" id="products">
  			<jsp:include page="products.jsp"/>
  		</div>
  </div>
  </body>
</html>



