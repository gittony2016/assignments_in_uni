package bookstoreServletControl;

import bookstoreBean.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IUserDAO {
	// This for login check,return only to result;
	public boolean findLogin(User user) throws Exception ;
	public List<User> findAll(String keyWord) throws Exception;
	public String banUser(String string) throws Exception;
	public String modUser(User user) throws Exception;
	//public User doDeleteUsername(String username) throws Exception;
	//public User findByUsername(String username) throws Exception;
	//public boolean doAddUser(User user) throws Exception;
	public List<User> findSingle(String keyWord)  throws Exception;
} 