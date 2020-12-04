public class Checking extends Account {
    private double fee;
    
    /**
     * Create a new account
     * @param owner - the user that owns this account
     * @param currency - the type of the currency stored in this account
     * @param balance - the current amount of that currency in this account
     * @param feeRate - The percentage removed in a fee from each transaction
     */
    public Checking(User owner, Currency currency, double balance, double feeRate) {
        super(owner, currency, balance);
        this.fee = feeRate; 
    }

    public double getFee() {
        return this.fee;
    }

    public void setFee(double newFeeRate) {
        this.fee = newFeeRate;
    }

    /**
     * When a transaction is done with this account, remove the correct fee from this account
     * @param transactionAmount - The amount of the transaction (in the correct currency)
     * @return
     */
    public double newTransaction(double transactionAmount) {
        this.subtractFromBalance(transactionAmount * this.fee);

        return this.balance;
    }

    /**
     * When a transaction is done with this account, remove the correct fee from this account
     * @param transactionAmount - the amount of the transaction in the given currency
     * @param transactionCurrency - the currency that the transaction amount is given in
     * @return
     */
    public double newTransaction(double transactionAmount, Currency transactionCurrency) {
        transactionAmount = transactionCurrency.convertFromCurrencyToOther(transactionAmount, this.currency);
        this.subtractFromBalance(transactionAmount * this.fee);

        return this.balance;
    }

    /**
     * Withdraws the given amount and gives it to the owner of the account
     * @param amountToWithdraw - amount to withdraw (in the current currency type)
     * @return the new balance of the account
     */
    public double withdrawFromAccount(double amountToWithdraw) {
        /* Do the usual withdrawal */
        super.withdrawFromAccount(amountToWithdraw);
        
        /* Then subtract the fee from the account */
        this.newTransaction(amountToWithdraw);

        return this.balance;
    }
}
