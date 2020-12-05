public abstract class BalanceHandler {
    protected Currency currency;
    protected double balance;

    public BalanceHandler(double startingBalance, Currency currency) {
        this.currency = currency;
        this.balance = startingBalance;
    }

    /**
     * Change all of the funds in this account to the new currency type and convert
     * according to the given exchange rate
     * @param newCurrency - the new currency type
     * @return new balance
     */
    public double setCurrency(Currency newCurrency) {
        this.balance = this.currency.convertFromCurrencyToOther(this.balance, newCurrency);
        this.currency = newCurrency;

        return this.balance;
    }

    public double getBalance() {
        return balance;
    }

    private void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public Currency getCurrencyType() {
        return currency;
    }

    /**
     * Adds the given amount to the current balance
     * @param additionalFunds - amount to add (in the correct currency type)
     * @return new balance amount
     */
    public double addToBalance(double additionalFunds) {
        this.setBalance(this.getBalance() + additionalFunds);

        return this.getBalance();
    }

    /**
     * Adds the given amount to the current balance
     * @param additionalFunds - amount to add 
     * @param currencyType - the currency type that this amount is given in
     * @return new balance amount
     */
    public double addToBalance(double additionalFunds, Currency currencyType) {
        additionalFunds = currencyType.convertFromCurrencyToOther(additionalFunds, this.currency);
        this.setBalance(this.getBalance() + additionalFunds);

        return this.getBalance();
    }

    /**
     * Substracts the given amount from the current balance
     * @param amountToSubtract - amount to subtract (in the correct currency type)
     * @return new balance amount
     */
    public double subtractFromBalance(double amountToSubtract) {
        this.setBalance(this.getBalance() - amountToSubtract);

        return this.getBalance();
    }

    /**
     * Substracts the given amount from the current balance
     * @param amountToWithdraw - amount to subtract (in the given currency type)
     * @param currencyType - the currency type that this amount is given in
     * @return new balance amount
     */
    public double subtractFromBalance(double amountToSubtract, Currency currencyType) {
        amountToSubtract = currencyType.convertFromCurrencyToOther(amountToSubtract, this.currency);
        this.setBalance(this.getBalance() - amountToSubtract);

        return this.getBalance();
    }
}
