
import java.math.BigDecimal;

public class MaxiSavingsAccount extends Account{

	@Override
	public int getAccountType() {
		return Account.MAXI_SAVINGS;
	}

	@Override
	public BigDecimal interestEarned() {
		BigDecimal amount = sumTransactions();
        if (amount.compareTo(HelperUtil.oneThousand) <= 0)
            return amount.multiply(HelperUtil.twoPerCent);
        if ( amount.compareTo(HelperUtil.twoThousand) <= 0)
        	return ((amount.subtract(HelperUtil.oneThousand)).multiply(HelperUtil.fivePerCent)).add(HelperUtil.twenty);
        return ((amount.subtract(HelperUtil.oneThousand)).multiply(HelperUtil.tenPerCent)).add(HelperUtil.seventy);

	}

	@Override
	public String statementForAccount() {
		StringBuffer s = new StringBuffer("");
        s.append(HelperUtil.MAXI_SAVINGS_ACCOUNT);
        s.append(HelperUtil.NEW_LINE);
        s.append(transactionDetails());
        return s.toString();
	}

}
