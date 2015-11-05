package com.abc;

import java.math.BigDecimal;

public class CheckingAccount extends Account{

	@Override
	public int getAccountType() {
		return Account.CHECKING;
	}

	@Override
	public BigDecimal interestEarned() {
		BigDecimal amount = sumTransactions();
		return amount.multiply(HelperUtil.pointOnePerCent);
	}

	@Override
	public String statementForAccount() {
		StringBuffer s = new StringBuffer("");
        s.append(HelperUtil.CHECKING_ACCOUNT);
        s.append(HelperUtil.NEW_LINE);
        s.append(transactionDetails());
        return s.toString();
		
	}

}
