package jdbc;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/** For handling different select statements and corresponding results to be 
 * manipulated later on.
 *
 */

public class DBResultSetHandler extends DBConnector {
	
	DBConnector con = new DBConnector();
	
	
	/**
	 * Returns the user expenses for a specific month.
	 * 
	 * @param name
	 * @param month
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Double> getExpenses(String name, int month) throws SQLException {
		
		String query = "select * from wallettracker.expense_history where Month(issueDate) = "
				+ "? and accName =?;" ;
		PreparedStatement prQuery = null;
		ArrayList<Double> expenses = new ArrayList<Double>();
		

		prQuery = this.con.getConnection().prepareStatement(query);
		prQuery.setInt(1, month);
		prQuery.setString(2, name);
			
		ResultSet rs = prQuery.executeQuery();
			
		while (rs.next()) {
			expenses.add(rs.getDouble("amount"));
		}
			
		return expenses;
				
	}
	
	/**
	 * Returns all the account names currently available at the accounts table as a
	 * String array.
	 * @throws SQLException
	 */
	public ArrayList<String> getCurrentAccounts() throws SQLException {
		
		String query = "select * from wallettracker.accounts";
		ArrayList<String> accNames = new ArrayList<String>();

			ResultSet rs = this.con.getStatement().executeQuery(query);
			while (rs.next()) {
				
				accNames.add(rs.getString("accName"));
			}
			
		return accNames;
	}
	
	/**
	 * Prints out all current accounts in the database.
	 * @throws SQLException
	 */
	public void displayCurrentAccounts() throws SQLException {
		
		String query = "select * from wallettracker.accounts";
		

			ResultSet rs = this.con.getStatement().executeQuery(query);
			while (rs.next()) {
				
				System.out.println(rs.getString("accName"));
			}
	}
	
	/**
	 * Checks the db for existing account names, if exists, returns true.
	 * @param accName
	 * @return
	 * @throws SQLException
	 */
	public boolean checkAccountExistance(String accName) throws SQLException {
		
		boolean accountExists = false;
		ArrayList<String> existingAccounts = getCurrentAccounts();
		
		for (String acc: existingAccounts) {
			if (acc == accName) {
				accountExists = true;
			}
		}	
		return accountExists;
	}
	
	
	/**
	 * Returns the account balance for given account name
	 * @param accName
	 * @throws SQLException
	 * @return double balance
	 */
	
	public double getCurrentBalance(String accName) throws SQLException {
		
		double balance = 0.00;
		PreparedStatement prQuery = null;
		String query = "select accBalance from wallettracker.accounts where accName = ?";
		
		prQuery = this.con.getConnection().prepareStatement(query);
		prQuery.setString(1, accName);
		ResultSet rs = prQuery.executeQuery();
		
		while (rs.next()){
			balance = rs.getDouble("accBalance");
		}
		
		return balance;
		
	}
	
	/**
	 * Displays all current accounts and their balance
	 * @throws SQLException
	 */
	
	public void getCurrentBalanceAll() throws SQLException {
		
		String query = "select accName, accBalance from wallettracker.accounts";
		
		ResultSet rs = this.con.getStatement().executeQuery(query);
		
		while (rs.next()){
			
			System.out.print(rs.getString("accName") + " ");
			System.out.print(rs.getDouble("accBalance"));
			System.out.println();
		}
	}
	
	
	/**
	 * Returns the month of the last expense for a given user.
	 * @param accName
	 * @return
	 * @throws SQLException
	 */
	
	public int getLastExpenseMonth(String accName) throws SQLException {
		
		int lastMonth = 0;
		
		PreparedStatement prStatement = null;
		String prQuery = "select Month(issueDate) from expense_history where accName = ?"
				+ "order by issueDate desc limit 1";
		
		prStatement = this.con.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		ResultSet rs = prStatement.executeQuery();
		
		while (rs.next()){
			lastMonth = rs.getInt("Month(issueDate)");

		}
		return lastMonth;
		
	}
	
	public int getLastExpenseYear(String accName) throws SQLException {
		
		int lastYear = 0;
		
		PreparedStatement prStatement = null;
		String prQuery = "select Year(issueDate) from expense_history where accName = ?"
				+ "order by issueDate desc limit 1";
		
		prStatement = this.con.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		ResultSet rs = prStatement.executeQuery();
		
		while (rs.next()){
			lastYear = rs.getInt("Year(issueDate)");

		}
		return lastYear;
	}
	
