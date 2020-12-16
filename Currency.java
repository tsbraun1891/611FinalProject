public class Currency {
	/**
	 * the class represents all currencies 
	 */
    protected String name, desc, symbol;
    protected double exchangeRate;

    /**
     * Create a new currency type
     * @param name - The full name of the new currency type
     * @param desc - A 3 letter abbreviation of the name
     * @param exchangeRate - The exchange rate between this amount and a standard used 
     */
    public Currency(String name, String desc, String symbol, double exchangeRate) {
        this.name = name;
        this.desc = desc;
        this.symbol = symbol;
        this.exchangeRate = exchangeRate;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getExchangeRate() {
        return this.exchangeRate;
    }

    public void setExchangeRate(double newRate) {
        this.exchangeRate = newRate;
    }

    public double convertToCurrency(double amount) {
        return amount * this.getExchangeRate();
    }

    public double convertFromCurrency(double amount) {
        return amount / this.getExchangeRate();
    }

    /**
     * Converts the given amount from this currency to the other given currency
     * @param amount
     * @param newCurrency
     * @return the amount after the conversion
     */
    public double convertFromCurrencyToOther(double amount, Currency newCurrency) {
        amount = this.convertFromCurrency(amount);
        amount = newCurrency.convertToCurrency(amount);

        return amount;
    }
}
