package jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * Class for handling different inserts/updates/selections from DB.
 * Extends the DBConnector class, which has the connection,driver and login details.
 */
public class DBHandling extends DBConnector {
	
	DBConnector connector = new DBConnector();
	
	PreparedStatement prInsertNewUser = null;
	
	
	public void insertNewUser(String accName, double accBalance, double accDebt) {

		String insert = "insert into wallettracker.accounts values (?,?,?)";
		
		try {
			this.prInsertNewUser = connector.getConnection().prepareStatement(insert);
			this.prInsertNewUser.setString(1, accName);
			this.prInsertNewUser.setDouble(2, accBalance);
			this.prInsertNewUser.setDouble(3, accDebt);
			
			this.prInsertNewUser.executeUpdate();
			
			connector.getConnection().close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void main (String args[]) {
		
		DBHandling handler = new DBHandling();
		handler.insertNewUser("nako", 3335.43, 0);
		
	}
	

}
