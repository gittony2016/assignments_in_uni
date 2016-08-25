package bookstoreDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;



import bookstoreBean.Item;
import bookstoreBean.myUser;
import bookstoreDBConnection.DBConnection;


public class userDAO {
	private Connection connection =null;
	private DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
	private String dt = fmt.format(new Date());
	public userDAO(){
	}

	public void getIteminfo(int itemid,Item item){
		Statement st;
		try {
			connection = DBConnection.getConnection();
			st = connection.createStatement();
			String sql_getitem  = "select item.itemid itemid,"
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
					+ "where item.itemid ="+itemid;
			ResultSet res_getitem = st.executeQuery(sql_getitem);
			if(res_getitem == null){
				
			}
			if(res_getitem.next()){				
				item.setItemid(res_getitem.getInt("itemid"));
				item.setItemName(res_getitem.getString("itemname"));
				item.setEditor(res_getitem.getString("editor"));
				item.setTitle(res_getitem.getString("title"));
				item.setVolume(res_getitem.getString("volume"));
				item.setPubtype(res_getitem.getString("pubtype"));
				item.setItemtype(res_getitem.getString("itemtype"));
				item.setVenue(res_getitem.getString("venue"));
				item.setUrl(res_getitem.getString("url"));
				item.setOnlinedate(res_getitem.getDate("onlinedate"));
				item.setAddress(res_getitem.getString("address"));
				item.setCreditnum(res_getitem.getString("creditnum"));
				item.setSellerid(res_getitem.getInt("sellerid"));
				item.setSellername(res_getitem.getString("sellername"));
			}
			res_getitem.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public int getUsertypeid(int userid){
		Statement st;
		int typeid = 999;
		try {
			connection = DBConnection.getConnection();
			st = connection.createStatement();
			String sql_gettypeid = "select typeid from User where userid = "+userid;
			ResultSet res_gettypeid = st.executeQuery(sql_gettypeid);
			if(res_gettypeid.next()){				
				typeid = res_gettypeid.getInt("typeid");								
			}
			res_gettypeid.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return typeid;		
	}
	public void changeStatus(String itemid,String status){
		Statement st;
		try {
			connection = DBConnection.getConnection();
			st = connection.createStatement();
			String sql_gettypeid = "update item set status = "+status+" where itemid = "+itemid;
			st.executeUpdate(sql_gettypeid);
			st.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	public void findSelleritem(int userid,List<Item> itemlist){
		try {
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();			
			String sql_getitem  = "SELECT "
			+"itemid, "
			+"itemname, "
			+"title, "
			+"author, "
			+"onlinedate, "
			+ "status "			
			+"FROM item "
			+ "where sellerid =" + userid ;
			ResultSet res_getitem = st.executeQuery(sql_getitem);
			while(res_getitem.next()) {
				Item item = new Item();
				item.setItemid(res_getitem.getInt("itemid"));
				item.setItemName(res_getitem.getString("itemname"));
				item.setTitle(res_getitem.getString("title"));
				item.setAuthor(res_getitem.getString("author"));
				item.setOnlinedate(res_getitem.getDate("onlinedate"));
				item.setStatus(res_getitem.getString("status"));
				itemlist.add(item);				
			}
			res_getitem.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	public void findUserinfo(int userid,myUser userbean) {		
		try {
			connection = DBConnection.getConnection();
			Statement st = connection.createStatement();			
			String sql_getusrinf  = "SELECT "
			+"username, "
			+"password, "
			+"nickname, "
			+"firstname, "
			+"lastname, "
			+"email, "
			+"phone, "
			+"birthdate, "
			+"address, "
			+"creditnum, "
			+"typeid "
			+"FROM user "
			+ "where userid =" + userid ;
			ResultSet res_getusrinf = st.executeQuery(sql_getusrinf);
			if (res_getusrinf.next()) {
				userbean.setUserid(userid);	
				userbean.setUsername(res_getusrinf.getString("username"));
				userbean.setPassword(res_getusrinf.getString("password"));
				userbean.setNickname(res_getusrinf.getString("nickname"));
				userbean.setFirstname(res_getusrinf.getString("firstname"));
				userbean.setLastname(res_getusrinf.getString("lastname"));
				userbean.setEmail(res_getusrinf.getString("email"));
				userbean.setPhone(res_getusrinf.getString("phone"));
				userbean.setBirthdate(res_getusrinf.getDate("birthdate"));
				userbean.setAddress(res_getusrinf.getString("address"));
				userbean.setCreditnum(res_getusrinf.getString("creditnum"));
				userbean.setTypeid(res_getusrinf.getString("typeid"));
				
			}
			res_getusrinf.close();
			st.close();
			connection.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}								
	}
	public void modifyUser(myUser userbean){
		try {
			connection = DBConnection.getConnection();			
			String sql_updateusr  ="UPDATE user SET userid=?,"
					+ " username=?,"
					+ " password=?,"
					+ " nickname=?,"
					+ " firstname=?,"
					+ " lastname=?,"
					+ " email=?,"
					+ " phone=?,"
					+ " birthdate=?,"
					+ " address=?,"
					+ " creditnum=?,"
					+ " typeid=?"
					+ " WHERE userid=?";
			PreparedStatement st = connection.prepareStatement(sql_updateusr);
			st.setInt(1, userbean.getUserid());
			st.setString(2,userbean.getUsername());
			st.setString(3, userbean.getPassword());
			st.setString(4, userbean.getNickname());
			st.setString(5, userbean.getFirstname());
			st.setString(6, userbean.getLastname());
			st.setString(7, userbean.getEmail());
			st.setString(8, userbean.getPhone());
			st.setDate(9,new java.sql.Date(userbean.getBirthdate().getTime()));	//Becareful to add the getTime
			st.setString(10, userbean.getAddress());
			st.setString(11, userbean.getCreditnum());
			st.setString(12, userbean.getTypeid());
			st.setInt(13, userbean.getUserid());
			st.executeUpdate();
			st.close();
			connection.close();
			findUserinfo(userbean.getUserid(),userbean);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}