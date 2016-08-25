package bookstoreDBConnection;

import java.sql.Connection;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class DBConnection {
	private static DBConnection factory = null;
	private Context initContext;
	private Context envContext;
	private DataSource ds = null;
	
	private DBConnection(){	
		try {
			initContext = new InitialContext();
			envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/comp9321");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}
	
	private DataSource getDataSource(){
		return ds;
	}

	public static Connection getConnection() throws Exception{
		
		if(factory==null)
			factory = new DBConnection();
		Connection conn = factory.getDataSource().getConnection();		
		return conn;
	}	
}
