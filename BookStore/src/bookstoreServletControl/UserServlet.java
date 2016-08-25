package bookstoreServletControl;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;




import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.comp9321.ass2.JourneyBean;

import java.sql.*;

import bookstoreBean.*;
import bookstoreDAO.*;


public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	
	private String action = null;
	private String userid = "-1";	
	private int typeid = -1;
	private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	String dt = fmt.format(new Date());	
	/**
	 * Constructor of the object.
	 */
	public UserServlet() {
		super();		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}
	
	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		String forwardPage = "";
		JourneyBean bean = (JourneyBean) request.getSession().getAttribute("journey");
		action = request.getParameter("action");	
//		System.out.println(action);
		if(action == null){
			throw new Exception();
		}
		
		
		if(action.equals("mainpage")){
			userid = request.getParameter("userid");
			userDAO userdao = new userDAO();
			typeid = userdao.getUsertypeid(Integer.parseInt(userid));
			if(typeid == 1){
				response.sendRedirect("UserServlet?action=buyerinfo&userid="+userid);
			}
			else if (typeid == 2){
				response.sendRedirect("UserServlet?action=sellerinfo&userid="+userid);
			}
//			else{
//				response.sendRedirect(forwardPage = "error.jsp?userid="+userid);
//			}		
			
		}
		else if(action.equals("buyerinfo")){			
			userid = request.getParameter("userid");						 
			String aparam = request.getParameter("aparam");
			
			if(aparam !=null){
				forwardPage = "buyerinfo.jsp?aparam="+aparam;
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);
			}
			else{
				response.sendRedirect("buyerinfo.jsp?userid="+userid);
			}
						
		}
		else if(action.equals("sellerinfo")){			
			userid = request.getParameter("userid");						 
			String para = request.getParameter("para");
			
			if(para !=null){
				forwardPage = "sellerinfo.jsp?para="+para;
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);
			}
			else{
				response.sendRedirect("sellerinfo.jsp?userid="+userid);
			}						
		}
		else if(action.equals("myitem")){			
			userid = request.getParameter("userid");						 			
			response.sendRedirect("myitem.jsp?userid="+userid);					
		}
		else if(action.equals("active")){
			userid = bean.getUser_id();
			String itemid = request.getParameter("itemid");
			userDAO userdao = new userDAO();
			userdao.changeStatus(itemid, "1");
			response.sendRedirect("myitem.jsp?userid="+userid);				
		}
		else if(action.equals("pause")){
			
			userid = bean.getUser_id();
			String itemid = request.getParameter("itemid");	
			userDAO userdao = new userDAO();
			userdao.changeStatus(itemid, "0");
			response.sendRedirect("myitem.jsp?userid="+userid);				
		}
		else if(action.equals("confirm")){			
			userid = bean.getUser_id();
			String orderid = request.getParameter("orderid");
			
			orderDAO orderdao = new orderDAO();
			orderdao.validateOrder(orderid);
			response.sendRedirect("allborder.jsp?userid="+userid);					
		}
		else if(action.equals("mycart")){			
			userid = request.getParameter("userid");
			
			String para = request.getParameter("para");
			
			
			if(para !=null){								
				response.sendRedirect("mycart.jsp?userid="+userid+"&para="+para);								
			}
			else{
				response.sendRedirect("mycart.jsp?userid="+userid);
			}			
		}

		else if(action.equals("allborder")){			
			userid = request.getParameter("userid");			
			response.sendRedirect("allborder.jsp?userid="+userid);
		}
		else if(action.equals("allsorder")){			
			userid = request.getParameter("userid");			
			response.sendRedirect("allsorder.jsp?userid="+userid);
		}
		else if(action.equals("addcart")){			
			userid = bean.getUser_id();	
			if(userid == null){
				response.sendRedirect("index.jsp");
			}else
			{
				
				String itemid = request.getParameter("itemid");
//				System.out.println(itemid);
				cartDAO cartdao = new cartDAO();
				cartdao.addCartitem(Integer.parseInt(userid),Integer.parseInt(itemid));
				response.sendRedirect("mycart.jsp?userid="+userid);
			}

		}
		else if(action.equals("itemforall")){			
			userid = bean.getUser_id();			
			String itemid = request.getParameter("itemid");			
			response.sendRedirect("itemforall.jsp?userid="+userid+"&itemid="+itemid);
		}
		else if(action.equals("unconborder")){			
			userid = request.getParameter("userid");			
			String para = request.getParameter("para");			
			response.sendRedirect("unconborder.jsp?userid="+userid+"&para="+para);
		}
		else if(action.equals("conborder")){			
			userid = request.getParameter("userid");			
			String para = request.getParameter("para");			
			response.sendRedirect("conborder.jsp?userid="+userid+"&para="+para);
		}
		else if(action.equals("unconsorder")){			
			userid = request.getParameter("userid");			
			String para = request.getParameter("para");			
			response.sendRedirect("unconsorder.jsp?userid="+userid);
		}
		else if(action.equals("consorder")){			
			userid = request.getParameter("userid");			
			String para = request.getParameter("para");			
			response.sendRedirect("consorder.jsp?userid="+userid);
		}
		else if(action.equals("orderdetail")){			
			String orderid = request.getParameter("orderid");	
			response.sendRedirect("orderdetail.jsp?orderid="+orderid);
		}
		else if(action.equals("orditemdetail")){			
			String itemid = request.getParameter("itemid");	
			response.sendRedirect("orditemdetail.jsp?itemid="+itemid);
		}
		else if(action.equals("cartitemdetail")){			
			userid = bean.getUser_id();			
			String para = request.getParameter("para");
			String cartitemid = request.getParameter("cartitemid");									
			if(para !=null){
				if(para.equals("delete")){
					
					response.sendRedirect("cartitemdetail.jsp?userid="+userid+"&cartitemid="+cartitemid+"&para="+para);
				}
				else if(para.equals("buy")) {
					response.sendRedirect("cartitemdetail.jsp?userid="+userid+"&cartitemid="+cartitemid+"&para="+para);
				}
				
			}
			else{
				response.sendRedirect("cartitemdetail.jsp?userid="+userid+"&cartitemid="+cartitemid);
			}			
		}
		
	}
	
	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
				processRequest(request,response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			processRequest(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
