package jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;


/**
 * Class for handling different inserts/updates/selections from DB.
 * Extends the DBConnector class, which has the connection,driver and login details.
 */
public class DBHandling extends DBConnector {
	
	DBConnector connector = new DBConnector();
	
	PreparedStatement prInsertNewUser = null;
	PreparedStatement prInsertExpense = null;	
	DBResultSetHandler rshandler = new DBResultSetHandler();

	
	public void insertNewUser(String accName) throws SQLException{
		
		PreparedStatement prStatement = null;
		String prQuery = "insert into wallettracker.accounts values (?,?,?)";
		
		prStatement = this.connector.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		prStatement.setDouble(2, 0.0);
		prStatement.setDouble(3, 0.0);
		prStatement.executeUpdate();
		
	
	}
	
	public void insertNewUser(String accName, double accBalance) throws SQLException{
		
		PreparedStatement prStatement = null;
		String prQuery = "insert into wallettracker.accounts values (?,?,?)";
		
		prStatement = this.connector.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		prStatement.setDouble(2, accBalance);
		prStatement.setDouble(3, 0.0);
		prStatement.executeUpdate();

	}
	
	/**
	 * method for inserting new accounts into the database.
	 * 
	 * @param accName 
	 * @param accBalance
	 * @param accDebt
	 * @throws SQLException 
	 */	
	public void insertNewUser(String accName, double accBalance, double accDebt) throws SQLException {

		String insert = "insert into wallettracker.accounts values (?,?,?)";
		

			this.prInsertNewUser = connector.getConnection().prepareStatement(insert);
			this.prInsertNewUser.setString(1, accName);
			this.prInsertNewUser.setDouble(2, accBalance);
			this.prInsertNewUser.setDouble(3, accDebt);
			
			this.prInsertNewUser.executeUpdate();
			
			connector.getConnection().close();

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
	
	public void deleteExpense(String accName,java.util.Date date) throws SQLException{
		
		java.sql.Date dateSQL = new java.sql.Date(date.getTime());
		PreparedStatement prStatement = null;
		String prQuery = "delete from wallettracker.accounts where accName = ? and issueDate= ?";
		
		prStatement = this.connector.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		prStatement.setDate(2, dateSQL);
		prStatement.executeUpdate();
	}
	
	/**
	 * Sets the value for the balance of a given user.
	 * @param accName
	 * @param amount
	 * @throws SQLException
	 */
	public void setBalance(String accName, double amount) throws SQLException{
		
		PreparedStatement prStatement = null;
		String prQuery = "update wallettracker.accounts set accBalance = ? where accName = ?";
		
		prStatement = this.connector.getConnection().prepareStatement(prQuery);
		prStatement.setDouble(1, amount);
		prStatement.setString(2, accName);
		prStatement.executeUpdate();
	}
	
	public void setDebt(String accName, double amount) throws SQLException{
		
		PreparedStatement prStatement = null;
		String prQuery = "update wallettracker.accounts set accDebt = ? where accName = ?";
		
		prStatement = this.connector.getConnection().prepareStatement(prQuery);
		prStatement.setDouble(1, amount);
		prStatement.setString(2, accName);
		prStatement.executeUpdate();
	
	}
	
	
	public void deleteUser(String accName) throws SQLException {
		
		PreparedStatement prStatement = null;
		String prQuery = "delete from wallettracker.accounts where accName = ?";
		
		
		prStatement = this.connector.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		prStatement.executeUpdate();
	}
	
	public void changeExpenseValue(String accName, java.util.Date date){
		// change the value of the expense amount for a specific expense
	}
	
	public void changeAccountName(String accName, String newName) throws SQLException{
		
		// check if another account with the new name exists
		// meaning newName != to a existing name
		
		if (rshandler.checkAccountExistance(newName) == true){
			System.out.println("The given new name already exists, please choose another!");
		}
		else {
			
			PreparedStatement prStatement = null;
			String prQuery = "update wallettracker.accounts set accName = ? where accName = ?";
			
			prStatement = this.connector.getConnection().prepareStatement(prQuery);
			prStatement.setString(1, newName);
			prStatement.setString(2, accName);
			prStatement.executeUpdate();
		}
	}
	
	/* Adds new income to the database and updates the current balance of the user.
	 * 
	 * 
	 */
	public void addNewIncome(String accName, double amount, java.util.Date issueDate ) throws SQLException {
		
		PreparedStatement prStatement = null;
		java.sql.Date sqlDate = new java.sql.Date(issueDate.getTime());
		
		String prQuery = "insert into wallettracker.income values (?,?,?) ";
		
		prStatement = this.connector.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		prStatement.setDouble(2, amount);
		prStatement.setDate(3, sqlDate);
		
		prStatement.executeUpdate();
		
		// update balance with the new income
		
		updateBalance(accName,amount);
		
		this.connector.getConnection().close();
		
	}
	
	
	/**
	 * Sets the account information to the default values, meaning 0 debt and 0 balance.
	 * @param accName
	 * @throws SQLException
	 */
	public void resetUserData(String accName) throws SQLException{
		
		PreparedStatement prStatement = null;
		String prQuery = "update wallettracker.accounts set accBalance = 0.0 and accDebt = 0.0 where accName = ?";
		
		prStatement = this.connector.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		prStatement.executeUpdate();
		
	}

	public static void main (String args[]) throws SQLException {
		
		DBHandling dbhandler = new DBHandling();
		//dbhandler.insertNewUser("nako", 0, 0);
//		dbhandler.deleteUser("ceco");
//		dbhandler.changeAccountName("bako", "nako");
		dbhandler.insertNewUser("bako");
		//dbhandler.updateBalance("nako", 300.00);
	}
}
