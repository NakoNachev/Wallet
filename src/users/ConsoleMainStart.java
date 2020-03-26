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
	
	public enum OPTIONS {
		
		VIEW_BALANCE,
		VIEW_BALANCE_PERIOD,
		VIEW_CREDIT,
		VIEW_EXPENSES_PERIOD,
		VIEW_EXPENSES,
		ADD_EXPENSE,
		ADD_INCOME,
		LOG_ACCOUNT,
		REG_ACCOUNT
	}
	
	
	Scanner input = new Scanner(System.in);
	DBHandling dbhandler = new DBHandling();
	DBResultSetHandler rshandler = new DBResultSetHandler();
	
	
	public void startApp() throws SQLException{
		displayMainMenu();
		mainMenuInput();
		//displayGreeting();
	}
	
	public void displayMainMenu() throws SQLException {

		System.out.println("Welcome to wallet!");
		System.out.println();
		System.out.println("1.Register new account.");
		System.out.println("2.Enter into existing account.");
		System.out.print("Please enter the desired option: ");
	}
	
	public void mainMenuInput() throws SQLException {
		
		int userChoice;
		
		do {
			try {
				System.out.println();
				System.out.print("Choose between 1 or 2: ");
				String inputValue = input.next();
				userChoice = Integer.parseInt(inputValue);
					while(userChoice != 1 || userChoice != 2){
						System.out.println();
						System.out.println("Should be 1 or 2!");
						inputValue = input.next();
						userChoice = Integer.parseInt(inputValue);
						break;
					}
				break;
				
			} catch (Exception e){
				System.out.println("Value not accepted, try again: ");
			}
		}while (true);
		
		
		switch (userChoice){
		
		case 1:
			mainMenuOption1();
		case 2:
			mainMenuOption2();
			
		}
		
	}
	
	public void displayGreeting(String accName) {
		
		System.out.println();
		System.out.println("Welcome back " + accName + "!");
		System.out.println("Which one of the following options would you like to use: ?");	
	}
	
	public void mainMenuOption1() {
		
		
	}
	
	/**
	 * Second option of the main menu for logging into a account.
	 * @throws SQLException
	 */
	
	public void mainMenuOption2() throws SQLException {
		
		String desiredAccount;
		
		System.out.println("Please enter the desired account name:");
		desiredAccount = input.next();
		boolean accountExists = rshandler.checkAccountExistance(desiredAccount);
		
		
		if (accountExists == true) {
			System.out.println("Welcome," + desiredAccount);
			// display submenu
		}
		else {
			System.out.println("Account doesnt exist, please try again!");
		}
		
	}
	
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		ConsoleMainStart starter = new ConsoleMainStart();
		starter.mainMenuOption2();
	}

}
