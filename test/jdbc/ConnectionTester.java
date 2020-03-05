package jdbc;

import java.sql.SQLException;

public class ConnectionTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DBConnector con = new DBConnector();
		try {
			System.out.println(con.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
