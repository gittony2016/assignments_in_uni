package bookstoreBean;

import java.util.Date;

public class itemOrder {
	private int orderid;
	private int itemid;
	private String itemname;
	private int buyerid;
	private int sellerid;
	private Date dealdate;
	private String status;
	private String username;
	private int orderitemid;
	public itemOrder(){
		
	}
	public itemOrder(int orderid, int itemid, int buyerid, int sellerid, Date dealdate, String status, int orderitemid, String username){
		this.orderid = orderid ;
		this.itemid = itemid ;
		this.buyerid = buyerid ;
		this.sellerid = sellerid ;
		this.dealdate = dealdate ;
		this.status=status;
		this.orderitemid=orderitemid;
		this.username=username;
	}

	//Define setter methods
	public void setOrderid(int orderid){
		this.orderid = orderid ;
	}
	public void setItemid(int itemid){
		this.itemid = itemid ;
	}	
	public void setItemname(String itemname){
		this.itemname = itemname ;
	}	
	public void setBuyerid(int buyerid){
		this.buyerid = buyerid ;
	}	
	public void setSellerid(int sellerid){
		this.sellerid = sellerid ;
	}
	public void setDealdate(Date dealdate){
		this.dealdate = dealdate ;
	}
	public void setStatus(String status){
		this.status=status;
	}
	public void setOrderitemid(int orderitemid){
		this.orderitemid=orderitemid;
	}
	public void setUsername(String username){
		this.username=username;
	}
	
	//Defined getter methods
	public int getOrderid(){
		return this.orderid ;
	}
	public int getItemid(){
		return this.itemid ;
	}
	public String getItemname(){
		return this.itemname ;
	}
	public int getBuyerid(){
		return this.buyerid ;
	}
	public int getSellerid(){
		return this.sellerid ;
	}
	public Date getDealdate(){
		return this.dealdate ;
	}
	public String getStatus(){
		return this.status ;
	}
	public int getOrderitemid(){
		return this.orderitemid ;
	}
	public String getUsername(){
		return this.username;
	}
}
