import java.util.ArrayList;
/**
 * Abstract ATM class, Bank.java extends it. It shows the basic functions of an ATM. The main purpose of this class is for future extension.
 * @author ling
 *
 */
public abstract class ATM {
	
	protected User currentUser;
	protected Admin admin;
	protected IO bankIO;
	protected ArrayList<Currency> currencies;
	protected ArrayList<User> users;
	protected ArrayList<Account> accounts;
	protected ArrayList<Transaction> transactions;
	protected ArrayList<Loan> loans;
	
	public ATM() {
		
	}
	
	public abstract void saveData();
	public abstract void addNewUserToSystem(User user);
	public abstract boolean registerNewUser(boolean newAdmin, String firstName, String lastName, String username, String password, double startingBalance, Currency currency);
	public abstract boolean createNewAccount(AccountType type, User owner, Currency currencyType, double startingAmount);
	public abstract User getUserByUsername(String username);
	public abstract boolean login(String username, String password);
	public abstract boolean withdrawFromAccount(User user, Account account, double amount);
	public abstract boolean depositToAccount(User user, Account account, double amount);
	public abstract boolean transferMoney(BalanceHandler sender, BalanceHandler receiver, double amount);
	public abstract void requestLoan(Customer owner, Currency currencyType, double amount);
	public abstract ArrayList<Transaction> getTransactionsForAccount(Account account);
	public abstract ArrayList<Transaction> getTransactionsForLoan(Loan loan);
	public abstract ArrayList<Transaction> getDailyReport();

}
