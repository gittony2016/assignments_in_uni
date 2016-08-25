package bookstoreServletControl;

import bookstoreBean.Item;
import bookstoreBean.User;
import bookstoreBean.itemOrder;

import java.util.List;

public interface IItemOrderDAO {
	// This for login check,return only to result;
	public List<itemOrder> findAll(String keyWord) throws Exception;
	public String delItemOrder(String string) throws Exception;
	public String modItemOrder(itemOrder itemorder) throws Exception;
	public List<itemOrder> findSingle(String keyWord) throws Exception;
	//public User doDeleteUsername(String username) throws Exception;
	//public User findByUsername(String username) throws Exception;
	//public boolean doAddUser(User user) throws Exception;
}
