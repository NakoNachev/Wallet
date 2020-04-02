package starter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import jdbc.DBHandling;
import jdbc.DBResultSetHandler;
import jdbc.EXPENSE_TYPES;
import jdbc.OVERVIEW_PERIOD;


/**
 * Class for starting the app as a console.
 * 
 *
 */
public class ConsoleLogic {

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
			mainMenuInputController();
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
		dbhandler.insertNewUser(desiredAccount);
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
		System.out.println("2.4 Display all expenses till now.");
		System.out.println("2.5 Add new expense.");
		System.out.println("2.6 Add new income.");
		System.out.println("2.7 Exit user.");
		System.out.println("2.8 Change balance.");
		System.out.println("2.9 Delete all expenses so far");
		
	}
	
	public void subMenuInputController(String accName) throws SQLException, InterruptedException {
		
		int userChoice;
		
		do {
			try {
				System.out.println();
				System.out.print("Choose between 1 .. 8: ");
				String inputValue = input.next();
				userChoice = Integer.parseInt(inputValue);
				
				
					while(userChoice != 1 & userChoice != 2 & userChoice != 3 & userChoice != 4 & userChoice != 5 
							& userChoice != 6 & userChoice != 7 & userChoice != 8){
						System.out.println();
						System.out.println("Should be between 1 .. 8!");
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
			subMenuOption2(accName);
			break;
		case 3:
			subMenuOption3(accName);
			break;
		case 4:
			subMenuOption4(accName);;
			break;
		case 5:
			subMenuOption5(accName,true);
			break;
		case 6:
			subMenuOption6(accName);
			break;
		case 7:
			displayMainMenu();
			mainMenuInputController();
			break;
		case 8:
			subMenuOption8(accName);
			break;

			
		}
	}
	
	public void subMenuOption1(String accName) throws SQLException, InterruptedException {
		
		System.out.println();
		System.out.println("Current balance is: "+ rshandler.getCurrentBalance(accName));
		displaySubMenu();
		subMenuInputController(accName);
		System.out.println();
	}
	
	public void subMenuOption2(String accName) throws SQLException, InterruptedException {
		
		System.out.println();
		System.out.println("Please choose the period you want to display: ");
		System.out.println("2.2.1 Three months back. "
				+ "2.2.2 Six months back."
				+ "2.2.3 One year back."
				+ "2.2.4 Five years back.");
		int userChoice;
		do {
			try {
				System.out.println();
				System.out.print("Choose between 1 .. 4: ");
				String inputValue = input.next();
				userChoice = Integer.parseInt(inputValue);
				
				
					while(userChoice != 1 & userChoice != 2 & userChoice != 3 & userChoice != 4){
						System.out.println();
						System.out.println("Should be between 1 .. 4!");
						inputValue = input.next();
						userChoice = Integer.parseInt(inputValue);
						break;
					}
				break;
				
			} catch (Exception e){
				System.out.println("Value not accepted, try again: ");
			}
		}while (true);
		
		switch(userChoice){
			
		case 1:
			rshandler.displayPeriodData(accName, OVERVIEW_PERIOD.THREE_MONTHS);
			displaySubMenu();
			subMenuInputController(accName);
			break;
		case 2:
			rshandler.displayPeriodData(accName, OVERVIEW_PERIOD.SIX_MONTHS);
			displaySubMenu();
			subMenuInputController(accName);
			break;
		case 3:
			rshandler.displayPeriodData(accName, OVERVIEW_PERIOD.ONE_YEAR);
			displaySubMenu();
			subMenuInputController(accName);
			break;
		case 4:
			rshandler.displayPeriodData(accName, OVERVIEW_PERIOD.FIVE_YEARS);
			displaySubMenu();
			subMenuInputController(accName);
			break;
			
		}
		
	}
	
	public void subMenuOption3(String accName) throws SQLException, InterruptedException {
		
		System.out.println("Current balance is: "+ rshandler.getCurrentDebt(accName));
		displaySubMenu();
		subMenuInputController(accName);
	}


	public void subMenuOption4(String accName) throws SQLException, InterruptedException {
		
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
	@SuppressWarnings("deprecation")
	public void subMenuOption5(String accName, boolean isExpense) throws SQLException, InterruptedException {
	
		String name = accName;
		double amount;
		int day;
		int month;
		int year;
		java.util.Date date;
		boolean checkIfExpenseInsert = isExpense;
		EXPENSE_TYPES type = null;
		int typeChoiceID;
		
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
		
		System.out.println("Please choose of the following expense types:");
		type.printExpenseTypes();
		typeChoiceID = input.nextInt();
		
		date = new Date(month + "/" + day + "/" + year);
		
		if (checkIfExpenseInsert) {
			dbhandler.insertNewExpense(name, amount, date,typeChoiceID);
			displaySubMenu();
			subMenuInputController(accName);
		}
		else {
			dbhandler.addNewIncome(accName, amount, date);
			displaySubMenu();
			subMenuInputController(accName);
		}
	}

	public void subMenuOption6(String accName) throws SQLException, InterruptedException {
	
		subMenuOption5(accName,false);
	}
		
	public void subMenuOption8(String accName) throws SQLException, InterruptedException {
		
		System.out.println();
		System.out.println("Please enter the desired balance: ");
		double amount;
		
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

		dbhandler.setBalance(accName, amount);
		System.out.println("Balance changes successfully");
		
	}
	
	
	
		

	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub
		ConsoleLogic starter = new ConsoleLogic();
		starter.startApp();
		
	}

}
