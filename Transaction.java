
public abstract class Transaction {

	private Account account;
	
	public Transaction(Account account) {
        this.account = account;
    }

    abstract public void transfer(Account account,double amount);
    
}
