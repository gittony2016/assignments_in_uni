<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<html>
<head><title>Administrator Login</title></head>
<%
String logout=request.getParameter("flag");
if(logout !=null && logout.equals("logout")){
	session.setAttribute("flag",false);
	out.println("<h1>Logout successful!</h1>");
	response.setHeader("refresh","1;URL=login.jsp");	
}
Boolean flag=(Boolean) session.getAttribute("flag");
%>
<script>
	function validate(f){
		if(!(/^\w{3,15}$/.test(f.userid.value))){
			alert("Username should between 3~15.") ;
			f.userid.focus() ;
			return false ;
		}
		if(!(/^\w{3,15}$/.test(f.userpass.value))){
			alert("Password should between 3~15.") ;
			f.userpass.focus() ;
			return false ;
		}
	}
</script>

<style>
.div-center{position:absolute;top:30%;left:40%;margin-top:-50px;width:300px;height:130px;border:1px solid #000}
</style>

<body>
<div class="div-center">
<form action="AdminLoginServlet" method="post" onSubmit="return validate(this)">
	<br>
	&nbsp;UserName: <input type="text" name="username" size="20"><p>
	&nbsp;Password: <input type="password" name="userpass" size="20"><p>
			<center>
			<input type="submit" value="Login">
			<input type="reset" value="Reset">
			<input type="hidden" name="flag" value="false">
			<input type="hidden" name="typeid" value="3">
			</center>
</form>
<%
	List<String> info = (List<String>) request.getAttribute("info") ;
	if(info != null){	// have return info
		Iterator<String> iter = info.iterator() ;
		while(iter.hasNext()){
%>
			<h4><%=iter.next()%></h4>
<%
		}	
	}
		if(flag !=null && flag.equals(true)){
			out.println("Redirecting after 3 seconds,if not redirect please click here: <h2><a href='UserList.jsp'>UserList</a><h2>");
			response.setHeader("refresh","3;URL=UserList.jsp");
		}
		
		/*
		else{
			session.setAttribute("flag", false);
			response.setHeader("refresh","3URL=login.jsp");
		}	
		*/	
		
%>
</div>

</body>
</html>
