package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class Account {
    public static final int CHECKING = 0;
    public static final int SAVINGS = 1;
    public static final int MAXI_SAVINGS = 2;

    public List<Transaction> transactions = new ArrayList<Transaction>();
    
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    
    public abstract int getAccountType();
    public abstract BigDecimal interestEarned();
    public abstract String statementForAccount();
    
    
    public boolean deposit(BigDecimal amount) {
    	boolean success = false;
        if (amount.signum() < 0 || amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException(HelperUtil.AMOUNT_MUST_BE_GREATER_THAN_ZERO);
        } else {
        	//Deposit Transaction need to be exclusive with Withdraw and sum Transactions calculation.
        	readWriteLock.writeLock().lock();
        	success = transactions.add(new Transaction(amount));
        	readWriteLock.writeLock().unlock();
        }
        return success;
        
    }
    public boolean withdraw(BigDecimal amount) {
    	boolean success = false;
        if (amount.signum() < 0 || amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException(HelperUtil.AMOUNT_MUST_BE_GREATER_THAN_ZERO);
        } else {
        	//Withdraw Transaction need to be exclusive with Deposit and sum Transactions calculation.
        	readWriteLock.writeLock().lock();
        	success = transactions.add(new Transaction(amount.negate()));
        	readWriteLock.writeLock().unlock();
        }
        return success;
    }
    public BigDecimal sumTransactions() {
    	readWriteLock.readLock().lock();
        BigDecimal amount = BigDecimal.ZERO;
        for (Transaction t: transactions){
        	amount = amount.add(t.amount);
        }   
        readWriteLock.readLock().unlock();
        return amount;
    }
    
    public String transactionDetails(){
    	StringBuffer s = new StringBuffer();
       	readWriteLock.readLock().lock();
        BigDecimal total = new BigDecimal(0.0);
        for (Transaction t : transactions) {
        	s.append("  ");
        	s.append(t.amount.signum() < 0 ? HelperUtil.WITHDRAWAL : HelperUtil.DEPOSIT);
        	s.append(" ");
        	s.append(HelperUtil.toDollars(t.amount));
        	s.append(HelperUtil.NEW_LINE);
        	total = total.add(t.amount);
        }
        s.append(HelperUtil.TOTAL);
        s.append(HelperUtil.toDollars(total));
        readWriteLock.readLock().unlock();

        return s.toString();
    }
    
}

