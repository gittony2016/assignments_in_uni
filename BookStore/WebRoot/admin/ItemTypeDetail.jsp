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
String add=request.getParameter("add");	//get the modify value
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
    
    <title>ItemType Detail</title>
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
			if(window.confirm("Do you confirm to " + f + " this itemtype?")){
				if(f=="del"){
					this.document.modify.action="./admin/ItemTypeDetail.jsp?kw=" + this.document.modify.listid.value + "&del=true";
				}else if(f=="mod"){
					this.document.modify.action="./admin/ItemTypeDetail.jsp?kw=" + this.document.modify.listid.value + "&mod=true";
				}/*else if(f=="add"){
					this.document.modify.action="./admin/ItemTypeDetail.jsp?kw=" + this.document.modify.listid.value + "&add=true";
				}*/
				
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

	if(del !=null && del.equals("true")){		//Do the del itemtype
		//out.println("<script>alert('Now try to del the itemtype');</script>");
		//out.println("Now try to del the itemtype");
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
			String delResult=DAOFactory.getIItemTypeDAOInstance().delItemType(keyWord);
			if(delResult.equals("Del Succeed")){
				out.println("<script>alert('Del Succeed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=ItemTypeList.jsp");
			}else{
				out.println("<script>alert('Del Failed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=ItemTypeList.jsp");		
			}
			//Iterator<Item> iter=all.iterator();
		}catch(Exception e){
			e.printStackTrace();
		}
	}else if(mod !=null && mod.equals("true")){		//Do the modify itemtype
		//out.println("Now try to modify the itemtype");
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
		
			//out.println(request.getParameter("itemname"));
			ItemType itemtype=new ItemType();
			itemtype.setListid(Integer.parseInt(request.getParameter("listid")));
			itemtype.setListname(request.getParameter("listname"));
		
			String modResult=DAOFactory.getIItemTypeDAOInstance().modItemType(itemtype);
			if(modResult.equals("Mod Succeed")){
				out.println("<script>alert('Mod Succeed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=ItemTypeList.jsp");
			}else{
				out.println("<script>alert('Mod Failed!');</script>");
				//out.println("Ban Succeed!");
				response.setHeader("refresh","0;URL=ItemTypeList.jsp");		
			}
			//Iterator<Item> iter=all.iterator();
		}catch(Exception e){
			e.printStackTrace();
		}
	}else if(add !=null && add.equals("true")){		//Do the add itemtype
		//out.println("Now try to add the itemtype");
		try{
			String keyWord=request.getParameter("kw");
			if(keyWord==null){
				keyWord="";
			}
		
			//out.println(request.getParameter("itemname"));
			ItemType itemtype=new ItemType();
			itemtype.setListid(Integer.parseInt(request.getParameter("listid")));
			itemtype.setListname(request.getParameter("listname"));
		
			String addResult=DAOFactory.getIItemTypeDAOInstance().addItemType(itemtype);
			if(addResult.equals("Add Succeed")){
				out.println("<script>alert('Add Succeed!');</script>");
				//out.println("Add Succeed!");
				response.setHeader("refresh","0;URL=ItemTypeList.jsp");
			}else{
				out.println("<script>alert('Add Failed!');</script>");
				//out.println("Add Succeed!");
				response.setHeader("refresh","0;URL=ItemTypeList.jsp");		
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
			List<ItemType> all=DAOFactory.getIItemTypeDAOInstance().findSingle(keyWord);
			Iterator<ItemType> iter=all.iterator();
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
<form action="./admin/ItemTypeList.jsp" method="post">
	Please enter KeyWord&nbsp;: &nbsp;(Support: ItemId, ItemName)<p>
	<input type="text" name="kw" size="50">
	<input type="submit" value="Search">
	
</form>

    ItemType Detail <br>
<form name="modify" method="post">      
	<table border="1" width="80%">

<%
			while(iter.hasNext()){
				ItemType itemtype=iter.next();
 %>	
			<tr><td>ItemType ID</td><td><input type="text" name="listid" value="<%=itemtype.getListid()%>" readonly="true"><br></td></tr>
			<tr><td>ItemType Name</td><td><input type="text" name="listname" value="<%=itemtype.getListname()%>"><br></td></tr>	
			<tr>
			<!-- Use the onclick function to separate different methods and do different actions. -->
				<td align="center"><input type="submit" name="del" value="Delete" onclick="return action_confirm('del')"></td>
				<td align="center"><input type="submit" name="mod" value="Modify"  onclick="return action_confirm('mod')"></td>
				<input type="hidden" name="flag" value="false">
				<input type="hidden" name="kw" value="<%=itemtype.getListid()%>">
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
