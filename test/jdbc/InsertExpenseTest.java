package jdbc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class InsertExpenseTest extends DBHandling {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		
		// simple insert which takes current date
		
		DBHandling handler = new DBHandling();
		
		java.util.Date date3 = new Date("10/28/2020");
		java.sql.Date date4 = new java.sql.Date(date3.getTime());
		
		java.sql.Date date = new java.sql.Date(2000,3,10);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		

		//java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		handler.insertNewExpense("nako", 200, date3 );

	}

}
