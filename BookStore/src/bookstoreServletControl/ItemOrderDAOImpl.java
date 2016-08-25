package bookstoreServletControl;

import java.util.List;

import bookstoreBean.Item;
import bookstoreBean.User;
import bookstoreDBConnection.*;
import bookstoreServletControl.IItemDAO;
import bookstoreServletControl.IItemOrderDAO;

import java.sql.* ;
import java.util.ArrayList;




import bookstoreBean.itemOrder;

public class ItemOrderDAOImpl implements IItemOrderDAO {
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	public ItemOrderDAOImpl(Connection conn){
		this.conn = conn ;
	}

	public List<itemOrder> findAll(String keyWord) throws Exception{
		List<itemOrder> all=new ArrayList<itemOrder>();
		/*String sql="SELECT orderinfo.orderid o_orderid, orderinfo.buyerid o_buyerid, "
				+ "orderinfo.dealdate o_dealdate, orderinfo.status o_status,"
				+ "orderitem.orderitemid oi_orderitemid, orderitem.itemid oi_itemid, orderitem.orderid oi_orderid, "
				+ "user.userid u_id, user.username u_name, "
				+ "item.itemid i_id, item.sellerid i_sellerid, item.itemname i_name "
				+ " FROM orderinfo left join orderitem on orderinfo.orderid=orderitem.orderid left join user on orderinfo.buyerid=user.userid where orderinfo.buyerid=?";
		*/
		String sql="SELECT ORDERINFO.*, USER.USERID, USER.USERNAME FROM ORDERINFO LEFT JOIN USER ON ORDERINFO.BUYERID=USER.USERID WHERE BUYERID=? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, Integer.parseInt(keyWord));

		ResultSet rs =this.pstmt.executeQuery();
		itemOrder itemorder=null;
		while(rs.next())
		{
			itemorder =new itemOrder();
			itemorder.setOrderid(rs.getInt(1));
			itemorder.setBuyerid(rs.getInt(2));
			itemorder.setDealdate(rs.getDate(3));
			itemorder.setStatus(rs.getString(4));
			itemorder.setUsername(rs.getString(6));
			//itemorder.setOrderitemid(rs.getInt(5));
			//itemorder.setItemid(rs.getInt(6));
			//itemorder.setOrderid(rs.getInt(7));
			all.add(itemorder);
		}
		this.pstmt.close();
		return all;
	}
	
	public List<itemOrder> findSingle(String keyWord) throws Exception{
		List<itemOrder> all=new ArrayList<itemOrder>();
		String sql="SELECT ORDERITEM.ORDERITEMID, ORDERITEM.ORDERID, ORDERITEM.ITEMID, ITEM.ITEMNAME, ITEM.ITEMID FROM ORDERITEM LEFT JOIN ITEM ON ORDERITEM.ITEMID=ITEM.ITEMID WHERE ORDERITEM.ORDERID=? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, Integer.parseInt(keyWord));

		ResultSet rs =this.pstmt.executeQuery();
		itemOrder itemorder=null;
		while(rs.next())
		{
			itemorder =new itemOrder();
			itemorder.setOrderitemid(rs.getInt(1));
			itemorder.setOrderid(rs.getInt(2));			
			itemorder.setItemid(rs.getInt(3));
			itemorder.setItemname(rs.getString(4));

			all.add(itemorder);
		}
		this.pstmt.close();
		return all;
	}
	
	public String delItemOrder(String keyWord) throws Exception{
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
	
	public String modItemOrder(itemOrder itemorder) throws Exception{
		String modResult="";
		String sql="UPDATE ITEM SET itemid=?, sellerid=?, itemname=?, author=?, editor=?, title=?, volume=?, pubtype=?, itemtype=?, venue=?, url=?, onlinedate=?, address=?, creditnum=? WHERE itemid=?";
	
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, itemorder.getOrderid());
		this.pstmt.setInt(2,itemorder.getBuyerid());
		this.pstmt.setDate(3,new java.sql.Date(itemorder.getDealdate().getTime()));	//Becareful to add the getTime		
		this.pstmt.setString(4, itemorder.getStatus());
		this.pstmt.setInt(5, itemorder.getOrderitemid());
		this.pstmt.setInt(6, itemorder.getItemid());
		this.pstmt.setInt(7, itemorder.getOrderid());
		this.pstmt.setInt(15, itemorder.getItemid());
			//Use executeUpdate to do the database UPDATE
			if(this.pstmt.executeUpdate() > 0){
				modResult="Mod Succeed";
			}			
		
		this.pstmt.close();
		return modResult;
	}	
} 

