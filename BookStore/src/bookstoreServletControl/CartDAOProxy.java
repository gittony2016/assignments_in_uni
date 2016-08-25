package bookstoreServletControl;

import java.util.List;

import bookstoreBean.Cart;
import bookstoreBean.itemOrder;

import bookstoreBean.Item;
import bookstoreBean.User;
import bookstoreDBConnection.*;
import bookstoreServletControl.ICartDAO;
import bookstoreServletControl.CartDAOImpl;

import java.sql.* ;


public class CartDAOProxy implements ICartDAO {
	private databaseConnection dbc = null ;
	private ICartDAO dao = null ;
	public CartDAOProxy(){
		try{
			this.dbc = new databaseConnection() ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
		this.dao = new CartDAOImpl(dbc.getConnection()) ;
	}
	
	public List<Cart> findAll(String keyWord) throws Exception{
		List<Cart> all=null;
		try{
			all=this.dao.findAll(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	public List<Cart> findSingle(String keyWord) throws Exception{
		List<Cart> all=null;
		try{
			all=this.dao.findSingle(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}
	
	/*
	public String delCart(String keyWord) throws Exception{
		String delResult=null;
		try{
			delResult=this.dao.delCart(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return delResult;
	}
	
	public String modCart(Cart cart) throws Exception{
		String modResult=null;
		try{
			modResult=this.dao.modCart(cart);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return modResult;
	}	
	
	*/
} 


