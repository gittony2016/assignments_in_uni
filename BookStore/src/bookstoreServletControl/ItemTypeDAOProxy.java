package bookstoreServletControl;
import bookstoreBean.ItemType;
import bookstoreBean.User;
import bookstoreDBConnection.*;
import bookstoreServletControl.IItemTypeDAO;
import bookstoreServletControl.ItemTypeDAOImpl;

import java.sql.* ;
import java.util.List;
public class ItemTypeDAOProxy implements IItemTypeDAO {
	private databaseConnection dbc = null ;
	private IItemTypeDAO dao = null ;
	public ItemTypeDAOProxy(){
		try{
			this.dbc = new databaseConnection() ;
		}catch(Exception e){
			e.printStackTrace() ;
		}
		this.dao = new ItemTypeDAOImpl(dbc.getConnection()) ;
	}
	
	public List<ItemType> findAll(String keyWord) throws Exception{
		List<ItemType> all=null;
		try{
			all=this.dao.findAll(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}

	public List<ItemType> findSingle(String keyWord) throws Exception{
		List<ItemType> all=null;
		try{
			all=this.dao.findSingle(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return all;
	}
	
	
	public String delItemType(String keyWord) throws Exception{
		String delResult=null;
		try{
			delResult=this.dao.delItemType(keyWord);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return delResult;
	}
	
	public String modItemType(ItemType itemtype) throws Exception{
		String modResult=null;
		try{
			modResult=this.dao.modItemType(itemtype);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return modResult;
	}	
	
	public String addItemType(ItemType itemtype) throws Exception{
		String addResult=null;
		try{
			addResult=this.dao.addItemType(itemtype);
			
		}catch(Exception e){
			throw e;
		}finally{
			this.dbc.close();
		}
		return addResult;
	}	
} 
