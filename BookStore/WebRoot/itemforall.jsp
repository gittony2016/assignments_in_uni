<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>

<%
String userid = request.getParameter("userid");	
String itemid = request.getParameter("itemid");

Item itembeanjsp = new Item();
cartDAO cartdao = new cartDAO();
cartdao.findIteminfo(Integer.parseInt(itemid), itembeanjsp);

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<script>
	//<!-- Use the onclick function to separate different methods and do different actions. -->
		function action_confirm(f){
				if(window.confirm("Do you confirm to " + f + " this item?")){
					if(f=="cart"){
						
						this.document.modify.action="UserServlet?action=addcart&itemid=" + this.document.modify.itemid.value;
						
					}
					this.document.modify.submit;
					//alert("you select yes");
					return true;
				
				}
				else{
					//alert("you select no");
					return false;
				}
		}
	</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allitem.jsp' starting page</title>
    
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
  		if(userid == null){
			out.println("<script>alert('login please');</script>");
		}
		
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
	<form name="modify" method="post">
	<table border="1" width="80%">						
			<tr><td>Image</td><img src="upload/<%=url%>"><td><input type="hidden" name="url" value="<%=url %>"><br></td></tr>
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
			<!-- Use the onclick function to separate different methods and do different actions. -->
				<td align="center"><input type="submit" name="cart" value="Cart" onclick="return action_confirm('cart')"></td>
				<input type="hidden" name="itemid" value="<%=itemid %>">
			</tr>
	</table>
	</form>		
	</center>
  </body>
</html>
