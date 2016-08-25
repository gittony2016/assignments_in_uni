package bookstoreBean;



import java.util.List;



public class Cart {

	private int cartid;
	private int buyerid;
	private int itemlistid;
	private String username;
	private String status;
	private int cartitemid;
	private int itemid;
	private String itemname;
	private String buyername;
	private String itemstatus;
	
	private List<Item> itemlist;
	private List<cartItem> cartitemlist;

	public Cart(){
		
	}
	public Cart(int cartid,int buyerid,int itemlistid,String username,String status,int cartitemid,int itemid,String itemname,String buyername,List<Item> itemlist,List<cartItem> cartitemlist){

		this.cartid = cartid;

		this.buyerid = buyerid;
		
		this.itemlistid = itemlistid;

		this.username = username;
		this.buyername = buyername;
		this.status = status;

		this.itemlist = itemlist;
		this.cartitemid=cartitemid;
		this.itemid=itemid;
		this.itemname=itemname;
		this.itemlist = itemlist;
		this.cartitemlist = cartitemlist;

	}

	public int getCartid(){

		return cartid;

	}

	public int getBuyerid(){

		return buyerid;

	}
	public String getBuyername(){

		return buyername;

	}	
	public int getItemlistid(){

		return itemlistid;

	}
	public String getUsername(){

		return username;

	}
	
	public String getStatus(){

		return status;

	}

	public List<Item> getItemlist(){

		return itemlist;

	}
	public List<cartItem> getCartitemlist(){

		return cartitemlist;

	}
	public String getItemname(){

		return itemname;

	}
	public void setCartitemid(int cartitemid){
		this.cartitemid=cartitemid;
	}

	public void setItemid(int itemid){
		this.itemid=itemid;
	}
	

	public void setCartid(int cartid){

		this.cartid = cartid;

	}

	

	public void setBuyerid(int buyerid){

		this.buyerid = buyerid;

	}

	public void setItemlistid(int itemlistid){

		this.itemlistid = itemlistid;

	}
	public void setUsername(String username){

		this.username = username;

	}
	
	public void setBuyername(String buyername){

		this.buyername = buyername;

	}	
	public void setItemname(String itemname){

		this.itemname = itemname;

	}
	public void setStatus(String status){

		this.status = status;

	}

	public void setItemlist(List<Item> itemlist){

		this.itemlist = itemlist;

	}
	public String getItemstatus(){

		return itemstatus;

	}
	public void setCartitemlist(List<cartItem> cartitemlist){

		this.cartitemlist = cartitemlist;

	}
	public void setItemstatus(String itemstatus){

		this.itemstatus = itemstatus;

	}
	public int getCartitemid(){
		return this.cartitemid;
	}

	public int getItemid(){
		return this.itemid;
	}
}
