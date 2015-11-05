package com.abc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Bank {
    private List<Customer> customers;

    public Bank() {
        customers = new ArrayList<Customer>();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public String customerSummary() {
        StringBuffer summary = new StringBuffer(HelperUtil.CUSTOMER_SUMMARY);
        for (Customer c : customers){
        	summary.append(HelperUtil.NEW_LINE);
        	summary.append(c.getName());
        	summary.append(" (");
        	summary.append(HelperUtil.format(c.getNumberOfAccounts(), "account"));
        	summary.append(")");
        }	
        return summary.toString();
    }

    

    public BigDecimal totalInterestPaid() {
    	BigDecimal total = BigDecimal.ZERO;
        for(Customer c: customers)
            total = total.add(c.totalInterestEarned());
        return total;
    }
    
 
//What is the purpose?
    /*
    public String getFirstCustomer() {
        try {
            customers = null;
            return customers.get(0).getName();
        } catch (Exception e){
            e.printStackTrace();
            return "Error";
        }
    }
    */
}
