package bookstoreServletControl;

import bookstoreBean.User;
import bookstoreDBConnection.*;
import bookstoreServletControl.IUserDAO;
import bookstoreServletControl.UserDAOImpl;

import java.sql.* ;
import java.util.List;
public class UserDAOProxy implements IUserDAO {
	private databaseConnection dbc = null ;
	private IUserDAO dao = null ;
	public UserDAOProxy(){
		try{
			this.dbc = new databaseConnection() ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
		this.dao = new UserDAOImpl(dbc.getConnection()) ;
	}
	public boolean findLogin(User user) throws Exception{
		boolean flag = false ;
		try{
			flag = this.dao.findLogin(user) ;	//get the real method;
		}catch(Exception e){
			throw e ;
		}finally{
			this.dbc.close() ;
		}
		return flag ;
	}
	
	public List<User> findAll(String keyWord) throws Exception{
		List<User> all=null;
		try{
			all=this.dao.findAll(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	
	public List<User> findSingle(String keyWord) throws Exception{
		List<User> all=null;
		try{
			all=this.dao.findSingle(keyWord);			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}
	
	
	public String banUser(String keyWord) throws Exception{
		String banResult=null;
		try{
			banResult=this.dao.banUser(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return banResult;
	}
	
	public String modUser(User user) throws Exception{
		String modResult=null;
		try{
			modResult=this.dao.modUser(user);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return modResult;
	}	
	
} 