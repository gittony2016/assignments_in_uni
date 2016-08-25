package bookstoreServletControl;
import bookstoreBean.Cart;
import bookstoreBean.cartItem;
import bookstoreBean.itemOrder;

import java.util.List;

public interface ICartDAO {
	// This for login check,return only to result;
	public List<Cart> findAll(String keyWord) throws Exception;
	//public String delCart(String string) throws Exception;
	//public String modCart(itemOrder itemorder) throws Exception;
	public List<Cart> findSingle(String keyWord) throws Exception;
	//public User doDeleteUsername(String username) throws Exception;
	//public User findByUsername(String username) throws Exception;
	//public boolean doAddUser(User user) throws Exception;
}

