<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.sql.*"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>
<%
String userid = request.getParameter("userid");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<script>
	//<!-- Use the onclick function to separate different methods and do different actions. -->
		function action_confirm(f,v){
//alert("you select no");
			if(window.confirm("Do you confirm to " + f + " this item?")){
				if(f=="pause"){
					this.document.modify.action="UserServlet?action=pause&itemid="+v;						
				}
				else if(f=="active"){
					this.document.modify.action="UserServlet?action=active&itemid="+v;
				}
				
				this.document.modify.submit;
				
				return true;
			
			}else{
				
				return false;
			}
		}
	</script>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myitem.jsp' starting page</title>
    
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
<div>	
  		<div style="width:20%; float:left;border:solid 0px #333333;vertical-align:middle;padding:50px 0px 50px 0px">
  		
		<font size='4'><a style="color:#072A45;">User Operation:</a></font>
  		<ul>
  		<font size='4'><a href="UserServlet?action=sellerinfo&userid=<%=userid%>" style="color:#072A45;">User Information</a></font><br>
		<font size='4'><a href="UserServlet?action=myitem&userid=<%=userid%>" style="color:#072A45;">My Item</a></font><br>
		<font size='4'><a href="UserServlet?action=allsorder&userid=<%=userid%>&para=all" style="color:#072A45;">All Order</a></font><br>
		<font size='4'><a href="UserServlet?action=unconsorder&userid=<%=userid%>&para=uncon" style="color:#072A45;">Unconfirm Order</a></font><br>
		<font size='4'><a href="UserServlet?action=consorder&userid=<%=userid%>&para=con" style="color:#072A45;">Confirmed Order</a></font><br>
		</ul>		
 	 	</div>
 </div>
 <form name="modify" method="post">
 <table border="1" width="80%">
		<tr>			
			<td>Item Name</td>	
			<td>Title</td>
			<td>Author</td>
			<td>Online Date</td>
			<td>Status</td>
			<td>Operation</td>
		</tr>
 <%
 	List<Item> itemlist = new ArrayList<Item>();
 	userDAO userdao = new userDAO();
 	userdao.findSelleritem(Integer.parseInt(userid), itemlist);
 	for(int i=0;i<itemlist.size();i++){
 		if(itemlist.get(i).getItemName() != null){
 			String status ="";
 			String operation ="";
 			
 			if(itemlist.get(i).getStatus().equals("0")){
 				status = "paused";
 				operation = "active";
 			}
 			else if(itemlist.get(i).getStatus().equals("1")){
 				status = "online";
 				operation = "pause";
 			}
 		%>
 		
 		<tr>
			<td><a href='UserServlet?action=itemforall&userid=<%=userid%>&itemid=<%=itemlist.get(i).getItemid()%>'><%=itemlist.get(i).getItemName() %></a>
			<td><a href='UserServlet?action=itemforall&userid=<%=userid%>&itemid=<%=itemlist.get(i).getItemid()%>'><%=itemlist.get(i).getTitle() %></a>
			<td><a href='UserServlet?action=itemforall&userid=<%=userid%>&itemid=<%=itemlist.get(i).getItemid()%>'><%=itemlist.get(i).getAuthor() %></a>
			<td><a href='UserServlet?action=itemforall&userid=<%=userid%>&itemid=<%=itemlist.get(i).getItemid()%>'><%=itemlist.get(i).getOnlinedate() %></a>
			<td><a href='UserServlet?action=itemforall&userid=<%=userid%>&itemid=<%=itemlist.get(i).getItemid()%>'><%=status %></a>			
			<td><input type="submit" name="operation" value="<%=operation%>" onclick="return action_confirm('<%=operation%>','<%=itemlist.get(i).getItemid()%>')">
		</tr>
		 		
 		<% 		
 		}
 	}
  %>  
</table>
</form>	
<font size='4'><a href="uploaditem.jsp" style="color:#072A45;">upload new item</a></font><br>
  </body>
</html>
