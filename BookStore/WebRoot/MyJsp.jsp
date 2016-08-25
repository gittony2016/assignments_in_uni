<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">

  <script src="http://code.jquery.com/jquery-1.10.2.js"></script>

  <script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>

  <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">

  <script>

  $(function() {

    $( "#datepicker" ).datepicker();

  });

  </script>
  <body>
    This is my JSP page. <br>
    <p>date: <input type="text" id="datepicker"></p>
    <table>
    <tr>
    <td>123</td>
    </tr>
    <tr>
    <td>321</td>
    </tr>
    </table>
  </body>
</html>
