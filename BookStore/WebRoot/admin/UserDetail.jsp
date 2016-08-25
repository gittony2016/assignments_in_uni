<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="bookstoreBean.*,bookstoreServletControl.*" %>
<%@ page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import ="java.util.ArrayList" %>
<%@page import ="java.util.List" %>
<%@page import ="java.text.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String logout=request.getParameter("flag");
String ban=request.getParameter("ban");	//get the ban value
String mod=request.getParameter("mod");	//get the modify value
if(logout !=null && logout.equals("logout")){
	session.setAttribute("flag",false);		//Set the logout flag to "false"
	response.setHeader("refresh","URL=login.jsp");		
}
Boolean flag=(Boolean) session.getAttribute("flag");	//Check whether login or not.
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>User Detail</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script>
	//<!-- Use the onclick function to separate different methods and do different actions. -->
		function action_confirm(f){
			//document.writeln(f);
			if(window.confirm("Do you confirm to " + f + " this user?")){
				if(f=="ban"){
					this.document.modify.action="./admin/UserDetail.jsp?kw=" + this.document.modify.userid.value + "&ban=true";
				}else if(f=="mod"){
					this.document.modify.action="./admin/UserDetail.jsp?kw=" + this.document.modify.userid.value + "&mod=true";
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
  </head>
  
  <body>
<%
if(flag !=null && flag.equals(true)){

	if(ban !=null && ban.equals("true")){		//Do the ban user
		//out.println("<script>alert('Now try to ban the user');</script>");
		//out.println("Now try to ban the user");
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
			String banResult=DAOFactory.getIUserDAOInstance().banUser(keyWord);
			if(banResult.equals("Ban Succeed")){
				out.println("<script>alert('Ban Succeed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=UserList.jsp");
			}else{
				out.println("<script>alert('Ban Failed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=UserList.jsp");		
			}
			//Iterator<User> iter=all.iterator();
		}catch(Exception e){
			e.printStackTrace();
		}
	}else if(mod !=null && mod.equals("true")){		//Do the modify user
		//out.println("Now try to modify the user");
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
			//out.println(request.getParameter("nickname"));
			User user=new User();
			user.setUserid(Integer.parseInt(request.getParameter("userid")));
			user.setUserName(request.getParameter("username"));
			user.setPassword(request.getParameter("password"));
			user.setNickName(request.getParameter("nickname"));
			user.setFirstName(request.getParameter("firstname"));
			user.setLastName(request.getParameter("lastname"));
			user.setEmail(request.getParameter("email"));
			user.setPhone(request.getParameter("phone"));
			user.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("birthdate")));
			user.setAddress(request.getParameter("address"));
			user.setCreditnum(request.getParameter("creditcardnum"));
			user.setTypeid(request.getParameter("typeid"));
			
			String modResult=DAOFactory.getIUserDAOInstance().modUser(user);
			if(modResult.equals("Mod Succeed")){
				out.println("<script>alert('Mod Succeed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=UserList.jsp");
			}else{
				out.println("<script>alert('Mod Failed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=UserList.jsp");		
			}
			//Iterator<User> iter=all.iterator();
		}catch(Exception e){
			e.printStackTrace();
		}
	}else{	//Do the show detail
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
			List<User> all=DAOFactory.getIUserDAOInstance().findSingle(keyWord);
			Iterator<User> iter=all.iterator();
%>

	<div id="menu">
	<ul>
	<li><a href="./admin/login.jsp">Login</a></li>
	<li><a href="./admin/All_Items.jsp">List all items</a></li>
	<li><a href="./admin/UserList.jsp">View all users</a></li>
	<li><a href="./admin/ItemTypeList.jsp">View all itemtypes</a></li>
	<li><a href="javascript:window.history.back();">Return Back</a></li>
	<li><a href="./admin/login.jsp?flag=logout">Logout</a></li>
	</ul>
	</div>	
	
<center>
<form action="./admin/UserList.jsp" method="post">
	Please enter KeyWord&nbsp;: &nbsp;(Support:username,email and address)<p>
	<input type="text" name="kw" size="50">
	<input type="submit" value="Search">
	
</form>

    User Detail <br>
<form name="modify" method="post">    
	<table border="1" width="80%">

<%
			while(iter.hasNext()){
				User user=iter.next();
				
				/*
				int userID=rs.getInt("userid");
				String userName=rs.getString("username");
				String userpassword=rs.getString("password");	
				String userNickname=rs.getString("nickame");
				String userfirstname=rs.getString("firstname");
				String userlastname=rs.getString("lastname");
				String userEmail=rs.getString("email");
				String userEmail=rs.getString("phone");
				String userbirthdate=rs.getString("birthdate");	
				String useraddress=rs.getString("address");	
				String usercreditnum=rs.getString("creditnum");	
				String usertypeid=rs.getString("typeid");	
				*/
				
 %>	
 

			<tr><td>UserID</td><td><input type="text" name="userid" value="<%=user.getUserid()%>" readonly="true"><br></td></tr>
			<tr><td>User Name</td><td><input type="text" name="username" value="<%=user.getUserName()%>"><br></td></tr>
			<tr><td>Password</td><td><input type="password" name="password" value="<%=user.getPassword()%>"><br></td></tr>	
			<tr><td>Nick Name</td><td><input type="text" name="nickname" value="<%=user.getNickName() %>"><br></td></tr>
			<tr><td>First Name</td>	<td><input type="text"name="firstname"  value="<%=user.getFirstName() %>"><br></td></tr>		
			<tr><td>Last Name</td><td><input type="text" name="lastname" value="<%=user.getLastName()%>"><br></td></tr>
			<tr><td>Email</td><td><input type="text" name="email"  value="<%=user.getEmail()%>"><br></td></tr>
			<tr><td>Phone</td><td><input type="text" name="phone" value="<%=user.getPhone()%>"><br></td></tr>	
			<tr><td>Birthdate</td><td><input type="text" name="birthdate" value="<%=user.getBirthdate()%>"><br></td>	</tr>
			<tr><td>Address</td><td><input type="text" name="address" value="<%=user.getAddress()%>"><br></td></tr>
			<tr><td>Credit Card Num</td><td><input type="text" name="creditcardnum" value="<%=user.getCreditnum()%>"><br></td></tr>	
			<tr><td>TypeID<br/>(0:Ban;1:buyer;2:seller;3:administrator)</td><td><input type="text" name="typeid" value="<%=user.getTypeid()%>"><br></td></tr>
			<tr>
			<!-- Use the onclick function to separate different methods and do different actions. -->

					<%
	 					//Separate the User types
	 					
	 					String usertypeid=user.getTypeid();
	 					if(usertypeid.equals("1")){
	 				%>
						<td align="center"><input type="submit" name="ban" value="Ban this User" onclick="return action_confirm('ban')">
							<input type="submit" name="mod" value="Modify this User"  onclick="return action_confirm('mod')">
						</td>
						<td align="center">						
							<a href="./admin/OrderList.jsp?kw=<%=user.getUserid()%>">View User Orders</a> &nbsp;&nbsp;||&nbsp;&nbsp;
							<a href="./admin/CartList.jsp?kw=<%=user.getUserid()%>">View User ShoppingCart</a>
	 						<!-- <input type="button" name="viewCart" value="User ShoppingCart"  onclick="window.location.href='./CartList.jsp?kw=<%=user.getUserid()%>'"> -->
						</td>
					<%
	 					}else{
	 				%>
						<td align="center"><input type="submit" name="ban" value="Ban this User" onclick="return action_confirm('ban')"></td>
						<td align="center"><input type="submit" name="mod" value="Modify this User"  onclick="return action_confirm('mod')"></td>
					<% 					
	 					}
					 %>
				<input type="hidden" name="flag" value="false">
				<input type="hidden" name="kw" value="<%=user.getUserid()%>">
			</tr>

 <%
		 	}
 %>
	</table>
</form>
</center>
<%
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
else{
	out.println("<center>Please login first!</center>");
	session.setAttribute("flag", false);
	//out.println(flag);
	response.setHeader("refresh","3;URL=login.jsp");
}	
%>
  </body>
</html>
