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
  	<script>
	//<!-- Use the onclick function to separate different methods and do different actions. -->
		function action_confirm(f){
			//document.writeln(f);
			if(window.confirm("Do you confirm to " + f + " this item?")){
				if(f=="modify"){
					this.document.modify.action="userinfo.jsp?&para=modify";
				}
				this.document.modify.submit;
				//alert("you select yes");
				return true;
			
			}else{
				//alert("you select no");
				return false;
			}
		}
	</script>
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
  	<%
  		if(para != null){
  			if(para.equals("modify")){
  				out.println("<script>alert("+request.getParameter("phone")+");</script>");
				userbeanjsp.setUsername(request.getParameter("username"));
				userbeanjsp.setNickname(request.getParameter("nickname"));
				userbeanjsp.setFirstname(request.getParameter("firstname"));
				userbeanjsp.setLastname(request.getParameter("lasttname"));
				userbeanjsp.setEmail(request.getParameter("email"));
				userbeanjsp.setPhone(request.getParameter("phone"));				
				userbeanjsp.setAddress(request.getParameter("address"));
				userbeanjsp.setCreditnum(request.getParameter("creditnum"));
				userDAO userdao = new userDAO();
				userdao.modifyUser(userbeanjsp);
				out.println("<script>alert('Modify Succeed!');</script>");
				response.setHeader("refresh","0;URL=buyerbookstore.jsp");
  			}
  		}
  		else{
  	 %>
  	<form name="modify" method="post"> 
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
	<td align="center"><input type="submit" name="mod" value="Modify"  onclick="return action_confirm('modify')"></td>
	</table>
	</form>
	<%
		}
	 %>
  </body>
</html>
