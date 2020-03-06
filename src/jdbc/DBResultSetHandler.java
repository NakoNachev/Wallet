package jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/** For handling different select statements and corresponding results to be 
 * manipulated later on.
 *
 */

public class DBResultSetHandler extends DBConnector {
	
	DBConnector con = new DBConnector();
	
	public void getExpenses(String name, int month, int year ) {
		
		String query = "select * from wallettracker.expense_history where Month(issueDate) = "
				+ "? and accName =?;" ;
		PreparedStatement prQuery = null;
		
		
		try {
			prQuery = this.con.getConnection().prepareStatement(query);
			prQuery.setInt(1, month);
			prQuery.setString(2, name);
			
			ResultSet rs = prQuery.executeQuery();
			
			while (rs.next()) {
				System.out.println(rs.getDouble("ammount"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
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
	 */
	
	public void getCurrentBalance(String accName) throws SQLException {
		
		PreparedStatement prQuery = null;
		String query = "select accBalance from wallettracker.accounts where accName = ?";
		
		prQuery = this.con.getConnection().prepareStatement(query);
		prQuery.setString(1, accName);
		ResultSet rs = prQuery.executeQuery();
		
		while (rs.next()){
			System.out.println(rs.getDouble("accBalance"));
		}
		
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
	
	public static void main(String args[]) throws SQLException {
		
		DBResultSetHandler rsHandler = new DBResultSetHandler();
		//rsHandler.getExpenses("test", 3, 2011);
		rsHandler.getCurrentAccounts();
		rsHandler.getCurrentBalance("nako");
		rsHandler.getCurrentBalanceAll();
	}

}
