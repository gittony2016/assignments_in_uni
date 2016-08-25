package bookstoreBean;

public class ItemType {
	private int listid;
	private String listname;
	
	public ItemType(){
		
	}
	public ItemType(int listid, String listname){
		this.listid=listid;
		this.listname=listname;		
	}
	//Defined setter methods;
	public void setListid(int listid){
		this.listid=listid;
	}
	public void setListname(String listname){
		this.listname=listname;
	}
	
	//Defined getter methods;
	public int getListid(){
		return this.listid;
	}
	public String getListname(){
		return this.listname;
	}

}
