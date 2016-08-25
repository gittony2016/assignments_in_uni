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
    
    <title>User Cart List</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		function validate() 
	    {
	    	var kw=document.form_search.kw.value;
	    	var check_kw=/^[0-9]+$/;
	    	var result=check_kw.test(kw);
	    	if (!result)
	    	{
	    		alert("Please enter Userid for search(number only).");
	    		document.form_search.kw.focus();
	    	return false ;
	    	}
	    	return true;
	    }	
	</script>	
  </head>
  
  <body>
<%
if(flag !=null && flag.equals(true)){
	try{
		String keyWord=request.getParameter("kw");
		if(keyWord==null){
			keyWord="";
		}
		List<Cart> all=DAOFactory.getICartDAOInstance().findAll(keyWord);
		Iterator<Cart> iter=all.iterator();
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
<form action="./admin/CartList.jsp" method="post"  name="form_search"  onsubmit="return validate()">
	Please enter KeyWord&nbsp;: &nbsp;(Support:Userid)<p>
	<input type="text" name="kw" size="50">
	<input type="submit" value="Search">
	
</form>

    User Cart List for BookStore <br>
	<table border="1" width="80%">
		<tr>
			<td>CartID</td>
			<td>ItemListid</td>
			<td>Buyerid</td>
			<td>BuyerName</td>
			<td>Status</td>
		</tr>
<%
		while(iter.hasNext()){
			Cart cart=iter.next();
					
 %>	

 		<tr>
 			<td><a href="./admin/CartDetail.jsp?kw=<%=cart.getCartid()%>"><%=cart.getCartid()%></a></td>
 			<td><a href="./admin/CartDetail.jsp?kw=<%=cart.getCartid()%>"><%=cart.getItemlistid()%></a></td>
 			<td><a href="./admin/UserDetail.jsp?kw=<%=cart.getBuyerid()%>"><%=cart.getBuyerid()%></a></td>
 			<td><a href="./admin/UserDetail.jsp?kw=<%=cart.getBuyerid()%>"><%=cart.getUsername()%></a></td>
 			

 			  	<%
 					//Separate the User types
 					
 					String cartstatus=cart.getStatus();
 					if(cartstatus.equals("1")){
 					//out.println(cartstatus + "&nbsp;&nbsp;=>Normal Cart");
 				%>
 				 	<td><a href="./admin/CartDetail.jsp?kw=<%=cart.getCartid()%>"><%=cart.getStatus()%>&nbsp;&nbsp;=>Normal Cart</a></td>
 						
 				<%
 					}else if(cartstatus.equals("2")){
 						//out.println(cartstatus + "&nbsp;&nbsp;=>Already Bought");
 				%>
 					<td><a href="./admin/CartDetail.jsp?kw=<%=cart.getCartid()%>"><%=cart.getStatus()%>&nbsp;&nbsp;=>Already Bought</a></td>
 				<%
 					}else if(cartstatus.equals("3")){
 						//out.println(cartstatus + "&nbsp;&nbsp;=>Deleted Cart");
 				%>
 					<td><a href="./admin/CartDetail.jsp?kw=<%=cart.getCartid()%>"><%=cart.getStatus()%>&nbsp;&nbsp;=>Deleted Cart</a></td>
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
