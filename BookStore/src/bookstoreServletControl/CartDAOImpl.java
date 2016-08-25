package bookstoreServletControl;

import java.util.List;

import bookstoreBean.Cart;
import bookstoreBean.cartItem;
import bookstoreDBConnection.*;
import bookstoreServletControl.IItemDAO;
import bookstoreServletControl.IItemOrderDAO;

import java.sql.* ;
import java.util.ArrayList;





import bookstoreBean.itemOrder;

public class CartDAOImpl implements ICartDAO {
	private Connection conn = null ;
	private PreparedStatement pstmt = null ;
	public CartDAOImpl(Connection conn){
		this.conn = conn ;
	}

	public List<Cart> findAll(String keyWord) throws Exception{
		List<Cart> all=new ArrayList<Cart>();
		/*String sql="SELECT orderinfo.orderid o_orderid, orderinfo.buyerid o_buyerid, "
				+ "orderinfo.dealdate o_dealdate, orderinfo.status o_status,"
				+ "orderitem.orderitemid oi_orderitemid, orderitem.itemid oi_itemid, orderitem.orderid oi_orderid, "
				+ "user.userid u_id, user.username u_name, "
				+ "item.itemid i_id, item.sellerid i_sellerid, item.itemname i_name "
				+ " FROM orderinfo left join orderitem on orderinfo.orderid=orderitem.orderid left join user on orderinfo.buyerid=user.userid where orderinfo.buyerid=?";
		*/
		String sql="SELECT CART.*, USER.USERID, USER.USERNAME FROM CART LEFT JOIN USER ON CART.BUYERID=USER.USERID WHERE BUYERID=? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, Integer.parseInt(keyWord));

		ResultSet rs =this.pstmt.executeQuery();
		Cart cart=null;
		while(rs.next())
		{
			cart =new Cart();
			cart.setCartid(rs.getInt(1));
			cart.setBuyerid(rs.getInt(2));
			cart.setItemlistid(rs.getInt(3));
			cart.setStatus(rs.getString(4));
			cart.setUsername(rs.getString(6));
			//cart.setCartitemid(rs.getInt(6));
			//cart.setItemid(rs.getInt(7));
			//itemorder.setOrderitemid(rs.getInt(5));
			//itemorder.setItemid(rs.getInt(6));
			//itemorder.setOrderid(rs.getInt(7));
			all.add(cart);
		}
		this.pstmt.close();
		return all;
	}
	
	public List<Cart> findSingle(String keyWord) throws Exception{
		List<Cart> all=new ArrayList<Cart>();
		String sql="SELECT CARTITEM.CARTITEMID, CARTITEM.CARTID, CARTITEM.ITEMID, CARTITEM.STATUS, ITEM.ITEMNAME, ITEM.ITEMID FROM CARTITEM LEFT JOIN ITEM ON CARTITEM.ITEMID=ITEM.ITEMID WHERE CARTITEM.CARTID=? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, Integer.parseInt(keyWord));


		ResultSet rs =this.pstmt.executeQuery();
		Cart cart=null;
		while(rs.next())
		{
			cart =new Cart();
			cart.setCartitemid(rs.getInt(1));
			cart.setCartid(rs.getInt(2));
			cart.setItemid(rs.getInt(3));
			cart.setItemstatus(rs.getString(4));
			cart.setItemname(rs.getString(5));
			//cart.setStatus(rs.getString(6));
			//cart.setCartitemid(rs.getInt(7));
			//cart.setItemid(rs.getInt(8));
			//itemorder.setOrderitemid(rs.getInt(5));
			//itemorder.setItemid(rs.getInt(6));
			//itemorder.setOrderid(rs.getInt(7));
			all.add(cart);
		}
		this.pstmt.close();
		return all;
	}

	/*
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
	
	*/
} 
