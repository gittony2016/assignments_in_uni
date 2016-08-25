package bookstoreServletControl;

import bookstoreBean.Item;
import bookstoreBean.User;
import bookstoreDBConnection.*;
import bookstoreServletControl.IItemDAO;
import bookstoreServletControl.ItemDAOImpl;

import java.sql.* ;
import java.util.List;
public class ItemDAOProxy implements IItemDAO {
	private databaseConnection dbc = null ;
	private IItemDAO dao = null ;
	public ItemDAOProxy(){
		try{
			this.dbc = new databaseConnection() ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
		this.dao = new ItemDAOImpl(dbc.getConnection()) ;
	}
	
	public List<Item> findAll(String keyWord) throws Exception{
		List<Item> all=null;
		try{
			all=this.dao.findAll(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	public List<Item> findSingle(String keyWord) throws Exception{
		List<Item> all=null;
		try{
			all=this.dao.findSingle(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}
	
	
	public String delItem(String keyWord) throws Exception{
		String delResult=null;
		try{
			delResult=this.dao.delItem(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return delResult;
	}
	
	public String modItem(Item item) throws Exception{
		String modResult=null;
		try{
			modResult=this.dao.modItem(item);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return modResult;
	}	
} 

