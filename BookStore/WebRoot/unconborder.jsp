<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="bookstoreBean.*"%>
<%@ page import="bookstoreDBConnection.*"%>
<%@ page import="bookstoreDAO.*"%>

<%
String para = request.getParameter("para");
String userid = request.getParameter("userid");
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>unconfirmed buyer order</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="styles.css">

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
  		
 		<table border="1" width="80%">
		<tr>			
			<td>Order id</td>	
			<td>Deal date</td>
			<td>Status</td>			
		</tr>
	
	<%
			List<Order> orderlist = new ArrayList<Order>();
			orderDAO orderdao = new orderDAO();
			orderdao.findBOrderlist(Integer.parseInt(userid), orderlist);
			String status ="";
			if(orderlist != null){
				for(int i =0;i<orderlist.size();i++){
					if(orderlist.get(i).getStatus().equals("2"))
					{
						status ="waiting for confirm";
					
 	%>
 				<tr>
 					<td><a href='UserServlet?action=orderdetail&orderid=<%=orderlist.get(i).getOrderid()%>'><%=orderlist.get(i).getOrderid() %></a>
 					<td><a href='UserServlet?action=orderdetail&orderid=<%=orderlist.get(i).getOrderid()%>'><%=orderlist.get(i).getDealdate() %></a>
 					<td><a href='UserServlet?action=orderdetail&orderid=<%=orderlist.get(i).getOrderid()%>'><%=status %></a> 				
 				</tr>

	<%
					}
				}
			}						
	 %>
		</table>
  </body>
</html>
