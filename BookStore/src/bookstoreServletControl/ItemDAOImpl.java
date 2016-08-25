package bookstoreServletControl;

import bookstoreBean.Item;
import bookstoreBean.User;
import bookstoreDBConnection.*;
import bookstoreServletControl.IItemDAO;

import java.sql.* ;
import java.util.ArrayList;
import java.util.List;
public class ItemDAOImpl implements IItemDAO {
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	public ItemDAOImpl(Connection conn){
		this.conn = conn ;
	}

	public List<Item> findAll(String keyWord) throws Exception{
		List<Item> all=new ArrayList<Item>();
		String sql="SELECT * FROM item WHERE itemname LIKE ? OR author LIKE ? OR title LIKE ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%" + keyWord + "%");
		this.pstmt.setString(2, "%" + keyWord + "%");
		this.pstmt.setString(3, "%" + keyWord + "%");

		ResultSet rs =this.pstmt.executeQuery();
		Item item=null;
		while(rs.next())
		{
			item =new Item();
			item.setItemid(rs.getInt(1));
			item.setSellerid(rs.getInt(2));
			item.setItemName(rs.getString(3));
			item.setAuthor(rs.getString(4));
			item.setEditor(rs.getString(5));
			item.setTitle(rs.getString(6));
			item.setVolume(rs.getString(7));
			item.setPubtype(rs.getString(8));
			item.setItemtype(rs.getString(9));
			item.setVenue(rs.getString(10));
			item.setUrl(rs.getString(11));
			item.setOnlinedate(rs.getDate(12));
			item.setAddress(rs.getString(13));
			item.setCreditnum(rs.getString(14));
			item.setStatus(rs.getString(15));
			all.add(item);
		}
		this.pstmt.close();
		return all;
	}
	
	public List<Item> findSingle(String keyWord) throws Exception{
		List<Item> all=new ArrayList<Item>();
		String sql="SELECT * FROM item WHERE itemid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, Integer.parseInt(keyWord));


		ResultSet rs =this.pstmt.executeQuery();
		Item item=null;
		while(rs.next())
		{
			item =new Item();
			item.setItemid(rs.getInt(1));
			item.setSellerid(rs.getInt(2));
			item.setItemName(rs.getString(3));
			item.setAuthor(rs.getString(4));
			item.setEditor(rs.getString(5));
			item.setTitle(rs.getString(6));
			item.setVolume(rs.getString(7));
			item.setPubtype(rs.getString(8));
			item.setItemtype(rs.getString(9));
			item.setVenue(rs.getString(10));
			item.setUrl(rs.getString(11));
			item.setOnlinedate(rs.getDate(12));
			item.setAddress(rs.getString(13));
			item.setCreditnum(rs.getString(14));
			item.setStatus(rs.getString(15));
			all.add(item);
		}
		this.pstmt.close();
		return all;
	}
	
	public String delItem(String keyWord) throws Exception{
		String delResult="";
		String sql="DELETE FROM ITEM WHERE itemid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, keyWord);
		
		//Use executeUpdate to do the database UPDATE
		if(this.pstmt.executeUpdate()>0){
			delResult="Del Succeed";
		}
		this.pstmt.close();

		return delResult;
	}
	
	public String modItem(Item item) throws Exception{
		String modResult="";
		String sql="UPDATE ITEM SET itemid=?, sellerid=?, itemname=?, author=?, editor=?, title=?, volume=?, pubtype=?, itemtype=?, venue=?, url=?, onlinedate=?, address=?, creditnum=?, status=? WHERE itemid=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, item.getItemid());
		this.pstmt.setInt(2,item.getSellerid());
		this.pstmt.setString(3, item.getItemName());
		this.pstmt.setString(4, item.getAuthor());
		this.pstmt.setString(5, item.getEditor());
		this.pstmt.setString(6, item.getTitle());
		this.pstmt.setString(7, item.getVolume());
		this.pstmt.setString(8, item.getPubtype());
		this.pstmt.setString(9,item.getItemtype());	//Becareful to add the getTime
		this.pstmt.setString(10, item.getVenue());
		this.pstmt.setString(11, item.getUrl());
		this.pstmt.setDate(12,new java.sql.Date(item.getOnlinedate().getTime()));	//Becareful to add the getTime
		this.pstmt.setString(13, item.getAddress());
		this.pstmt.setString(14, item.getCreditnum());
		this.pstmt.setString(15, item.getStatus());
		this.pstmt.setInt(16, item.getItemid());
			//Use executeUpdate to do the database UPDATE
			if(this.pstmt.executeUpdate() > 0){
				modResult="Mod Succeed";
			}			
		
		this.pstmt.close();
		return modResult;
	}	
} 

