package jdbc;

import java.sql.*;

public class DBConnector {
	
	//private String driverClassName = "com.mysql.jdbc.Driver";	
	private String connectionURL = "jdbc:mysql://localhost:3306/";
	private String dbName = "portfolio_management";
	private String user = "root";
	private String password = "CA2203MX";
	private String fullConnectionURL = this.connectionURL + this.dbName;
	
	
	
	public DBConnector() {
		
		//default parameters for local connection
		
		this.connectionURL = "jdbc:mysql://localhost:3306/";
		this.dbName = "wallettracker";
		this.user = "root";
		this.password = "CA2203MX";
		this.fullConnectionURL = this.connectionURL + this.dbName;
		
		
	}
	
	public DBConnector(String url, String user, String password,String dbName) {
		
		//custom parameters
		
		this.connectionURL = url;
		this.user = user;
		this.password = password;
		this.dbName = dbName;
		this.fullConnectionURL = this.connectionURL + this.dbName;
		
	}
	
	public Connection getConnection() throws SQLException {
		
		Connection con = null;
		con = DriverManager.getConnection(this.fullConnectionURL, this.user, this.password);
		return con;
	}
	
	public Statement getStatement() throws SQLException {
		
		Statement stm = null;
		stm = getConnection().createStatement();
		
		return stm;
	}
	
	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullConnectionURL() {
		return fullConnectionURL;
	}

	public void setFullConnectionURL(String fullConnectionURL) {
		this.fullConnectionURL = fullConnectionURL;
	}
	
	
	
	
}
