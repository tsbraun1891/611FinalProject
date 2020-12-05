public class Savings extends Account {
    private double threshold, rate;
    
    /**
     * Create a new savings account
     * @param owner - the user that owns this account
     * @param currency - the type of the currency stored in this account
     * @param balance - the current amount of that currency in this account
     * @param interestThreshold - the amount of money the account needs to have in order to start generating interest (in the given currency type)
     * @param interestRate - the rate of interest that this account can generate
     */
    public Savings(User owner, Currency currency, double balance, double feeRate, double interestThreshold, double interestRate) {
        super(owner, currency, balance, feeRate);
        this.threshold = interestThreshold;
        this.rate = interestRate;
    }

    public double getInterestRate() {
        return this.rate;
    }

    public double getThreshold() {
        return this.threshold;
    }

    public void setInterestRate(double newRate) {
        this.rate = newRate;
    }

    public void setThreshold(double newThreshold) {
        this.threshold = newThreshold;
    }

    /**
     * Function to call at the end of every month. Interest is generated at
     * the end of every month.
     * @return the balance after a month passes
     */
    public double passOneMonth() {
        if(this.balance >= this.threshold) {
            this.balance += this.balance * this.rate;
        }

        return this.balance;
    }

    /**
     * Change all of the funds in this account to the new currency type and convert
     * according to the given exchange rate
     * @param newCurrency - the new currency type
     * @return the new balance after changing the currency
     */
    public double setCurrency(Currency newCurrency) {
        this.balance = currency.convertFromCurrency(this.balance);
        this.threshold = currency.convertFromCurrency(this.threshold);

        this.currency = newCurrency;

        this.balance = currency.convertToCurrency(this.balance);
        this.threshold = currency.convertToCurrency(this.threshold);

        return this.balance;
    }
}
