<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>


<%
	String userid = request.getParameter("userid");
	String para = request.getParameter("para");
	String cartitemid = request.getParameter("cartitemid");
				
	cartItem itembeanjsp = new cartItem();
	cartDAO cartdao = new cartDAO();
	
	cartdao.findIteminfo(Integer.parseInt(cartitemid), itembeanjsp);
					
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

  <head>
  	<script>
	//<!-- Use the onclick function to separate different methods and do different actions. -->
		function action_confirm(f){
			//document.writeln(f);
			if(window.confirm("Do you confirm to " + f + " this item?")){
				if(f=="delete"){
					this.document.modify.action="UserServlet?action=cartitemdetail&cartitemid=" + this.document.modify.cartitemid.value +"&para=delete";
				}
				else if(f=="buy"){
					this.document.modify.action="UserServlet?action=cartitemdetail&cartitemid=" + this.document.modify.cartitemid.value +"&para=buy";
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
    <base href="<%=basePath%>">
    
    <title>Cart Item Detail</title>
    
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
		
		
		if(para !=null){
			if(para.equals("delete")){
				cartdao.deleteCartitem(Integer.parseInt(cartitemid));
				out.println("<script>alert('Delete Succeed!');</script>");
				response.setHeader("refresh","0;URL=UserServlet?action=mycart&userid="+userid);
			}
			else if (para.equals("buy")){				
				cartdao.deleteCartitem(Integer.parseInt(cartitemid));				
				orderDAO orderdao = new orderDAO();				
				List<cartItem> cartitemlist = new ArrayList<cartItem>();
				cartitemlist.add(itembeanjsp);
				Order order = new Order();
				order.setBuyerid(Integer.parseInt(userid));
				order.setDealdate(new Date());
				order.setStatus("2");
				order.setItemlist(cartitemlist);
				orderdao.addOrder(Integer.parseInt(userid),order);
				out.println("<script>alert('Buy Succeed! Waiting for activate via email');</script>");								
				response.setHeader("refresh","0;URL=UserServlet?action=allborder&userid="+userid);
			}
		}
		else{			
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
			<tr><td>Image</td><td><img src="upload/<%=url%>"><input type="hidden" name="url" value="<%=url %>"><br></td></tr>
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
				<td align="center"><input type="submit" name="del" value="Delete" onclick="return action_confirm('delete')"></td>
				<td align="center"><input type="submit" name="buy" value="Buy"  onclick="return action_confirm('buy')"></td>									
				<input type="hidden" name="cartitemid" value="<%=cartitemid%>">				
			</tr>
	</table>
	</form>		
	</center>
	<%
	}
	 %>
  </body>
</html>
