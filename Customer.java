import java.util.ArrayList;

public class Customer extends User{

	private ArrayList<Savings> savings;
	private ArrayList<Checking> checking;
	private ArrayList<Securities> securities;
	private ArrayList<Loan> loan = new ArrayList<>();
	private ArrayList<LoanPending> pendingLoan = new ArrayList<>();
	
	public Customer(int userId,String firstName, String lastName, String userName, String password, double balance, Currency currency) {
		super(userId, firstName, lastName, userName, password, balance, currency);
		
	}
	
	public void withdraw(double amount, Account account) {
        account.withdrawFromAccount(amount);
    }

	//not sure
    public void deposit(double amount, Account account) {
        account.newFeeTransaction(amount);
    }

	//Create accounts
	public void createCheckingAccount(User user, Currency currency, double balance, double rate) {
		Checking newChecking = new Checking(user, currency, balance, rate);
		checking.add(newChecking);
	}
	
	public void createSavingAccount(User user, Currency currency, double balance, double feeRate, double interestThreshold, double interestRate) {
		Savings newSaving = new Savings(user, currency, balance, feeRate, interestThreshold, interestRate);
		savings.add(newSaving);
	}
	
	public void RequestLoan(User owner, User loaner, Currency currency, double balance, double interestRate) {
		LoanPending newLoan = new LoanPending(owner, loaner, currency, balance, interestRate);
		pendingLoan.add(newLoan);
	}
	
	public void createSecuritiesAccount(User user, Currency currency, double balance, double feeRate, double interestThreshold, double interestRate) {
		Securities newSecurities = new Securities(user, currency, balance, feeRate);
		securities.add(newSecurities);
	}
	
	
	//get total balance of customer
	public double getTotalBalance() {
		return getSavingBalance()+ getCheckingBalance()+ getSecuritiesBalance();
	}
	
	private double getSavingBalance() {
		double balance = 0;
		for(Savings c: savings) {
			balance += c.getBalance();
		}
		return balance;
	}
	
	private double getCheckingBalance() {
		double balance = 0;
		for(Checking c: checking) {
			balance += c.getBalance();
		}
		return balance;
	}
	
	private double getSecuritiesBalance() {
		double balance = 0;
		for(Securities c: securities) {
			balance += c.getBalance();
		}
		return balance;
	}
	
	//getters for customers all accounts
	public ArrayList<Checking> getAllCheckingAcc() {
		return checking;
	}
	
	public ArrayList<Savings> getAllSavingsAcc(){
		return savings;
	}
	
	public ArrayList<LoanPending> getPendingLoan(){
		return pendingLoan;
	}
	public ArrayList<Loan> getAllLoan(){
		return loan;
	}
	
	
}
