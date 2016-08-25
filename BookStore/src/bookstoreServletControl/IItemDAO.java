package bookstoreServletControl;



import bookstoreBean.Item;
import bookstoreBean.User;

import java.util.List;

public interface IItemDAO {
	// This for login check,return only to result;
	public List<Item> findAll(String keyWord) throws Exception;
	public String delItem(String string) throws Exception;
	public String modItem(Item item) throws Exception;
	public List<Item> findSingle(String keyWord) throws Exception;
	//public User doDeleteUsername(String username) throws Exception;
	//public User findByUsername(String username) throws Exception;
	//public boolean doAddUser(User user) throws Exception;
}
