
public abstract class Account extends BalanceHolder {
    protected User owner;
    

    /**
     * Create a new account
     * @param owner - the user that owns this account
     * @param currency - the type of the currency stored in this account
     * @param balance - the current amount of that currency in this account
     */
    public Account(User owner, Currency currency, double balance) {
        super(balance, currency);

        this.balance = balance;
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

    /**
     * Withdraws the given amount and gives it to the owner of the account
     * @param amountToWithdraw - amount to withdraw (in the current currency type)
     * @return the new balance of the account
     */
    public double withdrawFromAccount(double amountToWithdraw) {
        this.subtractFromBalance(amountToWithdraw);
        this.owner.addToBalance(amountToWithdraw);
        return this.balance;
    }
}