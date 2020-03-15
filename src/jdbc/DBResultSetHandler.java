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
	 * Returns the user expenses for a specific month
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
	 * Returns all the account names currently available at the accounts table
	 * @throws SQLException
	 */
	public void getCurrentAccounts() throws SQLException {
		
		String query = "select * from wallettracker.accounts";
		

			ResultSet rs = this.con.getStatement().executeQuery(query);
			while (rs.next()) {
				
				System.out.println(rs.getString("accName"));
			}
	}
	
	/**
	 * Returns the account balance for given account name
	 * @param accName
	 * @throws SQLException
	 * @return double balance
	 */
	
	public double getCurrentBalance(String accName) throws SQLException {
		
		double balance;
		PreparedStatement prQuery = null;
		String query = "select accBalance from wallettracker.accounts where accName = ?";
		
		prQuery = this.con.getConnection().prepareStatement(query);
		prQuery.setString(1, accName);
		ResultSet rs = prQuery.executeQuery();
		
//		while (rs.next()){
//			System.out.println(rs.getDouble("accBalance"));
//		}
		
		return balance = rs.getDouble("accBalance");
		
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
	
	/**
	 * Returns an ArrayList with all the expenses for the given accName.
	 * @param accName
	 * @return
	 * @throws SQLException
	 */
	
	public ArrayList<Double> getUserExpenses(String accName) throws SQLException {
		
		ArrayList<Double> values = new ArrayList<Double>();
		
		PreparedStatement prStatement = null;
		String prQuery = "select amount from wallettracker.expenses_history where name = ?";
		
		prStatement = this.con.getConnection().prepareStatement(prQuery);
		prStatement.setString(1, accName);
		ResultSet rs = prStatement.executeQuery();
		
		while (rs.next()){
			
			values.add(rs.getDouble("amount"));
		}
		
		return values;
		
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
		rsHandler.getCurrentAccounts();
		rsHandler.getCurrentBalanceAll();
		System.out.println(rsHandler.getLastExpenseMonth("nako"));
		//rsHandler.getUserExpensesByPeriod("nako", OVERVIEW_PERIOD.SIX_MONTHS);
	}

}
