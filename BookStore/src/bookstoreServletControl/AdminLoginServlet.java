package bookstoreServletControl;

import java.io.* ;
import java.util.* ;
import java.util.List;

import javax.servlet.* ;
import javax.servlet.http.* ;

import bookstoreServletControl.DAOFactory;
import bookstoreBean.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;


public class AdminLoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		String path = "login.jsp" ;
		Boolean flag=false; 	//Define whether login is successful.
		//req.setAttribute("flag", flag);
		req.getSession().setAttribute("flag", flag);
		//System.out.println(req.toString());
		String username = req.getParameter("username") ;
		String userpass = req.getParameter("userpass") ;
		String typeid=req.getParameter("typeid");
		List<String> info = new ArrayList<String>() ;	//get error
		//System.out.println(username);
		if(username==null || "".equals(username)){
			info.add("Username can not be empty!") ;
		}
		if(userpass==null || "".equals(userpass)){
			info.add("Password can not be empty!") ;
		}
		if(info.size()==0){	// If nothing in the info.
			//User user = new User(0, username, null, null, null, null, null, null, null, userpass, null, null) ;
			User user = new User();
			user.setUserName(username);
			user.setPassword(userpass) ;
			user.setTypeid(typeid);
			try{
				if(DAOFactory.getIUserDAOInstance().findLogin(user)){
					info.add("Welcome:" + user.getUserName() + " Login!,Now change to UserList page.") ;
					flag=true;
					//req.setAttribute("flag", flag);
					req.getSession().setAttribute("flag", flag);
				} else {
					info.add("Fail!Wrong username or password.") ;
					flag=false;
					req.getSession().setAttribute("flag", flag);
				}
			}catch(Exception e){
				e.printStackTrace() ;
			}
		}
		req.setAttribute("info",info) ;
		req.getRequestDispatcher(path).forward(req,resp) ;
	}
	public void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		this.doGet(req,resp) ;
	}


}