package calc;

import jdbc.DBResultSetHandler;
import jdbc.OVERVIEW_PERIOD;

/** Class for implementation of different statistical calculations
 * based on the data in the DB.
 * 
 * 
 *
 */

public class Statistics {

	
	public double getAverageExpensesByMonth() {
		
		double average = 0.0;
		
		
		
		
		
		return average;
		
	}
	
	/**
	 * Displays the balance movements for a specific account on given period.Period is 
	 * taken from the enumerator values of OVERVIEW_PERIOD. Each balance value displayed is
	 * a result of a negative/positive transaction to the account.
	 * @param accName
	 * @param period
	 */
	
	public void getBalancePeriod(String accName, OVERVIEW_PERIOD period) {
		
		DBResultSetHandler handler = new DBResultSetHandler();
		
		switch(period) {
		
		case THREE_MONTHS: 
		case SIX_MONTHS:
		case ONE_YEAR:
		case FIVE_YEARS:
		case MAX:
		}
	}
}
