<<<<<<< Updated upstream
<<<<<<< Updated upstream
=======
=======
>>>>>>> Stashed changes
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The transaction class keeps track of information about recent transactions
 */
>>>>>>> Stashed changes

public abstract class Transaction {

<<<<<<< Updated upstream
	private Account account;
	
	public Transaction(Account account) {
        this.account = account;
=======
    private BalanceHandler sender, receiver;
    private double transactionAmount;
    private Currency currency;
    private int transactionID;
    private String date;

    /* Since sender and receiver must be either accounts or users, these flags will let
        you know if they are a user or not */
    private boolean senderUser, receiverUser;
	
	public Transaction(int id, BalanceHandler sender, BalanceHandler receiver, double amount, Currency currencyType) {
        this.sender = sender;
        this.receiver = receiver;
        this.transactionAmount = amount;
        this.currency = currencyType;
        this.transactionID = id;

        senderUser = sender instanceof User;
        receiverUser = receiver instanceof User;
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cal.getTime());
        setDate(date);
<<<<<<< Updated upstream
    }
/*
	public Transaction(int id, BalanceHandler sender, BalanceHandler receiver, double amount, Currency currencyType, String date) {
		this(id, sender, receiver, amount, currencyType);
		setDate(date);
	}
	*/
	
	//setters and getters
    private void setDate(String date) {
    	this.date = date;
	}
    
    public String getDate() {
    	return date;
    }

	public BalanceHandler getSender() {
=======
    }

	//setters and getters
	private void setDate(String date) {
    	this.date = date;
	}
    
    public String getDate() {
    	return date;
    }
    public BalanceHandler getSender() {
>>>>>>> Stashed changes
        return this.sender;
    }

    public BalanceHandler getReceiver() {
        return this.receiver;
    }

    public double getTransactionAmount() {
        return this.transactionAmount;
>>>>>>> Stashed changes
    }

    abstract public void transfer(Account account,double amount);
    
}
