<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>

<%
	myUser userbeanjsp = new myUser();
	userDAO userdao = new userDAO();	
	String aparam = request.getParameter("aparam");
	String userid = request.getParameter("userid");
	userdao.findUserinfo(Integer.parseInt(userid), userbeanjsp);	
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>buyer information</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">
  </head>
  <body>
  <div style="width:100%;"><jsp:include page="head.jsp"/></div>
<div>	
  		<div style="width:20%; float:left;border:solid 0px #333333;vertical-align:middle;padding:50px 0px 50px 0px">
		<font size='4'><a style="color:#072A45;">User Operation:</a></font>
  		<ul>
  		<font size='4'><a href="UserServlet?action=buyerinfo&userid=<%=userid%>" style="color:#072A45;">User Information</a></font><br>
		<font size='4'><a href="UserServlet?action=mycart&userid=<%=userid%>" style="color:#072A45;">My Cart</a></font><br>
		<font size='4'><a href="UserServlet?action=allborder&userid=<%=userid%>" style="color:#072A45;">All Order</a></font><br>
		<font size='4'><a href="UserServlet?action=unconborder&userid=<%=userid%>" style="color:#072A45;">Unconfirm Order</a></font><br>
		<font size='4'><a href="UserServlet?action=conborder&userid=<%=userid%>" style="color:#072A45;">Confirmed Order</a></font><br>
		</ul>		
 	 	</div>
 </div>
  	<%
  		if(aparam != null){  			
  			if(aparam.equals("modify")){  				
				userbeanjsp.setUsername(request.getParameter("username"));
				userbeanjsp.setNickname(request.getParameter("nickname"));
				userbeanjsp.setFirstname(request.getParameter("firstname"));
				userbeanjsp.setLastname(request.getParameter("lasttname"));
				userbeanjsp.setEmail(request.getParameter("email"));
				userbeanjsp.setPhone(request.getParameter("phone"));				
				userbeanjsp.setAddress(request.getParameter("address"));
				userbeanjsp.setCreditnum(request.getParameter("creditnum"));				
				userdao.modifyUser(userbeanjsp);
				out.println("<script>alert('Modify Succeed!');</script>");
				response.setHeader("refresh","0;URL=buyerinfo.jsp?userid="+userid);
  			}
  		}
  		else{
  	 %>
  
  	<form name="modify" method="post" action="UserServlet?action=buyerinfo&aparam=modify"> 
	<table border="1" width="80%">
	<tr><td>User name</td><td><input type="text" name="username" value="<%=userbeanjsp.getUsername()%>" readonly="true"><br></td></tr>
	<tr><td>Nick name</td><td><input type="text" name="nickname" value="<%=userbeanjsp.getNickname()%>" ><br></td></tr>
	<tr><td>First name</td><td><input type="text" name="firstname" value="<%=userbeanjsp.getFirstname()%>" readonly="true"><br></td></tr>
	<tr><td>Last name</td><td><input type="text" name="lasttname" value="<%=userbeanjsp.getLastname()%>" readonly="true"><br></td></tr>
	<tr><td>Email</td><td><input type="text" name="email" value="<%=userbeanjsp.getEmail()%>" readonly="true"><br></td></tr>
	<tr><td>phone</td><td><input type="text" name="phone" value="<%=userbeanjsp.getPhone()%>" ><br></td></tr>
	<tr><td>birthday</td><td><input type="text" name="birthday" value="<%=userbeanjsp.getBirthdate()%>" readonly="true"><br></td></tr>
	<tr><td>address</td><td><input type="text" name="address" value="<%=userbeanjsp.getAddress()%>"><br></td></tr>
	<tr><td>credit card</td><td><input type="text" name="creditnum" value="<%=userbeanjsp.getCreditnum()%>"><br></td></tr>
	<tr><td><input type="hidden" name="userid" value=<%=userid%>></td></tr>
	<tr><td align="center"><input type="submit" value="Modify" ></td></tr>
	
	</table>
	</form>
	<%
		}
	 %>
  </body>
</html>
