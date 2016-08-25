package bookstoreServletControl;

import bookstoreServletControl.IUserDAO;
import bookstoreServletControl.UserDAOProxy;
import bookstoreServletControl.IItemDAO;
import bookstoreServletControl.ItemDAOProxy;

public class DAOFactory {
	public static IUserDAO getIUserDAOInstance(){
		return new UserDAOProxy() ;
	}
	
	public static IItemDAO getIItemDAOInstance(){
		return new ItemDAOProxy();
	}
	
	public static IItemOrderDAO getIItemOrderDAOInstance(){
		return new ItemOrderDAOProxy();
	}	
	
	public static ICartDAO getICartDAOInstance(){
		return new CartDAOProxy();
	}	
	
	public static IItemTypeDAO getIItemTypeDAOInstance(){
		return new ItemTypeDAOProxy();
	}	
}