	/**
	 * Returns an ArrayList with all the expenses for the given accName.
	 * @param accName
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Double> getUserExpenses(String accName) throws SQLException {
		
		ArrayList<Double> values = new ArrayList<Double>();
		
		PreparedStatement prStatement = null;
		String prQuery = "select amount from wallettracker.expense_history where accName = ?";
		
		prStatement = this.con.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		ResultSet rs = prStatement.executeQuery();
		
		while (rs.next()){
			
			values.add(rs.getDouble("amount"));
		}
		
		return values;
		
	}
	
	/**
	 * Returns a list with all the expenses for a user in a specific month.
	 * @param accName
	 * @param month
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Double> getUserExpensesForMonthYear(String accName, int month, int year) throws SQLException {
		
		ArrayList<Double> expenses = new ArrayList<Double>();
		PreparedStatement prStatement = null;
		String prQuery = "select accName,amount from wallettracker.expense_history where Month(issuedate) = ?"
				+ " and accName = ? and Year(issueDate) = ? ";
		
		prStatement = this.con.getConnection().prepareStatement(prQuery);
		prStatement.setInt(1, month);
		prStatement.setString(2, accName);
		prStatement.setInt(3, year);
		ResultSet rs = prStatement.executeQuery();
		
		while (rs.next()){
			
			expenses.add(rs.getDouble("amount"));
			
		}
		
		return expenses;
	}
	
	
	/**
	 * Returns a list with all expense dates for a given user in a given month.
	 * @param accName
	 * @param month
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Date> getUserIssueDatesForMonthYear(String accName, int month, int year) throws SQLException {
		
		ArrayList<Date> dates = new ArrayList<Date>();
		PreparedStatement prStatement = null;
		String prQuery = "select * from wallettracker.expense_history where Month(issuedate) = ? and accName =? and Year(issueDate) = ? ";
		
		prStatement = this.con.getConnection().prepareStatement(prQuery);
		prStatement.setInt(1, month);
		prStatement.setString(2, accName);
		prStatement.setInt(3, year);
		ResultSet rs = prStatement.executeQuery();
		
		while (rs.next()){
			
			dates.add(rs.getDate("issueDate"));		
		}
		
		return dates;
	}

	
	/**
	 * Displays all the data for a user for a specified month. Uses {@link getUserIssueDatesForMonth}
	 * and {@link getUserExpensesForMonth}
	 * @param accName
	 * @param month
	 * @throws SQLException
	 */
	public void displayUserExpenseDataForMonth(String accName, int month, int year) throws SQLException{
		
		ArrayList<Double> expenses = getUserExpensesForMonthYear(accName,month,year);
		ArrayList<Date> dates = getUserIssueDatesForMonthYear(accName,month,year);
		
		for (int i = 0; i < expenses.size(); i ++) {
			
			System.out.println(accName + " | " + expenses.get(i).toString() + " | " + dates.get(i).toString());
		}
	}
	
	
	
	
	public void getUserExpensesByPeriod(String accName, OVERVIEW_PERIOD Period) throws SQLException {
		
		int lastMonth = getLastExpenseMonth(accName);
		double balance_user = getCurrentBalance(accName);
		ArrayList<Double> expenses = new ArrayList<Double>();
		
		switch(Period) {
		
		case THREE_MONTHS: 
			
		case SIX_MONTHS:
		case ONE_YEAR:
		case FIVE_YEARS:
		case MAX:
		}
		
		
		PreparedStatement prStatement = null;
		String prQuery = "select amount,issueDate from wallettracker.expense_history where accName = ?"
				+ "order by issueDate desc;";
		
		prStatement = this.con.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		ResultSet rs = prStatement.executeQuery();
		
		while (rs.next()){
			//expenses.add(rs.getDouble("amount"));
			System.out.println(rs.getDate("issueDate"));
		}
		
		
	}
	
	public static void main(String args[]) throws SQLException {
		
		DBResultSetHandler rsHandler = new DBResultSetHandler();
		rsHandler.displayUserExpenseDataForMonth("nako",3,2020);
//		rsHandler.getCurrentBalanceAll();
//		System.out.println(rsHandler.getLastExpenseMonth("nako"));
		//rsHandler.getUserExpensesByPeriod("nako", OVERVIEW_PERIOD.SIX_MONTHS);
	}

}
