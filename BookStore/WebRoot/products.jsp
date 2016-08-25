<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="
edu.comp9321.ass2.*,
java.util.*,
java.sql.ResultSet"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<%--   <%
  String p = request.getParameter("posttype");
  	if (p != null) {
			 response.sendRedirect("/COMP9321-ASS2-WEB/control?item_list=2&posttype='"+p+"'");
  	}
   %> --%>
    <base href="<%=basePath%>">
    
    <title>My JSP 'products.jsp' starting page</title>
    
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
<!--     This is my products page. <br> -->
    <table>
    <%
    	JourneyBean jn = (JourneyBean) request.getSession().getAttribute("journey");
    	
    	Vector<String> i_name=jn.getItem_name();
    	Vector<String> i_id=jn.getItem_id();
    	Vector<String> u=jn.getUrl();
    	int j=0;
    	if (i_name.size() < 10) {
	    	for (int i=0;i<i_name.size();i++) {
	    		if (j==0) {
	    			out.println("<tr>");
	    		}
	    		out.println("<td><center><a href='UserServlet?action=itemforall&itemid="+i_id.elementAt(i)+"'><div><div style='float:up;width:180;'><img src='upload/"+u.elementAt(i)+"' width='180' height='240'></div><div style='float:down;width:180;overflow:hidden;white-space:nowrap;color:#072A45;'>"+i_name.elementAt(i)+"</div></div></a></center></td>");
	    		j++;
	    		if (j==6) {
	    			out.println("</tr>");
	    			j=0;
	    		}
	    	}
    	}
    	else {
    		int[] reult2 = jn.randomArray(0,i_name.size()-1,10);  
		    for (int i : reult2) {  
	    		if (j==0) {
	    			out.println("<tr>");
	    		}
	    		out.println("<td><center><a href='UserServlet?action=itemforall&itemid="+i_id.elementAt(i)+"'><div><div style='float:up;width:180;'><img src='upload/"+u.elementAt(i)+"' width='180' height='240'></div><div style='float:down;width:180;overflow:hidden;white-space:nowrap;color:#072A45;'>"+i_name.elementAt(i)+"</div></div></a></center></td>");
	    		j++;
	    		if (j==6) {
	    			out.println("</tr>");
	    			j=0;
	    		}
	    	}
   		}
     %>
     </table>
  </body>
</html>
