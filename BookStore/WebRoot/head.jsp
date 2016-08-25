<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="
edu.comp9321.ass2.*,
java.util.*,
java.sql.ResultSet"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

<link rel="stylesheet" type="text/css" href="easycheck/css/easycheck.css"/>  
<script type="text/javascript" src="easycheck/jquery-1.9.0.min.js"></script>
<script type="text/javascript" src="easycheck/easy.easycheck-4.0.0.min.js"></script>
<script type="text/javascript" src="easycheck/lang/easy.easycheck-lang-en.js"></script>

<link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>
  <script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">
  <%
  String message = new String();
  String userid = new String();
  	JourneyBean jn = (JourneyBean) request.getSession().getAttribute("journey");
  	if (jn != null) {
  	message = jn.getUser_name();
  	userid = jn.getUser_id();
  	}
  	else {
  	%><jsp:useBean id="journey" class="edu.comp9321.ass2.JourneyBean" scope="session" /><%
  	}
   %>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>
<body>
<script type="text/javascript">
function error() {
	var uname = document.getElementById("iu").value;
	var pword = document.getElementById("ip").value;
	if (uname == "" || pword == "") {
		document.getElementById("es").style.display="";
		return false;
	}
	else {
		return true;
	}
}

</script>
<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#FF9966" style="background-color:#072A45;padding: 10px 0px 10px 0px;">
<tr>
<!-- <td width="50%">
<img height="100px" src="upload/19216800111020155614015645163937.png" alt=""/>
</td> -->
<td>
<div style="font-family:impact;font-size:50;color:white;"><a href='/BookStore/index.jsp' style="color:white;text-decoration:none;">OCEANBEACH</a></div>
</td>
<td align="right">
<%
	if (jn != null) {
	if (message != null) {
			out.println( "<font color='white'>Welcome "+message+"!</font><br><a href='UserServlet?action=mainpage&userid="+userid+"' style='color:white'>My Book Store</a><form id='o' action='control' method='post' name=out easycheck='false'><input type='hidden' name='logout' value='logout'><input align='right' type='submit' value='Logout'></form>");
			//out.println();
	}
	else {
		if (jn.getError() == null) {
			out.println("<div align='right'><form action='control' method='post' name=login id='logform'><label id='lu'><font color='white'>User name:</font></label><input id='iu' name='logname' type='text' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><label id='lp'><font color='white'>Password:</font></label><input id='ip' name='logpass' type='password' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><input type='submit' value='Login' id='submit'></form></div><div align='right'><a href='register.jsp' style='color:white'>Register</a></div>");		}
		else if (jn.getError().equals("wrongpass")) {
			out.println("<div align='right'><form action='control' method='post' name=login id='logform'><label id='lu' ><font color='white'>User name:</font></label><input id='iu' name='logname' type='text' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><label id='lp'><font color='white'>Password:</font></label><input id='ip' name='logpass' type='password' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><input type='submit' value='Login' id='submit'></div><div align='right'><span style='color:red;font-size:14px;'>User name or password is wrong!</span></form><a href='register.jsp' style='color:white'>Register</a></div>");
			//out.println("<tr><td align='right'><span style='color:red;font-size:14px;'>User name or password is wrong!</span></td></tr>");
		}
		else if (jn.getError().equals("notavailable")) {
			out.println("<div align='right'><form action='control' method='post' name=login id='logform'><label id='lu'><font color='white'>User name:</font></label><input id='iu' name='logname' type='text' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><label id='lp'><font color='white'>Password:</font></label><input id='ip' name='logpass' type='password' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><input type='submit' value='Login' id='submit'></div><div align='right'><span style='color:red;font-size:14px;'>Your account is not available now, please active it first!</span></form><a href='register.jsp' style='color:white'>Register</a></div>");
			//out.println("<span style='color:red;font-size:14px;'>Your account is not available now, please active it first!</span></td></tr>");
		}
		else if (jn.getError().equals("nouser")) {
			out.println("<div align='right'><form action='control' method='post' name=login id='logform'><label id='lu'><font color='white'>User name:</font></label><input id='iu' name='logname' type='text' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><label id='lp'><font color='white'>Password:</font></label><input id='ip' name='logpass' type='password' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><input type='submit' value='Login' id='submit'></div><div align='right'><span style='color:red;font-size:14px;'>No such an user!</span></form><a href='register.jsp' style='color:white'>Register</a></div>");
			//out.println("<tr><td align='right'><span style='color:red;font-size:14px;'>No such an user!</span></td></tr>");
		}
	}
	jn.setError(null);
	}
	else {
		out.println("<div align='right'><form action='control' method='post' name=login id='logform'><label id='lu'><font color='white'>User name:</font></label><input id='iu' name='logname' type='text' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><label id='lp'><font color='white'>Password:</font></label><input id='ip' name='logpass' type='password' border='0' style='width:100;border-bottom-width:thin;background-color:#072A45;color:white;' ecss='no'><input type='submit' value='Login' id='submit' onclick='return error()'></div><div align='right'></form><a href='register.jsp' style='color:white'>Register</a></div>");		
	}
 %>
</td>
</tr>
</table>
</body>
<style type="text/css">
input {border:0px solid #FFFFFF;}
input {star : expression(
onmouseover=function(){this.style.borderColor="#060"},
onmouseout=function(){this.style.borderColor="#c00"})}
</style>
</html>
