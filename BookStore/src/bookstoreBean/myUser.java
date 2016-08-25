package bookstoreBean;



import java.util.Date;

import java.util.List;



public class myUser {

	private int userid;

	private String username;

	private String password;

	private String nickname;

	private String firstname;

	private String lastname;

	private String email;

	private String phone;

	private Date birthdate;

	private String address;

	private String creditnum;

	private String typeid;

	private List<Order> orderlist;

	public myUser(){

		

	}

	public myUser( int userid,

			 String username,

			 String password,

			 String nickname,

			 String firstname,

			 String lastname,

			 String email,

			 String phone,

			 Date birthdate,

			 String address,

			 String creditnum,

			 String typeid,

			 List<Order> orderlist){

		this.userid = userid;

		this.username = username;

		this.password = password;

		this.nickname = nickname;

		this.firstname = firstname;

		this.lastname = lastname;

		this.email = email;

		this.phone = phone;

		this.birthdate = birthdate;

		this.address = address;

		this.creditnum = creditnum;

		this.typeid = typeid;

		this.orderlist = orderlist;

	}

	

	//Define setter methods

	public void setUserid(int userid){

		this.userid = userid ;

	}

	public void setUsername(String username){

		this.username = username ;

	}

	public void setPassword(String password){

		this.password = password ;

	}

	public void setNickname(String nickname){

		this.nickname = nickname ;

	}

	public void setFirstname(String firstname){

		this.firstname = firstname ;

	}

	public void setLastname(String lastname){

		this.lastname = lastname ;

	}

	public void setEmail(String email){

		this.email = email ;

	}

	public void setPhone(String phone){

		this.phone = phone ;

	}

	public void setBirthdate(Date birthdate){

		this.birthdate = birthdate ;

	}

	public void setAddress(String address){

		this.address=address;

	}

	public void setCreditnum(String creditnum){

		this.creditnum = creditnum ;

	}



	public void setTypeid(String typeid){

		this.typeid = typeid ;

	}



	public void setOrderlist(List<Order> orderlist){

		this.orderlist = orderlist ;

	}

	//Defined getter methods

	public int getUserid(){

		return this.userid ;

	}

	public String getUsername(){

		return this.username ;

	}

	public String getPassword(){

		return this.password ;

	}

	public String getNickname(){

		return this.nickname ;

	}

	public String getFirstname(){

		return this.firstname ;

	}

	public String getLastname(){

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

	public String getAddress(){

		return this.address ;

	}

	public String getCreditnum(){

		return this.creditnum ;

	}

	public String getTypeid(){

		return this.typeid ;

	}

	public List<Order> getOrderlist(){

		return this.orderlist;

	}

}