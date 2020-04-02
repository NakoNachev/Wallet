/**
 * 
 */
package jdbc;

import java.util.HashMap;
import java.util.Map;

/**
 * Enumerator to be used in database for differentiating expense types in expense history
 *
 */
public enum EXPENSE_TYPES {

	RENT,
	SPORT,
	TELEPHONE,
	TV_RADIO,
	GOING_OUT,
	EATING_OUT,
	SHOPPING_FOOD,
	SHOPPING_OTHER,		// more sub categories
	UNI_TAXES,
	GIFTS,
	WEB_SUBSCRIBTIONS,
	TRANSPORT,
	TAXES,				// different types
	MONTLY_PAYMENTS,
	INVESTMENTS,
	WELLNESS;
	
	
	public static Map<Integer,EXPENSE_TYPES> returnExpensesMapping(){
		Map<Integer,EXPENSE_TYPES> map = new HashMap<Integer,EXPENSE_TYPES>();
		
		map.put(1, EXPENSE_TYPES.RENT);
		map.put(2, EXPENSE_TYPES.SPORT);
		map.put(3, EXPENSE_TYPES.TELEPHONE);
		map.put(4, EXPENSE_TYPES.TV_RADIO);
		map.put(5, EXPENSE_TYPES.GOING_OUT);
		map.put(6, EXPENSE_TYPES.EATING_OUT);
		map.put(7, EXPENSE_TYPES.SHOPPING_FOOD);
		map.put(8, EXPENSE_TYPES.SHOPPING_OTHER);
		map.put(9, EXPENSE_TYPES.UNI_TAXES);
		map.put(10, EXPENSE_TYPES.WEB_SUBSCRIBTIONS);
		map.put(11, EXPENSE_TYPES.TRANSPORT);
		map.put(12, EXPENSE_TYPES.TAXES);
		map.put(13, EXPENSE_TYPES.MONTLY_PAYMENTS);
		map.put(14, EXPENSE_TYPES.INVESTMENTS);
		map.put(15, EXPENSE_TYPES.WELLNESS);
		
		return map;
		
	}
	
	public static void printExpenseTypes(){
		
		System.out.println();
		Map <Integer,EXPENSE_TYPES> map = returnExpensesMapping();
		for (Map.Entry<Integer,EXPENSE_TYPES> entry : map.entrySet()){
			
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}
		System.out.println();
	}
	
}

