<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>


<%
	String userid = request.getParameter("userid");
	String para = request.getParameter("para");
	String cartid = request.getParameter("cartid");
	String itemid = request.getParameter("itemid");			
	Item itembeanjsp = new Item();
	cartDAO cartdao = new cartDAO();
	cartdao.findIteminfo(Integer.parseInt(itemid), itembeanjsp);
					
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

  <head>
    <base href="<%=basePath%>">
    
    <title>Order Item Detail</title>
    
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
	<%
		
		String itemname =  null;
		String author=  null;
		String editor=  null;
		String title=  null;
		String volume=  null;
		String pubtype=  null;
		String itemtype=  null;
		String venue=  null;
		String url=  null;
		Date onlinedate=  null;
		String address=  null;
		String creditnum=  null;
		int sellerid=  -1;
		String sellername=  null;
		
				
			itemname = itembeanjsp.getItemName();
			author = itembeanjsp.getAuthor();
			editor = itembeanjsp.getEditor();
			title = itembeanjsp.getTitle();
			volume = itembeanjsp.getVolume();
			pubtype = itembeanjsp.getPubtype();
			itemtype = itembeanjsp.getItemtype();
			venue = itembeanjsp.getVenue();
			url = itembeanjsp.getUrl();
			onlinedate = itembeanjsp.getOnlinedate();
			address = itembeanjsp.getAddress();
			creditnum = itembeanjsp.getCreditnum();
			sellername = itembeanjsp.getSellername();
				
			%>
	<center>	
	<table border="1" width="80%">						
			<tr><td>Image</td><td><img src="upload/<%=url%>"><br></td></tr>
			<tr><td>Item Name</td><td><input type="text" name="itemname" value="<%=itemname %>"><br></td></tr>
			<tr><td>Seller Name</td><td><input type="text" name="sellername" value="<%=sellername %>"><br></td></tr>	
			<tr><td>Author</td><td><input type="text" name="author" value="<%=author %>"><br></td></tr>
			<tr><td>Editor</td>	<td><input type="text" name="editor" value="<%=editor %>"><br></td></tr>		
			<tr><td>Title</td><td><input type="text" name="title" value="<%=title %>"><br></td></tr>
			<tr><td>Volume</td><td><input type="text" name="volume" value="<%=volume %>"><br></td></tr>
			<tr><td>Pub Type</td><td><input type="text" name="pubtype" value="<%=pubtype %>"><br></td></tr>	
			<tr><td>Item Type</td><td><input type="text" name="itemtype" value="<%=itemtype %>"><br></td>	</tr>
			<tr><td>Venue</td><td><input type="text" name="venue" value="<%=venue %>"><br></td></tr>				
			<tr><td>Onlinedate</td><td><input type="text" name="onlinedate" value="<%=onlinedate %>"><br></td></tr>
			<tr><td>Address</td><td><input type="text" name="address" value="<%=address %>"><br></td></tr>
			<tr><td>Credit Card Num</td><td><input type="text" name="creditnum" value="<%=creditnum %>"><br></td></tr>
			<tr>				
			</tr>
	</table>
	</center>
  </body>
</html>
