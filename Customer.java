import java.util.ArrayList;

public class Customer extends User{

	private Accounts<Savings> savings;
	private Accounts<Checking> checking;
	private Accounts<Securities> securities;

	
	public Customer(int userId,String firstName, String lastName, String userName, String password, double balance, Currency currency) {
		super(userId, firstName, lastName, userName, password, balance, currency);
		
	}
	
	public void withdraw(double amount, Account account) {
        account.withdrawFromAccount(amount);
    }

    public void deposit(double amount, Account account) {
        //account.deposit(amount);
    }

	
	public void createCheckingAccount(User user, Currency currency, double balance, double rate) {
		Checking newChecking = new Checking(user, currency, balance, rate);
		checking.add(newChecking);
	}
	
	public void createSavingAccount(User user, Currency currency, double balance, double feeRate, double interestThreshold, double interestRate) {
		Savings newSaving = new Savings(user, currency, balance, feeRate, interestThreshold, interestRate);
		savings.add(newSaving);
	}
	
	public void createLoan(User owner, User loaner, Currency currency, double balance, double interestRate) {
		Loan newLoan = new Loan(owner, loaner, currency, balance, interestRate);
		//loan.add(newLoan);
	}
	
	public void createSecuritiesAccount(User user, Currency currency, double balance, double feeRate, double interestThreshold, double interestRate) {
		Securities newSecurities = new Securities(user, currency, balance, feeRate);
		securities.add(newSecurities);
	}
	
	private boolean isCustomerAccount(Account account){
        return savings.contains(account) || checking.contains(account) ||
                securities.contains(account);
    }
	
	public void transferToAccount(Account from, Account to, double amount){
        if (isCustomerAccount(from)) {
        	from.transfer(to, amount);
        }
    }
	
}
