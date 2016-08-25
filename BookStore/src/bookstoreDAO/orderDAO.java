package bookstoreDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

import bookstoreBean.Item;
import bookstoreBean.Order;
import bookstoreBean.cartItem;
import bookstoreDBConnection.DBConnection;


public class orderDAO {
	private Connection connection =null;
	private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	private String dt = fmt.format(new Date());
	public orderDAO(){	
	}
	
	public void sendMail (String mail,int orderid) {
		
		//System.out.println(mail+"**********************");
	      String to = mail;
	      String from = "zhuweic@hotmail.com";
	      String host = "smtp-mail.outlook.com";
	      Properties properties = System.getProperties();
	      properties.put("mail.smtp.starttls.enable", "true");
	      properties.put("mail.smtp.host", host);
	      properties.put("mail.smtp.user", from);
	      properties.put("mail.smtp.password", "7758521a");
	      properties.put("mail.smtp.port", "587");
	      properties.put("mail.smtp.auth", "true");
	      Session session = Session.getDefaultInstance(properties);
	      try{
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));
	         message.setSubject("This is the Subject Line!");
	         message.setContent("<h1>This is an official e-mail! Please click the link below to confirm your order!"
	         		+ "</h1><h3><a href='http://localhost:8080/BookStore/UserServlet?action=confirm"
	         		+ "&orderid="+orderid+"'>Click to active!</a></h3>"
	         		,"text/html;charset=UTF-8");
	         Transport transport = session.getTransport("smtp");
	         transport.connect(host, from, "7758521a");
	         transport.sendMessage(message, message.getAllRecipients());
	         transport.close();
	         
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}	
	public void addOrder(int userid,Order order) {				
		int orderid = -1;
		try{			
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
						
			

			String sql_insert = "insert into OrderInfo (buyerid,dealdate,status) VALUES('"+userid+"','"+dt+"','"+order.getStatus()+"')";						
			st.executeUpdate(sql_insert, Statement.RETURN_GENERATED_KEYS);
			ResultSet res_insert = st.getGeneratedKeys(); 			
			
			if (res_insert.next()) {                    
				orderid = res_insert.getInt(1);
	        }
			
			for(int i =0;i<order.getItemlist().size();i++){
				sql_insert = "insert into OrderItem (itemid,orderid) VALUES ('"+order.getItemlist().get(i).getItemid()+"','"+orderid+"')";
				st.executeUpdate(sql_insert, Statement.RETURN_GENERATED_KEYS);
			}
			String sql_search="select email from user where userid="+userid;
			ResultSet res_search = st.executeQuery(sql_search);
			String email = "";
			if(res_search.next()){				
				email = res_search.getString("email");			
			}
			if(email.equals("")){
				System.out.println("not get email");
			}
			else{
				sendMail(email.toString(),orderid);
			}
			res_search.close();
			res_insert.close();
			st.close();
			connection.close();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void findBOrderlist(int userid,List<Order> orderbean) {		
		List<cartItem> itemlist = new ArrayList<cartItem>();		
		try {
			ResultSet res_getlist = null;
			ResultSet res_getorder = null;
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
			String sql_getorder = "select orderid, " 			
			+ "status, "
			+ "dealdate "
			+ "from OrderInfo where buyerid = " + userid;
			res_getorder = st.executeQuery(sql_getorder);						
			while(res_getorder.next()){
				Order ord = new Order();
				ord.setOrderid(res_getorder.getInt("orderid"));				
				ord.setStatus(res_getorder.getString("status"));
				ord.setDealdate(res_getorder.getDate("dealdate"));
				orderbean.add(ord);
			}
			if(orderbean != null){
				for(int i =0;i<orderbean.size();i++){
					String sql_getlist  = "select item.itemid itemid,"
							+ "item.itemname itemname,"
							+ "item.author author,"
							+ "item.editor editor,"
							+ "item.title title,"
							+ "item.volume volume,"
							+ "item.pubtype pubtype,"
							+ "item.itemtype itemtype,"
							+ "item.venue venue,"
							+ "item.url url,"
							+ "item.onlinedate onlinedate,"
							+ "item.address address,"
							+ "item.creditnum creditnum,"
							+ "item.sellerid sellerid,"					
							+ "user.username sellername "
							+ "from OrderInfo "
							+ "left join OrderItem on OrderInfo.orderid = OrderItem.orderid "
							+ "left join item on OrderItem.itemid = item.itemid "
							+ "left join user on item.sellerid = user.userid "
							+ "where OrderInfo.orderid =" + orderbean.get(i).getOrderid();
					res_getlist = st.executeQuery(sql_getlist);
					itemlist = new ArrayList<cartItem>();
					while(res_getlist.next()){
						cartItem item = new cartItem();					
						item.setItemid(res_getlist.getInt("itemid"));
						item.setItemName(res_getlist.getString("itemname"));
						item.setEditor(res_getlist.getString("editor"));
						item.setTitle(res_getlist.getString("title"));
						item.setVolume(res_getlist.getString("volume"));
						item.setPubtype(res_getlist.getString("pubtype"));
						item.setItemtype(res_getlist.getString("itemtype"));
						item.setVenue(res_getlist.getString("venue"));
						item.setUrl(res_getlist.getString("url"));
						item.setOnlinedate(res_getlist.getDate("onlinedate"));
						item.setAddress(res_getlist.getString("address"));
						item.setCreditnum(res_getlist.getString("creditnum"));
						item.setSellerid(res_getlist.getInt("sellerid"));
						item.setSellername(res_getlist.getString("sellername"));
						itemlist.add(item);					
					}
					orderbean.get(i).setItemlist(itemlist);
				}																
			}						
			res_getorder.close();			
			res_getlist.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}								
	}
	
	public void findSOrderlist(int userid,List<Order> orderbean) {		
				
		try {
			
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
			String sql_getlist  = "select  t1.orderid orderid,t1.dealdate dealdate,t1.status status,t2.username buyername from "
					+ "( "
					+ "select orderinfo.orderid orderid,orderinfo.buyerid buyerid,orderinfo.dealdate dealdate,orderinfo.status status,count(*)  count "
					+ "from user "
					+ "left join item on user.userid = item.sellerid "
					+ "left join orderitem on item.itemid = orderitem.itemid "
					+ "left join orderinfo on orderitem.orderid = orderinfo.orderid "
					+ "where user.userid ="+userid+" and orderinfo.orderid is not null "
					+ "group by orderinfo.orderid,orderinfo.buyerid,orderinfo.buyerid,orderinfo.dealdate,orderinfo.status "
					+ ") as t1 "
					+ "left join user as t2 on t1.buyerid = t2.userid";
			ResultSet res_getlist = st.executeQuery(sql_getlist);
			while(res_getlist.next()){
						Order order = new Order();						
						order.setBuyername(res_getlist.getString("buyername"));
						order.setOrderid(res_getlist.getInt("orderid"));
						order.setDealdate(res_getlist.getDate("dealdate"));
						order.setStatus(res_getlist.getString("status"));
						orderbean.add(order);					
					}																																		
			res_getlist.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}								
	}
	public void findOitemist(int orderid,List<Item> itemlist) {						
		try {
			connection = DBConnection.getConnection();
			ResultSet res_getlist = null;			
			Statement st = connection.createStatement();							
			String sql_getlist  = "select item.itemid itemid,"
							+ "item.itemname itemname,"
							+ "item.author author,"
							+ "item.editor editor,"
							+ "item.title title,"
							+ "item.volume volume,"
							+ "item.pubtype pubtype,"
							+ "item.itemtype itemtype,"
							+ "item.venue venue,"
							+ "item.url url,"
							+ "item.onlinedate onlinedate,"
							+ "item.address address,"
							+ "item.creditnum creditnum,"
							+ "item.sellerid sellerid,"					
							+ "user.username sellername "
							+ "from OrderInfo "
							+ "left join OrderItem on OrderInfo.orderid = OrderItem.orderid "
							+ "left join item on OrderItem.itemid = item.itemid "
							+ "left join user on item.sellerid = user.userid "
							+ "where OrderInfo.orderid =" + orderid;
					res_getlist = st.executeQuery(sql_getlist);					
					while(res_getlist.next()){
						Item item = new Item();					
						item.setItemid(res_getlist.getInt("itemid"));
						item.setItemName(res_getlist.getString("itemname"));
						item.setEditor(res_getlist.getString("editor"));
						item.setTitle(res_getlist.getString("title"));
						item.setVolume(res_getlist.getString("volume"));
						item.setPubtype(res_getlist.getString("pubtype"));
						item.setItemtype(res_getlist.getString("itemtype"));
						item.setVenue(res_getlist.getString("venue"));
						item.setUrl(res_getlist.getString("url"));
						item.setOnlinedate(res_getlist.getDate("onlinedate"));
						item.setAddress(res_getlist.getString("address"));
						item.setCreditnum(res_getlist.getString("creditnum"));
						item.setSellerid(res_getlist.getInt("sellerid"));
						item.setSellername(res_getlist.getString("sellername"));
						itemlist.add(item);					
					}
					res_getlist.close();						
					st.close();
					connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}								
	}
	public void addBOrderitem(int userid,List<cartItem> itemlist) {				
		int orderid = -1;
		try{						
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();			 			
			String sql_insertin = "INSERT INTO OrderInfo (buyerid,dealdate,status) "
					+ "VALUES ("
					+ "'"+ userid+ "', "
					+ "'"+ dt+ "', "
					+ "'2')";		
			st.executeUpdate(sql_insertin, Statement.RETURN_GENERATED_KEYS);
			ResultSet res_insert = st.getGeneratedKeys();
			if (res_insert.next()) {                    
				orderid = res_insert.getInt(1);                   
	         }
			
			for(int i =0;i<itemlist.size();i++){
				String sql_insertit = "INSERT INTO OrderItem (itemid,orderid) "
						+ "VALUES ("
						+ "'"+ itemlist.get(i).getItemid()+ "', "
						+ "'"+orderid+ "')";
				st.executeUpdate(sql_insertit);
			}			
			String sql_search="select email from user where userid="+userid;
			ResultSet res_search = st.executeQuery(sql_search);
			String email = "";
			if(res_search.next()){				
				email = res_search.getString("email");			
			}
			if(email.equals("")){
				System.out.println("not get email");
			}
			else{
				sendMail(email.toString(),orderid);
			}
			res_search.close();
			res_insert.close();			
			st.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public void addBOrderitem(int userid,List<Integer> itemid,List<Order> orderbean) {				
		int orderid = 0;
		try{						
			Statement st = connection.createStatement();			 			
			String sql_insertin = "INSERT INTO OrderInfo (buyerid,dealdate,status) "
					+ "VALUES ("
					+ "'"+ userid+ "', "
					+ "'"+ dt+ "', "
					+ "'1')";		
			st.executeUpdate(sql_insertin, Statement.RETURN_GENERATED_KEYS);
			ResultSet res_insert = st.getGeneratedKeys();
			if (res_insert.next()) {                    
				orderid = res_insert.getInt(1);                   
	         }
			for(int i =0;i<itemid.size();i++){
				String sql_insertit = "INSERT INTO OrderItem (itemid,orderid) "
						+ "VALUES ("
						+ "'"+ itemid.get(i)+ "', "
						+ "'"+orderid+ "')";
				st.executeUpdate(sql_insertit);
			}
			findBOrderlist(userid,orderbean);
			res_insert.close();			
			st.close();						
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void addBOrderitem(int userid,int orderid,List<Integer> itemid,List<Order> orderbean) {						
		try{						
			Statement st = connection.createStatement();			 			
			for(int i =0;i<itemid.size();i++){
				String sql_insertit = "INSERT INTO OrderItem (itemid,orderid) "
						+ "VALUES ("
						+ "'"+ itemid.get(i)+ "', "
						+ "'"+ orderid+ "')";
				st.executeUpdate(sql_insertit);
			}
			findBOrderlist(userid,orderbean);
			st.close();						
		}catch(Exception e){
				e.printStackTrace();
		}
	}
	
	public void deleteBOrderitem(int userid,int orderid,List<Order> orderbean) {		
		try{			
			Statement st = connection.createStatement();						
				String sql_deletein = "DELETE FROM OrderInfo WHERE orderid = " + orderid;			
				st.executeUpdate(sql_deletein);
				String sql_deleteit = "DELETE FROM OrderItem WHERE orderid = " + orderid;			
				st.executeUpdate(sql_deleteit);
			findBOrderlist(userid,orderbean);
			st.close();		
		}catch(Exception e){
			e.printStackTrace();
		}		
	}	
	public void deleteBOrderitem(int userid,List<Integer> orderitemid,int orderid,List<Order> orderbean) {		
		try{			
			Statement st = connection.createStatement();
			String sql_delete = "";
			for(int i =0;i<orderitemid.size();i++){
				sql_delete = "DELETE FROM OrderItem WHERE orderid = " + orderid
						+" and orderitemid = "+orderitemid.get(i);				
				st.executeUpdate(sql_delete);					
			}
			String sql_check = "select 1 FROM OrderItem WHERE orderid = " + orderid;
			ResultSet res_getlist = st.executeQuery(sql_check);
			if(res_getlist.wasNull()){
				deleteBOrderitem(userid,orderid,orderbean);
			}
			findBOrderlist(userid,orderbean);
			res_getlist.close();
			st.close();		
		}catch(Exception e){
			e.printStackTrace();
		}		
	}

	public void validateOrder(String orderid) {
		try{	
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
			String sql_update = "UPDATE orderinfo SET status= '1' "
					+ "where orderid ="+orderid;			
			st.executeUpdate(sql_update);			
			st.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}				// TODO Auto-generated method stub
		
	}

	
}
