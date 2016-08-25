<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>
<jsp:useBean id="myuser" class="bookstoreBean.myUser" scope="session" />

<%
	myUser userbeanjsp = (myUser)session.getAttribute("myuser");
	String para = request.getParameter("para");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Left-User information</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  
  <body>	
	<table border="1" width="80%">
	<tr><td>User name</td><td><input type="text" name="username" value="<%=userbeanjsp.getUsername()%>" readonly="true"><br></td></tr>
	<tr><td>Nick name</td><td><input type="text" name="nickname" value="<%=userbeanjsp.getNickname()%>" readonly="true"><br></td></tr>
	<tr><td>First name</td><td><input type="text" name="firstname" value="<%=userbeanjsp.getFirstname()%>" readonly="true"><br></td></tr>
	<tr><td>Last name</td><td><input type="text" name="lasttname" value="<%=userbeanjsp.getLastname()%>" readonly="true"><br></td></tr>
	<tr><td>Email</td><td><input type="text" name="email" value="<%=userbeanjsp.getEmail()%>" readonly="true"><br></td></tr>
	<tr><td>phone</td><td><input type="text" name="phone" value="<%=userbeanjsp.getPhone()%>" readonly="true"><br></td></tr>
	<tr><td>birthday</td><td><input type="text" name="birthday" value="<%=userbeanjsp.getBirthdate()%>" readonly="true"><br></td></tr>
	<tr><td>address</td><td><input type="text" name="address" value="<%=userbeanjsp.getAddress()%>" readonly="true"><br></td></tr>
	<tr><td>credit card</td><td><input type="text" name="creditnum" value="<%=userbeanjsp.getCreditnum()%>" readonly="true"><br></td></tr>	
	<tr><td><INPUT name="mod" type="button" value="Modify" onClick="location.href='userinfo.jsp'"><br></td></tr>
	</table>
	
  </body>
  
</html>
