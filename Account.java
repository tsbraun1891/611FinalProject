
public abstract class Account extends BalanceHandler {
    protected User owner;
    protected boolean isOpen;
    protected double fee;
    
    protected Transaction transaction;

    /**
     * Create a new account
     * @param owner - the user that owns this account
     * @param currency - the type of the currency stored in this account
     * @param balance - the current amount of that currency in this account
     */
    public Account(User owner, Currency currency, double balance, double feeRate) {
        super(balance, currency);

        this.owner = owner;
        this.isOpen = true;
        this.fee = feeRate;
    }

    public User getOwner() {
        return owner;
    }

    /**
     * Change the owner of this account to a different user
     * @param newOwner - the new user to set as the owner of this account
     */
    public void setOwner(User newOwner) {
        this.owner = newOwner;
    }

    public double getFeeRate() {
        return this.fee;
    }

    public void setFeeRate(double newFeeRate) {
        this.fee = newFeeRate;
    }

    public boolean closeAccount() {
        if(this.isOpen) {
            /* Scale the fee based on the amount of money in the account being open */
            this.newFeeTransaction(this.balance);
            this.isOpen = false;
        }
        
        return this.isOpen;
    }

    public boolean openAccount() {
        if(!this.isOpen) {
            /* Scale the fee based on the amount of money in the account being open */
            this.newFeeTransaction(this.balance);
            this.isOpen = true;
        }

        return this.isOpen;
    }

    public boolean checkClosed() {
        return this.isOpen;
    }

    /**
     * Gives you how much the fee will cost to perform a chargeable action 
     * (like withdrawal) with the given amount
     * @param serviceAmount - amount of money in the action
     * @return the service amount multiplied by the fee rate
     */
    public double getServiceFeeAmount(double serviceAmount) {
        return serviceAmount * this.fee;
    }

    /**
     * Withdraws the given amount and gives it to the owner of the account and removes the service fee
     * @param amountToWithdraw - amount to withdraw (in the current currency type)
     * @return the new balance of the account
     */
    public double withdrawFromAccount(double amountToWithdraw) {
        /* Charge the fee */
        this.newFeeTransaction(amountToWithdraw);

        /* Take the money out of the account and give it to the owner */
        this.subtractFromBalance(amountToWithdraw);        
        this.owner.addToBalance(amountToWithdraw);

        return this.balance;
    }

    /**
     * When a transaction is done with this account that incurrs a fee, remove the correct fee from this account
     * @param transactionAmount - The amount of the transaction (in the correct currency)
     * @return
     */
    protected double newFeeTransaction(double transactionAmount) {
        this.subtractFromBalance(getServiceFeeAmount(transactionAmount));

        return this.balance;
    }

    /**
     * When a transaction is done with this account that incurrs a fee, remove the correct fee from this account
     * @param transactionAmount - the amount of the transaction in the given currency
     * @param transactionCurrency - the currency that the transaction amount is given in
     * @return
     */
    protected double newFeeTransaction(double transactionAmount, Currency transactionCurrency) {
        transactionAmount = transactionCurrency.convertFromCurrencyToOther(transactionAmount, this.currency);

        this.subtractFromBalance(getServiceFeeAmount(transactionAmount));

        return this.balance;
    }

    public boolean equals(Object other) {
        if(other instanceof Account) {
            Account otherAccount = (Account) other;
            if(otherAccount.getOwner().equals(this.getOwner()) && otherAccount.getBalance() == this.getBalance() && otherAccount.getFeeRate() == this.getFeeRate()) {
                return true;
            }
        }

        return false;
    }
    
    
    public void transfer(Account o, double amount) {
        transaction.transfer(o,amount);
    }
    
    public double getBalance() {
    	return this.balance;
    }
}