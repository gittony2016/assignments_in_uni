<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>

<%
String orderid = request.getParameter("orderid");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>orderdetail</title>
    
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
  <div style="width:100%;"><jsp:include page="head.jsp"/></div>
  	<div id="menu">
	<ul>
	<li><a href="javascript:window.history.back();">Return Back</a></li>	
	</ul>
	</div>
	
  	<center>	
	<form name="modify" method="post">
	<table border="1" width="80%">
		<tr>			
			<td>Item Name</td>	
			<td>Title</td>
			<td>Author</td>
			<td>Online Date</td>		
		</tr>
    <%  
    		List<Item> itemlist = new  ArrayList<Item>();
    		orderDAO orderdao = new orderDAO();
    		orderdao.findOitemist(Integer.parseInt(orderid), itemlist);
    		if(itemlist != null){
    			for(int i =0;i<itemlist.size();i++){
    			if(itemlist.get(i).getItemName()!=null){    			
    %>
	    		<tr>
				<td><a href='UserServlet?action=orditemdetail&itemid=<%=itemlist.get(i).getItemid()%>'><%=itemlist.get(i).getItemName() %></a>
				<td><a href='UserServlet?action=orditemdetail&itemid=<%=itemlist.get(i).getItemid()%>'><%=itemlist.get(i).getTitle() %></a>
				<td><a href='UserServlet?action=orditemdetail&itemid=<%=itemlist.get(i).getItemid()%>'><%=itemlist.get(i).getAuthor() %></a>
				<td><a href='UserServlet?action=orditemdetail&itemid=<%=itemlist.get(i).getItemid()%>'><%=itemlist.get(i).getOnlinedate() %></a>			
				</tr>
    <%
    				}
    			}
    		}    		
     %>
     <tr>			
	</tr>
	</table>
	</form>		
	</center>
  </body>
</html>
