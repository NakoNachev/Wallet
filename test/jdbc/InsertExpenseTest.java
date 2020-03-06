package jdbc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class InsertExpenseTest extends DBHandling {

	public static void main(String[] args) {
		
		
		// simple insert which takes current date
		
		DBHandling handler = new DBHandling();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

		handler.insertNewExpense("nako", 200, date );

	}

}
