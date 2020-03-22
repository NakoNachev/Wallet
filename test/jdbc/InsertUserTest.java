package jdbc;

import java.sql.SQLException;

/**
 * Tester for inserting new users. It has to check whether a specific user is already 
 * inside the database and if yes, ask for a new user.
 * 
 *
 */

public class InsertUserTest {

	public static void main(String[] args) {
		
		DBHandling dbhandler = new DBHandling();
		
		//TODO Write the whole testing.
		
		try{
			dbhandler.insertNewUser("nako", 200, 0);
		}
		catch (SQLException e) {
			
			if (e.getErrorCode() == 23) {
				System.out.println("User already exists");
			}
			
			e.printStackTrace();
			System.out.println(e.getSQLState());
			System.out.println(e.getMessage());
		}

	}

}
