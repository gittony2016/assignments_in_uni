<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="bookstoreBean.*,bookstoreServletControl.*" %>
<%@ page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import ="java.util.ArrayList" %>
<%@page import ="java.util.List" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String logout=request.getParameter("flag");
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
    
    <title>All Items List</title>
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
<%
if(flag !=null && flag.equals(true)){
	try{
		String keyWord=request.getParameter("kw");
		if(keyWord==null){
			keyWord="";
		}
		List<Item> all=DAOFactory.getIItemDAOInstance().findAll(keyWord);
		Iterator<Item> iter=all.iterator();
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
<form action="./admin/All_Items.jsp" method="post">
	Please enter KeyWord&nbsp;: &nbsp;(Support:ItemName,Author,Title)<p>
	<input type="text" name="kw" size="50">
	<input type="submit" value="Search">
	
</form>

    Book List for BookStore <br>
	<table border="1" width="80%">
		<tr>
			<td>Item Name</td>
			<td>Title</td>
			<td>Author</td>	
			<td>Onlinedate</td>
			<td>Status(0=>Pause;1=>Normal)</td>
				
		</tr>
<%
		while(iter.hasNext()){
			Item item=iter.next();
			
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
 
 		<tr>
 			<td><a href="./admin/ItemDetail.jsp?kw=<%=item.getItemid() %>"><%=item.getItemName() %></a></td>
 			<td><a href="./admin/ItemDetail.jsp?kw=<%=item.getItemid()%>"><%=item.getTitle()%></a></td>
 			<td><a href="./admin/ItemDetail.jsp?kw=<%=item.getItemid()%>"><%=item.getAuthor()%></a></td>
 			<td><%=item.getOnlinedate()%></td>
 			<%
 				String status=item.getStatus();
 				if(status.equals("0")){
 			%>
 					<td><%=item.getStatus() %>&nbsp;&nbsp;=>Pause</td>
 			<%
 				}else if(status.equals("1")){
 			%>
 					<td><%=item.getStatus() %>&nbsp;&nbsp;=>Normal</td>
 			<%
 				}
 			
 			 %>
 			
 		</tr>
 <%
	 	}
 %>
	</table>

</center>
<%
	}catch(Exception e){
		e.printStackTrace();
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
