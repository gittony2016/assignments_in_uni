package bookstoreDBConnection;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class databaseConnection {
	private Context initContext;
	private Context envContext;
	private DataSource ds = null;
	private Connection conn = null ;
	

	public databaseConnection() throws Exception{
		try{
			initContext = new InitialContext();
			envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/comp9321");			
			conn = ds.getConnection();			
		}catch(Exception e){
			throw e ;
		}
	}
	public Connection getConnection(){
		return this.conn ;
	}
	public void close() throws Exception{
		if(this.conn != null){
			try{
				this.conn.close() ;
			}catch(Exception e){
				throw e ;
			}
		}
	}
}