/**
 * The transaction class keeps track of information about recent transactions
 */

public class Transaction {

    private Account sender;
    private User receiver;
    private double transactionAmount;
    private Currency currency;
    private int transactionID;
	
	public Transaction(int id, Account sender, User receiver, double amount, Currency currencyType) {
        this.sender = sender;
        this.receiver = receiver;
        this.transactionAmount = amount;
        this.currency = currencyType;
        this.transactionID = id;
    }

    public Account getSender() {
        return this.sender;
    }

    public User getReceiver() {
        return this.receiver;
    }

    public double getTransactionAmount() {
        return this.transactionAmount;
    }

    /**
     * Returns the type of currency that the transaction
     * was done in (i.e. that the amount is given in)
     */
    public Currency getCurrencyType() {
        return this.currency;
    }

    public int getID() {
        return this.transactionID;
    }
}
