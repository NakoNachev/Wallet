package users;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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
	
	/**
	 * Main starter function, responsible for calling all the others necessary
	 * @throws SQLException
	 * @throws InterruptedException 
	 */
	public void startApp() throws SQLException, InterruptedException{
		displayMainMenu();
		mainMenuInputController();
		//displayGreeting();
	}
	
	public void displayMainMenu(){
		
		System.out.println();
		System.out.println("Welcome to wallet!");
		System.out.println();
		System.out.println("1.Register new account.");
		System.out.println("2.Enter into existing account.");
	}
	
	public void mainMenuInputController() throws SQLException, InterruptedException {
		
		int userChoice;
		
		do {
			try {
				System.out.println();
				System.out.print("Choose between 1 or 2: ");
				String inputValue = input.next();
				userChoice = Integer.parseInt(inputValue);
				
				
					while(userChoice != 1 & userChoice != 2){
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
			break;
		case 2:
			String acc = mainMenuOption2();
			displaySubMenu();
			subMenuInputController(acc);
			break;
			
		}
		
	}
	
	public void displayGreeting(String accName) {
		
		System.out.println();
		System.out.println("Welcome back " + accName + "!");
		System.out.println("Which one of the following options would you like to use: ?");	
	}
	
	/**
	 * First option of the main menu, allows the registration of a new user to the db.
	 * @throws SQLException
	 */
	public void mainMenuOption1() throws SQLException {
		String desiredAccount;
		System.out.println();
		System.out.print("Please choose a name for the account to register:");
		desiredAccount = input.next();
		boolean accountExists = rshandler.checkAccountExistance(desiredAccount);
		
		while(accountExists){
			
			System.out.println();
			System.out.print("Account taken, please choose another name: ");
			desiredAccount = input.next();
			accountExists = rshandler.checkAccountExistance(desiredAccount);
		}
		
		System.out.println();
		System.out.print("Account "+ desiredAccount + " was registered successfully!");
	}
	
	/**
	 * Second option of the main menu for logging into a account.
	 * @throws SQLException
	 */
	
	public String mainMenuOption2() throws SQLException {
		
		String desiredAccount;
		
		System.out.println("Please enter the desired account name:");
		desiredAccount = input.next();
		boolean accountExists = rshandler.checkAccountExistance(desiredAccount);
		
		while(!accountExists){
			
			System.out.println();
			System.out.print("Couldnt find an account with that name, please try again: ");
			desiredAccount = input.next();
			accountExists = rshandler.checkAccountExistance(desiredAccount);
			
		}
		if (accountExists == true) {
			System.out.println("Welcome " + desiredAccount);
			// display submenu
		}
		
		return desiredAccount;
	
	}
	
	/**
	 * Calls the submenu after entering in the username.
	 */
	public void displaySubMenu(){
		
		
		System.out.println("2.1 Check balance.");
		System.out.println("2.2 Check overview period.");
		System.out.println("2.3 Check debt.");
		System.out.println("2.4 Display expenses for a period.");
		System.out.println("2.5 Display all expenses till now.");
		System.out.println("2.6 Add new expense.");
		System.out.println("2.7 Add new income.");
		
	}
	
	public void subMenuInputController(String accName) throws SQLException, InterruptedException {
		
		int userChoice;
		
		do {
			try {
				System.out.println();
				System.out.print("Choose between 1 .. 7: ");
				String inputValue = input.next();
				userChoice = Integer.parseInt(inputValue);
				
				
					while(userChoice != 1 & userChoice != 2 & userChoice != 3 & userChoice != 4 & userChoice != 5 
							& userChoice != 6 & userChoice != 7){
						System.out.println();
						System.out.println("Should be between 1 .. 7!");
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
			subMenuOption1(accName);
			break;
		case 2:
			subMenuOption2();
			break;
		case 3:
			subMenuOption3(accName);
			break;
		case 4:
			subMenuOption4();
			break;
		case 5:
			subMenuOption5(accName);
			break;
		case 6:
			subMenuOption6(accName,true);
			break;
		case 7:
			subMenuOption7(accName);
			break;
			
		}
	}
	
	public void subMenuOption1(String accName) throws SQLException, InterruptedException {
		
		System.out.println("Current balance is: "+ rshandler.getCurrentBalance(accName));
		displaySubMenu();
		subMenuInputController(accName);
	}
	
	public void subMenuOption2() {
		
	}
	
	public void subMenuOption3(String accName) throws SQLException, InterruptedException {
		
		System.out.println("Current balance is: "+ rshandler.getCurrentDebt(accName));
		displaySubMenu();
		subMenuInputController(accName);
	}

	public void subMenuOption4() {
	
		
	}

	public void subMenuOption5(String accName) throws SQLException, InterruptedException {
		
		System.out.println();
		ArrayList<Double> expense = rshandler.getUserExpenses(accName);
		ArrayList<java.sql.Date> expenseDates = rshandler.getUserExpenseDates(accName);
		
		
		
		for (int index = 0; index < expense.size(); index++){
			System.out.println(accName + " | "+ expense.get(index) + " | " + expenseDates.get(index));
		}
		
		Thread.sleep(1000);
		
		System.out.println();
		
		displaySubMenu();
		subMenuInputController(accName);
		
		;
	}
	
	/**
	 * Add a new expense to the database for a given user. If the second parameter is true, adds expense,if false,
	 * adds an income.
	 * @param accName
	 * @param expense
	 * @throws SQLException
	 * @throws InterruptedException 
	 */
	public void subMenuOption6(String accName, boolean isExpense) throws SQLException, InterruptedException {
	
		String name = accName;
		double amount;
		int day;
		int month;
		int year;
		java.util.Date date;
		boolean checkIfExpenseInsert = isExpense;
		
		do {
			try {
				System.out.println();
				System.out.print("Select the amount you want to insert: ");
				String inputValue = input.next();
				amount = Double.parseDouble(inputValue);
				break;
				
			} catch (Exception e){
				System.out.println("Please enter a number: ");
			}
		}while (true);
		
		System.out.print("Select day: ");
		day = input.nextInt();
		System.out.print("Select month: ");
		month = input.nextInt();
		System.out.print("Select year: ");
		year = input.nextInt();
		
		date = new Date(month + "/" + day + "/" + year);
		
		if (checkIfExpenseInsert) {
			dbhandler.insertNewExpense(name, amount, date);
			displaySubMenu();
			subMenuInputController(accName);
		}
		else {
			dbhandler.addNewIncome(accName, amount, date);
			displaySubMenu();
			subMenuInputController(accName);
		}
	}

	public void subMenuOption7(String accName) throws SQLException, InterruptedException {
	
		subMenuOption6(accName,false);
		
}

	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub
		ConsoleMainStart starter = new ConsoleMainStart();
		starter.startApp();
		
	}

}
