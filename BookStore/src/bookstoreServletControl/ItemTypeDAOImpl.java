package bookstoreServletControl;

import bookstoreBean.ItemType;
import bookstoreServletControl.IItemTypeDAO;

import java.sql.* ;
import java.util.ArrayList;
import java.util.List;

public class ItemTypeDAOImpl implements IItemTypeDAO {
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	public ItemTypeDAOImpl(Connection conn){
		this.conn = conn ;
	}

	public List<ItemType> findAll(String keyWord) throws Exception{
		List<ItemType> all=new ArrayList<ItemType>();
		String sql="SELECT * FROM itemtype WHERE listid LIKE ? OR listname LIKE ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, "%" + keyWord + "%");
		this.pstmt.setString(2, "%" + keyWord + "%");

		ResultSet rs =this.pstmt.executeQuery();
		ItemType itemtype=null;
		while(rs.next())
		{
			itemtype =new ItemType();
			itemtype.setListid(rs.getInt(1));
			itemtype.setListname(rs.getString(2));
			all.add(itemtype);
		}
		this.pstmt.close();
		return all;
	}
	
	public List<ItemType> findSingle(String keyWord) throws Exception{
		List<ItemType> all=new ArrayList<ItemType>();
		String sql="SELECT * FROM itemtype WHERE listid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, Integer.parseInt(keyWord));


		ResultSet rs =this.pstmt.executeQuery();
		ItemType itemtype=null;
		while(rs.next())
		{
			itemtype =new ItemType();
			itemtype.setListid(rs.getInt(1));
			itemtype.setListname(rs.getString(2));
			all.add(itemtype);
		}
		this.pstmt.close();
		return all;
	}
	
	public String delItemType(String keyWord) throws Exception{
		String delResult="";
		String sql="DELETE FROM ITEMTYPE WHERE LISTID = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, keyWord);
		
		//Use executeUpdate to do the database UPDATE
		if(this.pstmt.executeUpdate()>0){
			delResult="Del Succeed";
		}
		this.pstmt.close();

		return delResult;
	}
	
	public String modItemType(ItemType itemtype) throws Exception{
		String modResult="";
		String sql="UPDATE ITEMTYPE SET listid=?, listname=? WHERE listid=?";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, itemtype.getListid());
		this.pstmt.setString(2, itemtype.getListname());
		this.pstmt.setInt(3, itemtype.getListid());
			//Use executeUpdate to do the database UPDATE
			if(this.pstmt.executeUpdate() > 0){
				modResult="Mod Succeed";
			}			
		
		this.pstmt.close();
		return modResult;
	}
	
	public String addItemType(ItemType itemtype) throws Exception{
		String addResult="";
		String sql="INSERT INTO ITEMTYPE (listid,listname) VALUES(?,?)";

		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, itemtype.getListid());
		this.pstmt.setString(2, itemtype.getListname());
			//Use executeUpdate to do the database UPDATE
			if(this.pstmt.executeUpdate() > 0){
				addResult="Add Succeed";
			}			
		
		this.pstmt.close();
		return addResult;
	}
} 

