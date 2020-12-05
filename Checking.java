public class Checking extends Account {
    
    /**
     * Create a new account
     * @param owner - the user that owns this account
     * @param currency - the type of the currency stored in this account
     * @param balance - the current amount of that currency in this account
     * @param feeRate - The percentage removed in a fee from each transaction
     */
    public Checking(User owner, Currency currency, double balance, double feeRate) {
        super(owner, currency, balance, feeRate);        
    }

    /**
     * This should be called any time a transaction takes place between this
     * account and any other account (since it is a checking account)
     * @param transactionAmount - amount of money being exchanged
     */
    public double newTransaction(double transactionAmount) {
        return super.newFeeTransaction(transactionAmount);
    }

    /**
     * This function transfers money from this account to another account
     * and then charges a fee as this is a checking account
     * @param otherAccount - the account you are transferring money to
     * @param amount - the amount you are transferring
     * @return the resulting balance of this account
     */
    public double transferMoneyToAccount(Account otherAccount, double amount) {
        otherAccount.addToBalance(amount, this.currency);
        this.subtractFromBalance(amount);
        this.newTransaction(amount);

        return this.balance;
    }
}
