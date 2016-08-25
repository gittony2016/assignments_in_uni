<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>

<%
String userid = request.getParameter("userid");
String para = request.getParameter("para");
Cart cartbeanjsp = new Cart();
cartDAO cartdao = new  cartDAO();
cartdao.findItemlist(Integer.parseInt(userid), cartbeanjsp);
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  	<script>
	//<!-- Use the onclick function to separate different methods and do different actions. -->
		function action_confirm(f){
			//document.writeln(f);
			if(window.confirm("Do you confirm to " + f + " this item?")){
				if(f=="delete"){
					this.document.modify.action="UserServlet?action=mycart&userid="+this.document.modify.userid.value+"&para=delete";
				}
				else if(f=="buy"){
					this.document.modify.action="UserServlet?action=mycart&userid="+this.document.modify.userid.value+"&para=buy";
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
  <head>
    <base href="<%=basePath%>">    
    <title>mycart</title>    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  <body>
    <div style="width:100%;"><jsp:include page="head.jsp"/></div>
  <div>
  		<div style="width:20%; float:left;border:solid 0px #333333;vertical-align:middle;padding:50px 0px 50px 0px">
		<font size='4'><a style="color:#072A45;">User Operation:</a></font>
  		<ul>
  		<font size='4'><a href="UserServlet?action=buyerinfo&userid=<%=userid%>" style="color:#072A45;">User Information</a></font><br>
		<font size='4'><a href="UserServlet?action=mycart&userid=<%=userid%>" style="color:#072A45;">My Cart</a></font><br>
		<font size='4'><a href="UserServlet?action=allborder&userid=<%=userid%>" style="color:#072A45;">All Order</a></font><br>
		<font size='4'><a href="UserServlet?action=unconborder&userid=<%=userid%>" style="color:#072A45;">Unconfirm Order</a></font><br>
		<font size='4'><a href="UserServlet?action=conborder&userid=<%=userid%>" style="color:#072A45;">Confirmed Order</a></font><br>
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
		</tr>
	<%
		List<cartItem> itemlist = cartbeanjsp.getCartitemlist();
		if(para!=null){
			if(para.equals("delete")){
				cartdao.deleteall(Integer.parseInt(userid), cartbeanjsp);
				out.println("<script>alert('Delete Succeed!');</script>");
				response.setHeader("refresh","0;URL=UserServlet?action=mycart&userid="+userid);
			}
			else if (para.equals("buy")){
				orderDAO orderdao = new orderDAO();
				orderdao.addBOrderitem(Integer.parseInt(userid),itemlist);
				cartdao.deleteall(Integer.parseInt(userid), cartbeanjsp);
				out.println("<script>alert('Buy Succeed!waiting for email validate');</script>");
				response.setHeader("refresh","0;URL=UserServlet?action=allborder&userid="+userid);
			}
		}
		
			for(int i =0;i<itemlist.size();i++){
				if(itemlist.get(i).getItemName() != null){				
	%>		

		<tr>
			<td><a href='UserServlet?action=cartitemdetail&userid=<%=userid%>&cartitemid=<%=itemlist.get(i).getCartitemid()%>'><%=itemlist.get(i).getItemName() %></a>
			<td><a href='UserServlet?action=cartitemdetail&userid=<%=userid%>&cartitemid=<%=itemlist.get(i).getCartitemid()%>'><%=itemlist.get(i).getTitle() %></a>
			<td><a href='UserServlet?action=cartitemdetail&userid=<%=userid%>&cartitemid=<%=itemlist.get(i).getCartitemid()%>'><%=itemlist.get(i).getAuthor() %></a>
			<td><a href='UserServlet?action=cartitemdetail&userid=<%=userid%>&cartitemid=<%=itemlist.get(i).getCartitemid()%>'><%=itemlist.get(i).getOnlinedate() %></a>			
		</tr>				
	<%
		}
	    	}
     %> 
		<td align="center"><input type="submit" name="del" value="Delete" onclick="return action_confirm('delete')"></td>
		<td align="center"><input type="submit" name="buy" value="Buy"  onclick="return action_confirm('buy')"></td>	
     	<input type="hidden" name="userid" value="<%=userid%>" >
		</table>
		</form>
  </body>
</html>
