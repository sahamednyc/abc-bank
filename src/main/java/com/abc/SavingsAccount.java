package com.abc;

import java.math.BigDecimal;

public class SavingsAccount extends Account{

	@Override
	public int getAccountType() {
		return Account.SAVINGS;
	}

	@Override
	public BigDecimal interestEarned() {
		BigDecimal amount = sumTransactions();
		if (amount.compareTo(HelperUtil.oneThousand) <= 0)
            return amount.multiply(HelperUtil.pointOnePerCent);
        else
            return (amount.subtract(HelperUtil.oneThousand).multiply(HelperUtil.pointTwoPerCent)).add(BigDecimal.ONE);
	}

	@Override
	public String statementForAccount() {
		StringBuffer s = new StringBuffer("");
        s.append(HelperUtil.SAVINGS_ACCOUNT);
        s.append(HelperUtil.NEW_LINE);
        s.append(transactionDetails());
        return s.toString();
	}

}
