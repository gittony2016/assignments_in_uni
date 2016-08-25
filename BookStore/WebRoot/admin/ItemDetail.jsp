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
String del=request.getParameter("del");	//get the ban value
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
    
    <title>Item Detail</title>
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
			if(window.confirm("Do you confirm to " + f + " this item?")){
				if(f=="del"){
					this.document.modify.action="./admin/ItemDetail.jsp?kw=" + this.document.modify.itemid.value + "&del=true";
				}else if(f=="mod"){
					this.document.modify.action="./admin/ItemDetail.jsp?kw=" + this.document.modify.itemid.value + "&mod=true";
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

	if(del !=null && del.equals("true")){		//Do the del item
		//out.println("<script>alert('Now try to del the item');</script>");
		//out.println("Now try to del the item");
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
			String delResult=DAOFactory.getIItemDAOInstance().delItem(keyWord);
			if(delResult.equals("Del Succeed")){
				out.println("<script>alert('Del Succeed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=All_Items.jsp");
			}else{
				out.println("<script>alert('Del Failed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=All_Items.jsp");		
			}
			//Iterator<Item> iter=all.iterator();
		}catch(Exception e){
			e.printStackTrace();
		}
	}else if(mod !=null && mod.equals("true")){		//Do the modify item
		//out.println("Now try to modify the item");
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
		
			//out.println(request.getParameter("itemname"));
			Item item=new Item();
			item.setItemid(Integer.parseInt(request.getParameter("itemid")));
			item.setSellerid(Integer.parseInt(request.getParameter("sellerid")));
			item.setItemName(request.getParameter("itemname"));
			item.setAuthor(request.getParameter("author"));
			item.setEditor(request.getParameter("editor"));
			item.setTitle(request.getParameter("title"));
			item.setVolume(request.getParameter("volume"));
			item.setPubtype(request.getParameter("pubtype"));
			item.setItemtype(request.getParameter("itemtype"));
			item.setVenue(request.getParameter("venue"));
			item.setUrl(request.getParameter("url"));
			item.setOnlinedate(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("onlinedate")));	
			item.setAddress(request.getParameter("address"));					
			item.setCreditnum(request.getParameter("creditnum"));
			item.setStatus(request.getParameter("status"));
		
			String modResult=DAOFactory.getIItemDAOInstance().modItem(item);
			if(modResult.equals("Mod Succeed")){
				out.println("<script>alert('Mod Succeed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=All_Items.jsp");
			}else{
				out.println("<script>alert('Mod Failed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=All_Items.jsp");		
			}
			//Iterator<Item> iter=all.iterator();
		}catch(Exception e){
			e.printStackTrace();
		}
	}else{
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
			List<Item> all=DAOFactory.getIItemDAOInstance().findSingle(keyWord);
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

    Item Detail <br>
<form name="modify" method="post">      
	<table border="1" width="80%">

<%
			while(iter.hasNext()){
				Item item=iter.next();
 %>	
			<tr><td>Item ID</td><td><input type="text" name="itemid" value="<%=item.getItemid()%>" readonly="true"><br></td></tr>
			<tr><td>Seller ID</td><td><input type="text" name="sellerid" value="<%=item.getSellerid()%>"><br></td></tr>
			<tr><td>Item Name</td><td><input type="text" name="itemname" value="<%=item.getItemName()%>"><br></td></tr>	
			<tr><td>Author</td><td><input type="text" name="author" value="<%=item.getAuthor() %>"><br></td></tr>
			<tr><td>Editor</td>	<td><input type="text" name="editor" value="<%=item.getEditor() %>"><br></td></tr>		
			<tr><td>Title</td><td><input type="text" name="title" value="<%=item.getTitle() %>"><br></td></tr>
			<tr><td>Volume</td><td><input type="text" name="volume" value="<%=item.getVolume() %>"><br></td></tr>
			<tr><td>Pub Type</td><td><input type="text" name="pubtype" value="<%=item.getPubtype() %>"><br></td></tr>	
			<tr><td>Item Type</td><td><input type="text" name="itemtype" value="<%=item.getItemtype() %>"><br></td>	</tr>
			<tr><td>Venue</td><td><input type="text" name="venue" value="<%=item.getVenue() %>"><br></td></tr>
			<tr><td>URL</td><td><input type="text" name="url" value="<%=item.getUrl() %>"><a href="<%=item.getUrl() %>">Visit</a><br></td></tr>	
			<tr><td>Onlinedate</td><td><input type="text" name="onlinedate" value="<%=item.getOnlinedate() %>"><br></td></tr>
			<tr><td>Address</td><td><input type="text" name="address" value="<%=item.getAddress() %>"><br></td></tr>
			<tr><td>Credit Card Num</td><td><input type="text" name="creditnum" value="<%=item.getCreditnum() %>"><br></td></tr>
			<tr><td>Item Status&nbsp;(0=>Pause;&nbsp; 1=>Normal)</td><td><input type="text" name="status" value="<%=item.getStatus() %>"><br></td></tr>
			<tr>
			<!-- Use the onclick function to separate different methods and do different actions. -->
				<td align="center"><input type="submit" name="del" value="Delete" onclick="return action_confirm('del')"></td>
				<td align="center"><input type="submit" name="mod" value="Modify"  onclick="return action_confirm('mod')"></td>
				<input type="hidden" name="flag" value="false">
				<input type="hidden" name="kw" value="<%=item.getItemid()%>">
				<input type="hidden" name="typeid" value="3">
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
