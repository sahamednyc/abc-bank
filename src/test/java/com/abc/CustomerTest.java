package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

public class CustomerTest {
	private static final double DOUBLE_DELTA = 1e-15;

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingsAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(new BigDecimal(100.0));
        savingsAccount.deposit(new BigDecimal(4000.0));
        savingsAccount.withdraw(new BigDecimal(200.0));

        assertEquals("Statement for Henry\n" +
                "\n" +
                "Checking Account\n" +
                "  deposit $100.00\n" +
                "Total $100.00\n" +
                "\n" +
                "Savings Account\n" +
                "  deposit $4,000.00\n" +
                "  withdrawal $200.00\n" +
                "Total $3,800.00\n" +
                "\n" +
                "Total In All Accounts $3,900.00", henry.getStatement());
    }

    @Test
    public void testOneAccount(){
        Customer oscar = new Customer("Oscar").openAccount(new SavingsAccount());
        assertEquals(1, oscar.getNumberOfAccounts());
    }

    @Test
    public void testTwoAccount(){
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        assertEquals(2, oscar.getNumberOfAccounts());
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingsAccount());
        oscar.openAccount(new CheckingAccount());
        oscar.openAccount(new MaxiSavingsAccount());
        assertEquals(3, oscar.getNumberOfAccounts());
    }
    
    @Test
    public void testTransfer(){
    	Account checkingAccount = new CheckingAccount();
    	Account savingsAccount = new SavingsAccount();
    	
    	Customer shameem = new Customer("Shameem").openAccount(checkingAccount);
    	shameem.openAccount(savingsAccount);
    	
    	savingsAccount.deposit(HelperUtil.oneThousand);//1000 in savings
    	BigDecimal fiveHundrad = new BigDecimal(500);
    	shameem.transfer(savingsAccount, checkingAccount, fiveHundrad);
    	assertEquals(fiveHundrad.doubleValue(),savingsAccount.sumTransactions().doubleValue(),DOUBLE_DELTA);
    }
}
