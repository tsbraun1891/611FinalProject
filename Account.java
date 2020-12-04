
public abstract class Account {
    protected User owner;
    protected Currency currency;
    protected double balance;

    /**
     * Create a new account
     * @param owner - the user that owns this account
     * @param currency - the type of the currency stored in this account
     * @param balance - the current amount of that currency in this account
     */
    public Account(User owner, Currency currency, double balance) {
        this.owner = owner;
        this.currency = currency;
        this.balance = balance;
    }

    public Currency getCurrencyType() {
        return currency;
    }

    public double getBalance() {
        return balance;
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
     * Change all of the funds in this account to the new currency type and convert
     * according to the given exchange rate
     * @param newCurrency - the new currency type
     */
    public void setCurrency(Currency newCurrency) {
        this.balance = currency.convertFromCurrency(this.balance);
        this.currency = newCurrency;
        this.balance = currency.convertToCurrency(this.balance);
    }

}