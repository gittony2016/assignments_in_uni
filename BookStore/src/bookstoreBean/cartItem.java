package bookstoreBean;

import java.util.Date;

public class cartItem {
	private int cartitemid;
	private int cartid;
	private int itemid;	

	private String itemname;

	private String author;

	private String editor;

	private String title;

	private String volume;

	private String pubtype;

	private String itemtype;

	private String venue;

	private String url;

	private Date onlinedate;

	private String address;

	private String creditnum;

	private int sellerid;

	private String sellername;

	private String status;
	
	public cartItem(){
		
	}
	public cartItem(int cartitemid, int cartid, int itemid,

			  String itemname,

			  String author,

			  String editor,

			  String title,

			  String volume,

			  String pubtype,

			  String itemtype,

			  String venue,

			  String url,

			  Date onlinedate,

			  String address,

			  String creditnum,

			  int sellerid,

			  String sellername,
			  String status){
		this.cartitemid=cartitemid;
		this.cartid=cartid;
		this.itemid = itemid ;

		this.itemname = itemname ;

		this.author = author ;

		this.editor = editor ;

		this.title = title ;

		this.volume = volume ;

		this.pubtype = pubtype ;

		this.itemtype = itemtype ;

		this.venue = venue ;

		this.url = url ;

		this.onlinedate = onlinedate ;

		this.address=address;

		this.creditnum = creditnum ;

		this.sellerid = sellerid ;

		this.sellername = sellername ;
		
		this.status=status;
	}
	
	//Define setter methods;
	public void setCartitemid(int cartitemid){
		this.cartitemid=cartitemid;
	}
	public void setCartid(int cartid){
		this.cartid=cartid;
	}
	public void setItemid(int itemid){

		this.itemid = itemid ;

	}

	public void setItemName(String itemname){

		this.itemname = itemname ;

	}

	public void setAuthor(String author){

		this.author = author ;

	}

	public void setEditor(String editor){

		this.editor = editor ;

	}

	public void setTitle(String title){

		this.title = title ;

	}

	public void setVolume(String volume){

		this.volume = volume ;

	}

	public void setPubtype(String pubtype){

		this.pubtype = pubtype ;

	}

	public void setItemtype(String itemtype){

		this.itemtype = itemtype ;

	}

	public void setVenue(String venue){

		this.venue = venue ;

	}

	public void setUrl(String url){

		this.url = url ;

	}

	public void setOnlinedate(Date onlinedate){

		this.onlinedate = onlinedate ;

	}

	public void setAddress(String address){

		this.address=address;

	}

	public void setCreditnum(String creditnum){

		this.creditnum = creditnum ;

	}

	public void setSellerid(int sellerid){

		this.sellerid = sellerid ;

	}

	public void setSellername(String sellername){

		this.sellername = sellername ;

	}
	public void setStatus(String status){

		this.status = status ;

	}
	
	//Define getter methods;
	public int getCartitemid(){
		return this.cartitemid;
	}
	public int getCartid(){
		return this.cartid;
	}
	public int getItemid(){

		return this.itemid ;

	}

	public String getItemName(){

		return this.itemname ;

	}

	public String getAuthor(){

		return this.author ;

	}

	public String getEditor(){

		return this.editor ;

	}

	public String getTitle(){

		return this.title ;

	}

	public String getVolume(){

		return this.volume ;

	}

	public String getPubtype(){

		return this.pubtype ;

	}

	public String getItemtype(){

		return this.itemtype ;

	}

	public String getVenue(){

		return this.venue ;

	}

	public String getUrl(){

		return this.url ;

	}

	public Date getOnlinedate(){

		return this.onlinedate ;

	}

	public String getAddress(){

		return this.address ;

	}

	public String getCreditnum(){

		return this.creditnum ;

	}

	public int getSellerid(){

		return this.sellerid ;

	}

	public String getSellername(){

		return this.sellername ;

	}
	
	public String getStatus(){

		return this.status;

	}

}
