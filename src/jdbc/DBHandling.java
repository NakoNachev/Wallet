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
	DBResultSetHandler rshandler = new DBResultSetHandler();

	/**
	 * Class for inserting new accounts into the database.
	 * 
	 * @param accName 
	 * @param accBalance
	 * @param accDebt
	 * @throws SQLException 
	 */	
	public void insertNewUser(String accName, double accBalance, double accDebt) throws SQLException {

		String insert = "insert into wallettracker.accounts values (?,?,?)";
		
//		try {
			this.prInsertNewUser = connector.getConnection().prepareStatement(insert);
			this.prInsertNewUser.setString(1, accName);
			this.prInsertNewUser.setDouble(2, accBalance);
			this.prInsertNewUser.setDouble(3, accDebt);
			
			this.prInsertNewUser.executeUpdate();
			
			connector.getConnection().close();
			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	
	}
	
	/**
	 * Inserting outgoing expenses for user, amount and the date in the DB. Uses a user-defined
	 * date, by converting it to java.sql.Date.
	 * 
	 * @param accName
	 * @param ammount
	 * @param date
	 */
	
	public void insertNewExpense(String accName, double amount, java.util.Date date) {
		
		String insertQuerry = "insert into wallettracker.expense_history values (?,?,?)";
		
		java.sql.Date dateSQL = new java.sql.Date(date.getTime());
		
		
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
			this.prInsertExpense.setDate(3,dateSQL);
			this.prInsertExpense.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	/**
	 *Inserts new expense in the database using the current date. 
	 * @param accName
	 * @param amount
	 * @param date
	 */
	
	public void insertNewExpense(String accName, double amount, java.sql.Date date) {
		
		String insertQuerry = "insert into wallettracker.expense_history values (?,?,?)";
		date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
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
	
	
	
	/**
	 * Updates the current balance of given user. Needed for when the user enters some type
	 * of income.
	 * @param accName
	 * @param amount
	 * @throws SQLException
	 */
	
	public void updateBalance(String accName, double amount) throws SQLException {
		
		double currentBalance = rshandler.getCurrentBalance(accName);
		double newBalance = currentBalance + amount;
		
		String prQuery = "update wallettracker.accounts set accBalance = ? where accName = ?";
		PreparedStatement prStatement = null;
		
		prStatement = connector.getConnection().prepareStatement(prQuery);
		prStatement.setDouble(1, newBalance);
		prStatement.setString(2, accName);
		prStatement.executeUpdate();
	}
	
	public void deleteExpense(String accName,java.util.Date date){
		
	}
	
	
	public void setBalance(String accName, double amount){
		
	}
	
	public void setDebt(String accName, double amount){
		
		
	}
	
	public void deleteUser(String accName) {
		
	}
	
	
	
	

	public static void main (String args[]) throws SQLException {
		
		DBHandling dbhandler = new DBHandling();
		dbhandler.insertNewUser("nako", 0, 0);
		//dbhandler.updateBalance("nako", 300.00);
	}
}
