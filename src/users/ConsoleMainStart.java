package users;

import java.sql.SQLException;
import java.util.Scanner;

import jdbc.DBHandling;
import jdbc.DBResultSetHandler;


/**
 * Class for starting the app as a console.
 * 
 *
 */
public class ConsoleMainStart {

	Scanner input = new Scanner(System.in);
	DBHandling dbhandler = new DBHandling();
	DBResultSetHandler rshandler = new DBResultSetHandler();
	
	
	public void startApp() throws SQLException{
		displayMenu();
	}
	
	public void displayMenu() throws SQLException {
		
		String userChoice ;
		
		System.out.println("Welcome to wallet!");
		System.out.println();
		System.out.println("1.Register new account.");
		System.out.println("2.Enter into existing account.");
		System.out.print("Please enter the desired option:");
		
		userChoice = input.nextLine();
		
		switch(userChoice) {
		
		case "1": 
			
		case "2":
			
			menuOption2();
		}
	}
	
	public void menuOption1() {
		
		
	}
	
	public void menuOption2() throws SQLException {
		
		String desiredAccount;
		boolean accountExists = true;
		System.out.println("Please enter the desired account name:");
		desiredAccount = input.nextLine();
		
		if (accountExists == rshandler.checkAccountExistance(desiredAccount)) {
			System.out.println("Welcome," + desiredAccount);
		}
		else {
			System.out.println("Account doesnt exist, please try again!");
		}
		
	}
	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConsoleMainStart starter = new ConsoleMainStart();
		starter.startApp();
	}

}
