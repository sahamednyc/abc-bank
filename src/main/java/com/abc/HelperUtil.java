package com.abc;

import java.math.BigDecimal;

public class HelperUtil {
	
	public static final String STATEMENT_FOR = "Statement for ";
	public static final String NEW_LINE = "\n";
	public static final String TOTAL_IN_ALL_ACCOUNTS = "Total In All Accounts ";
	public static final String CHECKING_ACCOUNT = "Checking Account";
	public static final String SAVINGS_ACCOUNT = "Savings Account";
	public static final String MAXI_SAVINGS_ACCOUNT = "Maxi Savings Account";
	public static final String WITHDRAWAL = "withdrawal";
	public static final String DEPOSIT = "deposit";
	public static final String TOTAL = "Total ";
	public static final String AMOUNT_MUST_BE_GREATER_THAN_ZERO= "amount must be greater than zero";
	public static final String CUSTOMER_SUMMARY = "Customer Summary";
	
	public static final BigDecimal twenty = new BigDecimal(20);
	public static final BigDecimal seventy = new BigDecimal(70);
	public static final BigDecimal oneThousand = new BigDecimal(1000);
	public static final BigDecimal twoThousand = new BigDecimal(2000);
	public static final BigDecimal pointOnePerCent = new BigDecimal(0.001);
	public static final BigDecimal pointTwoPerCent = new BigDecimal(0.002);
	
	public static final BigDecimal twoPerCent = new BigDecimal(0.02);
	public static final BigDecimal fivePerCent = new BigDecimal(0.05);
	public static final BigDecimal tenPerCent = new BigDecimal(0.10);
	
	
	
	//Make sure correct plural of word is created based on the number passed in:
    //If number passed in is 1 just return the word otherwise add an 's' at the end
    public static String format(int number, String word) {
        return number + " " + (number == 1 ? word : word + "s");
    }
    
    public static String toDollars(BigDecimal d){
        return String.format("$%,.2f", d.abs());
    }
	

}
