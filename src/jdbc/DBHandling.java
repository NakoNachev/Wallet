package jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Class for handling different inserts/updates/selections from DB.
 * Extends the DBConnector class, which has the connection,driver and login details.
 */
public class DBHandling extends DBConnector {
	
	DBConnector connector = new DBConnector();
	
	PreparedStatement prInsertNewUser = null;
	PreparedStatement prInsertExpense = null;
	
	
	/**
	 * Class for inserting new accounts in the database
	 * 
	 * @param accName 
	 * @param accBalance
	 * @param accDebt
	 */
	
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
	
	/**
	 * Inserting outgoing expenses for user, amount and the date in the DB.
	 * 
	 * @param accName
	 * @param ammount
	 * @param date
	 */
	
	public void insertNewExpense(String accName, double amount, java.sql.Date date) {
		
		String insertQuerry = "insert into wallettracker.expense_history values (?,?,?)";
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date dateWithoutTime = new Date();
//		try {
//			dateWithoutTime  = sdf.parse(sdf.format(date));
//		} catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		
		try {
			this.prInsertExpense = connector.getConnection().prepareStatement(insertQuerry);
			this.prInsertExpense.setString(1, accName);
			this.prInsertExpense.setDouble(2, amount);
			//this.prInsertExpense.setDate(3, (java.sql.Date) dateWithoutTime);
			this.prInsertExpense.setDate(3,date);
			this.prInsertExpense.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main (String args[]) {
		
		
		
	}
	

}
