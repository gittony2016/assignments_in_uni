package bookstoreBean;

import java.util.Date;

public class User {
	private int userid;
	private String username;
	private String nickname;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private Date birthdate;
	private String typeid;
	private String password;
	private String address;
	private String creditnum;
	public User(){
		
	}
	
	public User(int userid, 
			String username, 
			String nickname,
			String firstname,
			String lastname,
			String email,
			String phone,
			Date birthdate,
			String typeid,
			String password,
			String address,
			String creditnum			
			){
		this.userid = userid ;
		this.username = username ;
		this.nickname = nickname ;
		this.firstname = firstname ;
		this.lastname = lastname ;
		this.email = email ;
		this.phone = phone ;
		this.birthdate = birthdate ;
		this.typeid = typeid ;
		this.password = password ;
		this.address = address ;
		this.creditnum = creditnum ;
	}
//Define setter methods
	public void setUserid(int userid){
		this.userid = userid ;
	}
	public void setUserName(String username){
		this.username = username ;
	}
	public void setNickName(String nickname){
		this.nickname = nickname ;
	}
	public void setFirstName(String firstname){
		this.firstname = firstname ;
	}
	public void setLastName(String lastname){
		this.lastname = lastname ;
	}
	public void setEmail(String email){
		this.email = email ;
	}
	public void setPhone(String phone){
		this.phone = phone ;
	}
	public void setDate(Date birthdate){
		this.birthdate = birthdate ;
	}
	public void setTypeid(String typeid){
		this.typeid = typeid ;
	}
	public void setPassword(String password){
		this.password = password ;
	}
	public void setAddress(String address){
		this.address = address ;
	}
	public void setCreditnum(String creditnum){
		this.creditnum = creditnum ;
	}

	
	//Defined getter methods
	public int getUserid(){
		return this.userid ;
	}
	public String getUserName(){
		return this.username ;
	}
	public String getNickName(){
		return this.nickname ;
	}
	public String getFirstName(){
		return this.firstname ;
	}
	public String getLastName(){
		return this.lastname ;
	}
	public String getEmail(){
		return this.email ;
	}
	public String getPhone(){
		return this.phone ;
	}
	public Date getBirthdate(){
		return this.birthdate ;
	}
	public String getTypeid(){
		return this.typeid ;
	}
	public String getPassword(){
		return this.password ;
	}
	public String getAddress(){
		return this.address ;
	}
	public String getCreditnum(){
		return this.creditnum ;
	}
}
