package bookstoreServletControl;

import bookstoreBean.Item;
import bookstoreBean.User;
import bookstoreBean.itemOrder;
import bookstoreDBConnection.*;
import bookstoreServletControl.IItemOrderDAO;
import bookstoreServletControl.ItemOrderDAOImpl;

import java.sql.* ;
import java.util.List;

public class ItemOrderDAOProxy implements IItemOrderDAO {
	private databaseConnection dbc = null ;
	private IItemOrderDAO dao = null ;
	public ItemOrderDAOProxy(){
		try{
			this.dbc = new databaseConnection() ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
		this.dao = new ItemOrderDAOImpl(dbc.getConnection()) ;
	}
	
	public List<itemOrder> findAll(String keyWord) throws Exception{
		List<itemOrder> all=null;
		try{
			all=this.dao.findAll(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	public List<itemOrder> findSingle(String keyWord) throws Exception{
		List<itemOrder> all=null;
		try{
			all=this.dao.findSingle(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}
	
	
	public String delItemOrder(String keyWord) throws Exception{
		String delResult=null;
		try{
			delResult=this.dao.delItemOrder(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return delResult;
	}
	
	public String modItemOrder(itemOrder itemorder) throws Exception{
		String modResult=null;
		try{
			modResult=this.dao.modItemOrder(itemorder);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return modResult;
	}	
} 


