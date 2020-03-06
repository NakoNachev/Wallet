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
	
	public static void main(String args[]) {
		
		DBResultSetHandler rsHandler = new DBResultSetHandler();
		rsHandler.getExpenses("test", 3, 2011);
	}

}
