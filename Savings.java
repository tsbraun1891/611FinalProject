public class Savings extends Account {
    double threshold, rate;
    
    /**
     * Create a new savings account
     * @param owner - the user that owns this account
     * @param currency - the type of the currency stored in this account
     * @param balance - the current amount of that currency in this account
     * @param interestThreshold - the amount of money the account needs to have in order to start generating interest (in the given currency type)
     * @param interestRate - the rate of interest that this account can generate
     */
    public Savings(User owner, Currency currency, double balance, double interestThreshold, double interestRate) {
        super(owner, currency, balance);
        this.threshold = interestThreshold;
        this.rate = interestRate;
    }

    public double getInterestRate() {
        return this.rate;
    }

    public double getThreshold() {
        return this.threshold;
    }

    /**
     * Function to call at the end of every month. Interest is generated at
     * the end of every month.
     */
    public void passOneMonth() {
        if(this.balance >= this.threshold) {
            this.balance += this.balance * this.rate;
        }
    }

    /**
     * Change all of the funds in this account to the new currency type and convert
     * according to the given exchange rate
     * @param newCurrency - the new currency type
     */
    public void setCurrency(Currency newCurrency) {
        this.balance = currency.convertFromCurrency(this.balance);
        this.threshold = currency.convertFromCurrency(this.threshold);

        this.currency = newCurrency;

        this.balance = currency.convertToCurrency(this.balance);
        this.threshold = currency.convertToCurrency(this.threshold);
    }
}
