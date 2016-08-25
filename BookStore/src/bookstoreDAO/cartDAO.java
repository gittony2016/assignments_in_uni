package bookstoreDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bookstoreBean.Cart;
import bookstoreBean.Item;
import bookstoreBean.cartItem;
import bookstoreDBConnection.DBConnection;


public class cartDAO {
	private Connection connection =null;	
	public cartDAO(){
	}
	public void findIteminfo(int cartitemid,cartItem item) {
		try {
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
			
			String sql_getlist  = "select cartitem.cartitemid cartitemid,"
					+ "item.itemid itemid,"
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
					+ "from cartitem "
					+ "left join item on cartitem.itemid = item.itemid "				
					+ "left join user on item.sellerid = user.userid "
					+ "where cartitem.cartitemid = "+ cartitemid;
			ResultSet res_getlist = st.executeQuery(sql_getlist);			
			if(res_getlist.next()){
				item.setCartitemid(res_getlist.getInt("cartitemid"));
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
			}					
		res_getlist.close();
		st.close();
		connection.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}			
}
	public void findIteminfo(int itemid,Item item) {
		try {
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
			
			String sql_getlist  = "select "
					+ "item.itemid itemid,"
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
					+ "from item "				
					+ "left join user on item.sellerid = user.userid "
					+ "where item.itemid = "+ itemid;
			ResultSet res_getlist = st.executeQuery(sql_getlist);			
			if(res_getlist.next()){
				
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
			}					
		res_getlist.close();
		st.close();
		connection.close();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void findItemlist(int userid,Cart cartbean) {				 		
		try {
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
			String sql_getcart = " select cart.cartid, "
					+ " user.username buyername"
					+ " from cart "
					+ " left join user on user.userid = cart.buyerid "
					+ "where cart.buyerid =" + userid;
			ResultSet res_getcart = st.executeQuery(sql_getcart);
			int cartid = 0;
			if(res_getcart.next()){
				cartbean.setBuyerid(userid);				
				cartid = res_getcart.getInt("cartid");				
				cartbean.setCartid(cartid);	
				cartbean.setBuyername(res_getcart.getString("buyername"));
			}
			
			String sql_getlist  = "select cartitem.cartitemid cartitemid,"
					+ "cartitem.itemid itemid,"
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
					+ "from cart "
					+ "left join cartitem on cart.cartid = cartitem.cartid "
					+ "left join item on cartitem.itemid = item.itemid "
					+ "left join user on item.sellerid = user.userid "
					+ "where cart.cartid ="+cartid+" and cartitem.status = 1";
			ResultSet res_getlist = st.executeQuery(sql_getlist);
			List<cartItem> itemlist = new ArrayList<cartItem>();
			while(res_getlist.next()){
				cartItem item = new cartItem();
				item.setCartitemid(res_getlist.getInt("cartitemid"));
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
			cartbean.setCartitemlist(itemlist);						
			res_getcart.close();
			res_getlist.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}								
	}
	
	public void addCartitem(int userid,int itemid) {				
		try{
			
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
			String sql_search ="select cartid from cart where buyerid="+userid;
			ResultSet res_search = st.executeQuery(sql_search);
			int cartid = -1;
			
			if(res_search.next()){
				cartid = res_search.getInt("cartid");
			}
			
			String sql_insert = "INSERT INTO cartitem (cartid,itemid,status) VALUES ('"
					+ cartid
					+ "', '"
					+ itemid
					+ "', '"
					+ "1"
					+ "')";		
			st.executeUpdate(sql_insert, Statement.RETURN_GENERATED_KEYS);			
			res_search.close();
			st.close();
			connection.close();			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int addCartitem(int userid,int itemid,Cart cartbean) {		
		int cartitemid = 0;
		try{
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();
			String sql_insert = "INSERT INTO cartitem (cartid,itemid) VALUES ('"
					+ cartbean.getCartid()
					+ "', '"
					+ itemid
					+ "')";		
			st.executeUpdate(sql_insert, Statement.RETURN_GENERATED_KEYS);
			ResultSet res_insert = st.getGeneratedKeys(); 			
			if (res_insert.next()) {                    
				cartitemid = res_insert.getInt(1);                   
	           }
			
			findItemlist(userid,cartbean);						
			res_insert.close();
			st.close();
			connection.close();			
		}catch(Exception e){
			e.printStackTrace();
		}
		return cartitemid;
	}
	
	public void deleteCartitem(int userid,List<Integer> cartitemid,Cart cartbean) {			
		try{			
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();			
			for(int i =0;i<cartitemid.size();i++){
				String sql_delete = "DELETE FROM cartitem WHERE cartitemid = " + cartitemid.get(i);				
				st.executeUpdate(sql_delete);
			}
			findItemlist(userid,cartbean);
			st.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	public void deleteall(int userid,Cart cartbean) {			
		try{			
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();			
			String sql_cartid = "select cartid from cart WHERE buyerid = " + userid;
			ResultSet res_cartid = st.executeQuery(sql_cartid);
			int cartid = -1;
			if(res_cartid.next()){
				cartid = res_cartid.getInt("cartid");
			}
			String sql_cartitemid = "select cartitemid from cartitem WHERE cartid = " + cartid;
			res_cartid = st.executeQuery(sql_cartitemid);
			List<Integer> intlist = new ArrayList<Integer>();
			while(res_cartid.next()){
				intlist.add(res_cartid.getInt("cartitemid")) ;
			}
			for(int i=0;i<intlist.size();i++){
				String sql_delete = "update cartitem set status = 0 WHERE cartitemid = " + intlist.get(i) + " and status = 1";				
				st.executeUpdate(sql_delete);				
			}

			findItemlist(userid,cartbean);
			res_cartid.close();
			st.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	public void deleteCartitem(int userid,int itemid,Cart cartbean) {			
		try{			
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();						
				String sql_delete = "DELETE FROM cartitem WHERE itemid = " + itemid;				
				st.executeUpdate(sql_delete);
			findItemlist(userid,cartbean);
			st.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	public void deleteCartitem(int cartitemid) {			
		try{			
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();						
				String sql_delete = "DELETE FROM cartitem WHERE cartitemid = " + cartitemid;				
				st.executeUpdate(sql_delete);			
			st.close();
			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	
}