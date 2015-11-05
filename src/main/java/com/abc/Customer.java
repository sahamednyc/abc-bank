package com.abc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


import java.math.BigDecimal;

public class Customer {
    private String name;
    private List<Account> accounts;
    
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
    	readWriteLock.writeLock().lock();
    	accounts.add(account);
    	readWriteLock.writeLock().unlock();
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public BigDecimal totalInterestEarned() {
    	readWriteLock.readLock().lock();
    	BigDecimal total = new BigDecimal(0);
        for (Account a : accounts)
            total = total.add(a.interestEarned());
    	readWriteLock.readLock().unlock();
        return total;
    
        
    }

    public String getStatement() {
        StringBuffer statement = new StringBuffer();
        statement.append(HelperUtil.STATEMENT_FOR);
        statement.append(name);
        statement.append(HelperUtil.NEW_LINE);
        readWriteLock.readLock().lock();
        BigDecimal total = new BigDecimal(0.0);
        for (Account a : accounts) {
            statement.append(HelperUtil.NEW_LINE);
            statement.append(a.statementForAccount());
            statement.append(HelperUtil.NEW_LINE);
            total = total.add(a.sumTransactions());
        }
        
        statement.append(HelperUtil.NEW_LINE);
        statement.append(HelperUtil.TOTAL_IN_ALL_ACCOUNTS);
        statement.append(HelperUtil.toDollars(total));
    	readWriteLock.readLock().unlock();

        return statement.toString();
    }
    
    public boolean transfer(Account srcAccount, Account destAccount, BigDecimal amount){
    	boolean success = false;
    	if (amount.signum() < 0 || amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException(HelperUtil.AMOUNT_MUST_BE_GREATER_THAN_ZERO);
        } else {
        	try{
        		readWriteLock.writeLock().lock();
        		if(srcAccount.withdraw(amount))
        			success = destAccount.deposit(amount);
        		if(!success){
        			srcAccount.deposit(amount);//Roll back
        		}
        	}finally{
        		readWriteLock.writeLock().unlock();
        	}
        }
    	return success;
    }

 }
