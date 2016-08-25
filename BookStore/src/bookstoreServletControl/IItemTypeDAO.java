package bookstoreServletControl;

import bookstoreBean.ItemType;

import java.util.List;

public interface IItemTypeDAO {
	// This for login check,return only to result;
	public List<ItemType> findAll(String keyWord) throws Exception;
	public String delItemType(String string) throws Exception;
	public String modItemType(ItemType itemtype) throws Exception;
	public List<ItemType> findSingle(String keyWord) throws Exception;
	//public User doDeleteUsername(String username) throws Exception;
	//public User findByUsername(String username) throws Exception;
	//public boolean doAddUser(User user) throws Exception;
	public String addItemType(ItemType itemtype) throws Exception;
}
