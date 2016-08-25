package bookstoreBean;


import java.util.Date;
import java.util.List;

public class Order {
	private String buyername;
	private int orderid;
	private Date dealdate;
	private int buyerid;
	private String status;
	private List<cartItem> itemlist;
	
	public Order(){
		
	}
	public Order(String buyername,int orderid,Date dealdate,String status,int buyerid,List<cartItem> itemlist){
		this.buyername = buyername;
		this.orderid = orderid;
		this.dealdate = dealdate;
		this.status = status;
		this.buyerid = buyerid;
		this.itemlist = itemlist;
	}
	public int getOrderid(){
		return orderid;
	}
	public String getBuyername(){
		return buyername;
	}
	public Date getDealdate(){
		return dealdate;
	}
	public String getStatus(){
		return status;
	}
	public int getBuyerid(){
		return buyerid;
	}
	public List<cartItem> getItemlist(){
		return itemlist;
	}	
	public void setOrderid(int orderid){
		this.orderid = orderid;
	}			
	public void setDealdate(Date dealdate){
		this.dealdate = dealdate;
	}
	public void setBuyerid(int buyerid){
		this.buyerid = buyerid;
	}
	public void setStatus(String status){
		this.status = status;
	}
	
	public void setItemlist(List<cartItem> itemlist){
		this.itemlist = itemlist;
	}
	public void setBuyername(String buyername){
		this.buyername = buyername;
	}
}
