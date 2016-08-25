<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>
<jsp:useBean id="user" class="bookstoreBean.User" scope="session" />
<%
myUser userbeanjsp = (myUser)session.getAttribute("myuser");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>allorder</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">

  </head>
  <body>
 		<table border="1" width="80%">
		<tr>			
			<td>Order id</td>	
			<td>Deal date</td>
			<td>Status</td>			
		</tr>
	
	<%
			List<Order> orderlist = userbeanjsp.getOrderlist();
			String status ="";
			if(orderlist != null){
				for(int i =0;i<orderlist.size();i++){
					if(orderlist.get(i).getStatus().equals("1"))
					{
						status ="confirmed";
					}
					else if(orderlist.get(i).getStatus().equals("2"))
					{
						status ="waiting for confirm";
					}
 	%>
 				<tr>
 					<td><a href='UserServlet?action=orderdetail&orderid=<%=orderlist.get(i).getOrderid()%>'><%=orderlist.get(i).getOrderid() %></a>
 					<td><a href='UserServlet?action=orderdetail&orderid=<%=orderlist.get(i).getOrderid()%>'><%=orderlist.get(i).getDealdate() %></a>
 					<td><a href='UserServlet?action=orderdetail&orderid=<%=orderlist.get(i).getOrderid()%>'><%=status %></a> 				
 				</tr>

	<%
				}
			}						
	 %>
		</table>
  </body>
</html>
