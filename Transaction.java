import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The transaction class keeps track of information about recent transactions
 */

public class Transaction {

    private BalanceHandler sender, receiver;
    private double transactionAmount;
    private Currency currency;
    private int transactionID;
    private String date;

    /* Since sender and receiver must be either accounts or users, these flags will let
        you know if they are a user or not */
    private boolean senderUser, receiverUser, senderAccount, receiverAccount;

	public Transaction(int id, BalanceHandler sender, BalanceHandler receiver, double amount, Currency currencyType) {
        this.sender = sender;
        this.receiver = receiver;
        this.transactionAmount = amount;
        this.currency = currencyType;
        this.transactionID = id;

        senderUser = sender instanceof User;
        receiverUser = receiver instanceof User;
        senderAccount = sender instanceof Account;
        receiverAccount = receiver instanceof Account;
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(cal.getTime());
        setDate(date);
    }

    public Transaction(int id, BalanceHandler sender, BalanceHandler receiver, double amount, Currency currencyType, String date) {
        this.sender = sender;
        this.receiver = receiver;
        this.transactionAmount = amount;
        this.currency = currencyType;
        this.transactionID = id;

        senderUser = sender instanceof User;
        receiverUser = receiver instanceof User;
        senderAccount = sender instanceof Account;
        receiverAccount = receiver instanceof Account;

        setDate(date);
    }
	
	//setters and getters
    private void setDate(String date) {
    	this.date = date;
	}
    
    public String getDate() {
    	return date;
    }
    
    public BalanceHandler getSender() {
        return this.sender;
    }

    public BalanceHandler getReceiver() {
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

    public boolean isSenderUser() {
        return this.senderUser;
    }

    public boolean isReceiverUser() {
        return this.receiverUser;
    }

    public boolean isSenderAccount() {
        return this.senderAccount;
    }

    public boolean isReceiverAccount() {
        return this.receiverAccount;
    }

    /**
     * @return the User/Account ID of the sender
     */
    public int getSenderID() {

        /* BalanceHandlers are either users or accounts */
        if(this.senderUser) {
            User temp = (User) this.getSender();

            return temp.getUserId();
        } else if(this.senderAccount) {
            Account temp = (Account) this.getSender();

            return temp.getID();
        } else {
            Loan temp = (Loan) this.getSender();

            return temp.getID();
        }

    }

    /**
     * @return the User/Account ID of the receiver
     */
    public int getReceiverID() {

        /* BalanceHandlers are either users or accounts */
        if(this.receiverUser) {
            User temp = (User) this.getReceiver();

            return temp.getUserId();
        } else if(this.receiverAccount) {
            Account temp = (Account) this.getReceiver();

            return temp.getID();
        } else {
            Loan temp = (Loan) this.getReceiver();

            return temp.getID();
        }

    }
}
